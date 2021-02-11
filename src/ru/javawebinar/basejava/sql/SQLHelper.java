package ru.javawebinar.basejava.sql;

import ru.javawebinar.basejava.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLHelper {
    private final ConnectionFactory cf;

    public SQLHelper(ConnectionFactory cf) {
        this.cf = cf;
    }

    public <T> T doQuery(String sql, SQLAction<T> action) {
        try (Connection conn = cf.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            return action.execute(ps);
        } catch (SQLException e) {
            throw ExceptionUtil.convertException(e);
        }
    }

    public <T> T transactionalExecute(SqlTransaction<T> action) {
        try (Connection conn = cf.getConnection()) {
            try {
                conn.setAutoCommit(false);
                T result = action.execute(conn);
                conn.commit();
                return result;
            } catch (SQLException e) {
                conn.rollback();
                throw ExceptionUtil.convertException(e);
            }
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}
