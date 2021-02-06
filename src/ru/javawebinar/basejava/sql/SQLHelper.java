package ru.javawebinar.basejava.sql;

import ru.javawebinar.basejava.exception.StorageException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLHelper {

    private static void prepareQueryArguments(PreparedStatement ps, String... queryArgs) throws SQLException {
        for (int i = 0; i < queryArgs.length; i++) {
            ps.setString(i + 1, queryArgs[i]);
        }
    }

    public static void doQuery(ConnectionFactory cf, String query, String... queryArgs) {
        try (Connection conn = cf.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            SQLHelper.prepareQueryArguments(ps, queryArgs);

            ps.execute();

        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    public static List<List<String>> doQuerySelect(ConnectionFactory cf, String query, String... queryArgs) {
        try (Connection conn = cf.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            SQLHelper.prepareQueryArguments(ps, queryArgs);

            ps.executeQuery();

            ResultSet rs = ps.getResultSet();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            var result = new ArrayList<List<String>>(columnCount);
            while (rs.next()) {
                var row = new ArrayList<String>(columnCount);
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getString(i));
                }
                result.add(row);
            }
            return result;

        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    public static String getOneResult(ConnectionFactory cf, String query, String... queryArgs) {
        var result = doQuerySelect(cf, query, queryArgs);
        return result.size() == 0 ? null : result.get(0).get(0);
    }
}
