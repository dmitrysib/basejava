package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.SQLHelper;
import ru.javawebinar.basejava.util.LoggerUtil;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SqlStorage implements Storage {
    private static final Logger LOG = LoggerUtil.initLogger();
    private final SQLHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SQLHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    private boolean isExit(String uuid) {
        return sqlHelper.doQuery("SELECT full_name FROM resume WHERE uuid = ?", ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        });
    }

    private void checkExistKey(String uuid) {
        if (!isExit(uuid)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
    }

    private void checkNotExistKey(String uuid) {
        if (isExit(uuid)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
    }

    @Override
    public void clear() {
        sqlHelper.doQuery("DELETE FROM resume", PreparedStatement::execute);
    }

    @Override
    public void update(Resume resume) {
        LOG.info("Update " + resume);
        checkExistKey(resume.getUuid());
        sqlHelper.doQuery("UPDATE resume SET full_name = ? WHERE uuid = ?", ps -> {
            ps.setString(1, resume.getFullName());
            ps.setString(2, resume.getUuid());
            return ps.executeUpdate();
        });
    }

    @Override
    public void save(Resume resume) {
        LOG.info("Save " + resume);
        checkNotExistKey(resume.getUuid());
        sqlHelper.doQuery("INSERT INTO resume (uuid, full_name)VALUES (?, ?)", ps -> {
            ps.setString(1, resume.getUuid());
            ps.setString(2, resume.getFullName());
            return ps.execute();
        });
    }

    @Override
    public Resume get(String uuid) {
        String fullName = sqlHelper.doQuery("SELECT full_name FROM resume WHERE uuid = ?", ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getString(1) : null;
        });
        if (fullName == null) {
            throw new NotExistStorageException(uuid);
        }
        return new Resume(uuid, fullName);
    }

    @Override
    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        checkExistKey(uuid);
        sqlHelper.doQuery("DELETE FROM resume WHERE uuid = ?", ps -> {
            ps.setString(1, uuid);
            return ps.execute();
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.doQuery("SELECT * FROM resume ORDER BY full_name, uuid", ps -> {
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            List<Resume> resumes = new ArrayList<>(metaData.getColumnCount());

            while (rs.next()) {
                resumes.add(new Resume(rs.getString(1), rs.getString(2)));
            }

            return resumes;
        });
    }

    @Override
    public int size() {
        return sqlHelper.doQuery("SELECT COUNT(uuid) FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }
}
