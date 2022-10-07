package db;

import java.sql.*;
import java.util.Properties;
import javax.sql.DataSource;
import java.io.*;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Database {

    private static Properties prop = new Properties();
    private static String url = "";
    private static String username = "";
    private static String password = "";
    private static DataSource ds;

    private static void getProps() {
        try {
            prop.load(Database.class.getClassLoader().getResourceAsStream("db.properties"));
            url = prop.getProperty("url");
            username = prop.getProperty("user ");
            password = prop.getProperty("pass");
            System.out.println(url);
            System.out.println(username);
            System.out.println(password);  
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createDataSource() {
        Database.getProps();

        if (ds == null) {
            HikariConfig config = new HikariConfig();
            config.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
            //config.setJdbcUrl(url);
            config.setUsername(username);
            config.setPassword(password);
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtcacheSqlLimit", "2048");

            ds = new HikariDataSource(config);
        }
    }
}
