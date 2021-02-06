package ru.javawebinar.basejava.sql;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;

import java.sql.SQLException;

public class SQLExceptionHelper extends RuntimeException {
    private static final String POSTGRES_DUPLICATE_KEY_STATEMENT = "23505";
    private static final int MYSQL_DUPLICATE_KEY_CODE = 1062;

    SQLExceptionHelper(SQLException e) {
        if (POSTGRES_DUPLICATE_KEY_STATEMENT.equals(e.getSQLState()) || MYSQL_DUPLICATE_KEY_CODE == e.getErrorCode()) {
            throw new ExistStorageException(null);
        }
        throw new StorageException(e);
    }
}
