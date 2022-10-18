package com.epam.learning.library.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnection;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;

public class LogsConnectionFactory {

	private static interface Singleton {
        final LogsConnectionFactory INSTANCE = new LogsConnectionFactory();
    }
	
	private final DataSource dataSource;
	
	private String datasourceURL = "jdbc:mysql://localhost:3306/library_db";
	
	private String userName = "root";
	
	private String pass = "#445771qsd,.";
	
	private LogsConnectionFactory() {		 
		
	        Properties properties = new Properties();
	        properties.setProperty("user", userName);
	        properties.setProperty("password", pass);
	        
	        
	        GenericObjectPool<PoolableConnection> pool = new GenericObjectPool<PoolableConnection>();
	        DriverManagerConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
	        		datasourceURL, properties);
	        
	        new PoolableConnectionFactory(connectionFactory, pool, null, "SELECT 1", 3, false, false, Connection.TRANSACTION_READ_COMMITTED);
	 
	        this.dataSource = new PoolingDataSource(pool);
	}

	public static Connection getDatabaseConnection() throws SQLException {
	        return Singleton.INSTANCE.dataSource.getConnection();
	}
	
}
