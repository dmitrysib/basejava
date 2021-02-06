package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.ConnectionFactory;
import ru.javawebinar.basejava.sql.SQLHelper;
import ru.javawebinar.basejava.util.LoggerUtil;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SqlStorage implements Storage {
    private static final Logger LOG = LoggerUtil.initLogger();
    private final ConnectionFactory cf;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        cf = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    private boolean isExit(String uuid) {
        var result = SQLHelper.getOneResult(cf, "SELECT full_name FROM resume r WHERE uuid = ?", uuid);
        return result != null;
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
        SQLHelper.doQuery(cf, "DELETE FROM resume");
    }

    @Override
    public void update(Resume resume) {
        LOG.info("Update " + resume);
        checkExistKey(resume.getUuid());
        SQLHelper.doQuery(cf, "UPDATE resume SET full_name = ? WHERE uuid = ?", resume.getFullName(), resume.getUuid());
    }

    @Override
    public void save(Resume resume) {
        LOG.info("Save " + resume);
        checkNotExistKey(resume.getUuid());
        SQLHelper.doQuery(cf, "INSERT INTO resume (uuid, full_name)VALUES (?, ?)", resume.getUuid(), resume.getFullName());
    }

    @Override
    public Resume get(String uuid) {
        var fullName = SQLHelper.getOneResult(cf, "SELECT full_name FROM resume r WHERE uuid = ?", uuid);
        if (fullName == null) {
            throw new NotExistStorageException(uuid);
        }
        return new Resume(uuid, fullName);
    }

    @Override
    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        checkExistKey(uuid);
        SQLHelper.doQuery(cf, "DELETE FROM resume WHERE uuid = ?", uuid);
    }

    @Override
    public List<Resume> getAllSorted() {
        var result = SQLHelper.doQuerySelect(cf, "SELECT * FROM resume ORDER BY full_name, uuid");
        List<Resume> resumes = new ArrayList<>(result.size());

        result.forEach(res -> resumes.add(new Resume(res.get(0), res.get(1))));
        return resumes;
    }

    @Override
    public int size() {
        var result = SQLHelper.getOneResult(cf, "SELECT COUNT(uuid) FROM resume");
        return result == null ? 0 : Integer.parseInt(result);
    }
}
