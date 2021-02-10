package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.sql.SQLHelper;
import ru.javawebinar.basejava.util.LoggerUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class SqlStorage implements Storage {
    private static final Logger LOG = LoggerUtil.initLogger();
    private final SQLHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new StorageException(e);
        }
        sqlHelper = new SQLHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.doQuery("DELETE FROM resume", PreparedStatement::execute);
    }

    @Override
    public void update(Resume resume) {
        LOG.info("Update " + resume);
        sqlHelper.transactionalExecute(conn -> {
            String uuid = resume.getUuid();
            try (var ps = conn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                ps.setString(1, resume.getFullName());
                ps.setString(2, uuid);
                if (ps.executeUpdate() < 1) {
                    throw new NotExistStorageException(uuid);
                }

                deleteContacts(conn, uuid);
                deleteSections(conn, uuid);
                insertContacts(conn, resume);
                insertSections(conn, resume);
            }
            return null;
        });
    }

    @Override
    public void save(Resume resume) {
        LOG.info("Save " + resume);
        sqlHelper.transactionalExecute(conn -> {
            try (var ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name)VALUES (?, ?)")) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, resume.getFullName());
                ps.execute();

                insertContacts(conn, resume);
                insertSections(conn, resume);
            }
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.transactionalExecute(conn -> {
            Resume resume;
            try (var ps = conn.prepareStatement("SELECT uuid, full_name FROM resume WHERE uuid = ?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                resume = new Resume(uuid, rs.getString("full_name"));
            }

            try (var ps = conn.prepareStatement("SELECT type, value FROM contact WHERE resume_uuid = ?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addContact(rs, resume);
                }
            }

            try (var ps = conn.prepareStatement("SELECT section, value FROM section WHERE resume_uuid = ?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addSection(rs, resume);
                }
            }
            return resume;
        });
    }

    @Override
    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        sqlHelper.doQuery("DELETE FROM resume WHERE uuid = ?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() < 1) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.transactionalExecute(conn -> {
            Map<String, Resume> resumes = new LinkedHashMap<>();
            try (var ps = conn.prepareStatement("SELECT uuid, full_name FROM resume ORDER BY full_name, uuid")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String uuid = rs.getString("uuid");
                    resumes.put(uuid, new Resume(uuid, rs.getString("full_name")));
                }
            }

            try (var ps = conn.prepareStatement("SELECT resume_uuid, type, value FROM contact")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Resume resume = resumes.get(rs.getString("resume_uuid"));
                    addContact(rs, resume);
                }
            }

            try (var ps = conn.prepareStatement("SELECT resume_uuid, section, value FROM section")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Resume resume = resumes.get(rs.getString("resume_uuid"));
                    addSection(rs, resume);
                }
            }

            return new ArrayList<>(resumes.values());
        });
    }

    @Override
    public int size() {
        return sqlHelper.doQuery("SELECT COUNT(uuid) AS uuid_count FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt("uuid_count") : 0;
        });
    }

    private void addSection(ResultSet rs, Resume resume) throws SQLException {
        SectionType section = SectionType.valueOf(rs.getString("section"));
        switch (section) {
            case PERSONAL, OBJECTIVE -> resume.addSection(section, new StringSection(rs.getString("value")));
            case ACHIEVEMENT, QUALIFICATIONS -> resume.addSection(section, new ListSection(List.of(
                    rs.getString("value").split("\n")
            )));
        }
    }

    private void addContact(ResultSet rs, Resume resume) throws SQLException {
        resume.addContact(
                ContactType.valueOf(rs.getString("type")),
                rs.getString("value")
        );
    }

    private void insertSections(Connection conn, Resume resume) throws SQLException {
        try (var ps = conn.prepareStatement("INSERT INTO section (resume_uuid, section, value) VALUES (?, ?, ?)")) {
            for (Map.Entry<SectionType, AbstractSection> entry : resume.getSections().entrySet()) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, entry.getKey().name());
                switch (entry.getKey()) {
                    case PERSONAL, OBJECTIVE -> ps.setString(3, ((StringSection) entry.getValue()).getValue());
                    case ACHIEVEMENT, QUALIFICATIONS -> ps.setString(3, String.join("\n", ((ListSection) entry.getValue()).getElements()));
                }
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void insertContacts(Connection conn, Resume resume) throws SQLException {
        try (var ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?, ?, ?)")) {
            for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, entry.getKey().name());
                ps.setString(3, entry.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void deleteSections(Connection conn, String uuid) throws SQLException {
        doDelete(conn, uuid, "DELETE FROM section WHERE resume_uuid = ?");
    }

    private void deleteContacts(Connection conn, String uuid) throws SQLException {
        doDelete(conn, uuid, "DELETE FROM contact WHERE resume_uuid = ?");
    }

    private void doDelete(Connection conn, String uuid, String query) throws SQLException {
        try (var ps = conn.prepareStatement(query)) {
            ps.setString(1, uuid);
            ps.execute();
        }
    }
}
