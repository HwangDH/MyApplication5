package com.example.myapplication;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnect {
    static {
        try {
            String driverName="com.mysql.jdbc.Driver";
            Class.forName(driverName);
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException{
        String url="https://scv0319.cafe24.com/mydb/";
        String id="scv0319";
        String password="jessica0319!";
        Connection conn=DriverManager.getConnection(url,id,password);
        return conn;
    }

    public static void close(PreparedStatement ps, Connection conn) {
        if(ps!=null) {
            try {
                ps.close();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null) {
            try {
                conn.close();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet rs, PreparedStatement ps, Connection conn) {
        if(rs!=null) {
            try {
                rs.close();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
        close(ps,conn);
    }
}
