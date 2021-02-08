package ru.javawebinar.basejava.sql;

import org.postgresql.util.PSQLException;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class ExceptionUtil {
    private ExceptionUtil() {
    }

    public static StorageException convertException(SQLException e) {
        if (e instanceof PSQLException) {
            // POSTGRESQL UNIQUE VIOLATION
            if ("23505".equals(e.getSQLState())) {
                return new ExistStorageException(e);
            }
        } else if (e instanceof SQLIntegrityConstraintViolationException) {
            // MYSQL UNIQUE VIOLATION
            return new ExistStorageException(e);
        }
        return new StorageException(e);
    }
}
