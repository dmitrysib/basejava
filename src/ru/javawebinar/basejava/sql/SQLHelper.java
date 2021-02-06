package ru.javawebinar.basejava.sql;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLHelper {
    private static final String POSTGRES_DUPLICATE_KEY_STATEMENT = "23505";
    private final ConnectionFactory cf;

    public SQLHelper(ConnectionFactory cf) {
        this.cf = cf;
    }

    public <T> T doQuery(String query, SQLAction<T> action) {
        try (Connection conn = cf.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            return action.execute(ps);

        } catch (SQLException e) {
            if (POSTGRES_DUPLICATE_KEY_STATEMENT.equals(e.getSQLState())) {
                throw new ExistStorageException(null);
            }
            throw new StorageException(e);
        }
    }
}
