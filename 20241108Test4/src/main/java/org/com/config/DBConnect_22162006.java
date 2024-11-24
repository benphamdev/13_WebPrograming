package org.com.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.Connection;
import java.sql.SQLException;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DBConnect_22162006 {
    static String URL = "jdbc:mysql://localhost:3307/web_program";
    static String USER = "root";
    static String PASSWORD = "123456789";
    static String DRIVER = "com.mysql.cj.jdbc.Driver";

    static HikariConfig config = new HikariConfig();
    static HikariDataSource dataSource;
    static System.Logger logger = System.getLogger(DBConnect_22162006.class.getName());

    static {
        config.setJdbcUrl(URL);
        config.setUsername(USER);
        config.setPassword(PASSWORD);
        config.setDriverClassName(DRIVER);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("maximumPoolSize", "10");
        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() {
        try {
            logger.log(System.Logger.Level.INFO, "Connected to the database");
            return dataSource.getConnection();
        } catch (SQLException e) {
            logger.log(System.Logger.Level.ERROR, "Error in connecting to the database");
            return null;
        }
    }
}