package com.sangeng;

import java.sql.*;

public class TableColumnsInfo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://1.117.162.30:3306/db01";
        String username = "root";
        String password = "!zCECFhgQjX6";
        String table = "user";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            DatabaseMetaData metaData = connection.getMetaData();

            // 获取指定表的列信息
            ResultSet rs = metaData.getColumns(null, null, table, "%");

            // 遍历结果集，打印列信息
            while (rs.next()) {
                String columnName = rs.getString("COLUMN_NAME");
                String dataType = rs.getString("TYPE_NAME");
                int columnSize = rs.getInt("COLUMN_SIZE");

                System.out.println("Column Name: " + columnName);
                System.out.println("Data Type: " + dataType);
                System.out.println("Column Size: " + columnSize);
                System.out.println("-------------------------");
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}