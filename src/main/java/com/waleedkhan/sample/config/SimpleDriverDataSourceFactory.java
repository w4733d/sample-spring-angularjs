package com.waleedkhan.sample.config;

import java.sql.Driver;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.ConnectionProperties;
import org.springframework.jdbc.datasource.embedded.DataSourceFactory;

public class SimpleDriverDataSourceFactory implements DataSourceFactory {
    private final SimpleDriverDataSource dataSource;

    public SimpleDriverDataSourceFactory(
            SimpleDriverDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public ConnectionProperties getConnectionProperties() {
        return new ConnectionProperties() {
            @Override
            public void setDriverClass(Class<? extends Driver> driverClass) {
                dataSource.setDriverClass(driverClass);
            }

            @Override
            public void setUrl(String url) {
                dataSource.setUrl(url);
            }

            @Override
            public void setUsername(String username) {
                dataSource.setUsername(username);
            }

            @Override
            public void setPassword(String password) {
                dataSource.setPassword(password);
            }
        };
    }

    @Override
    public DataSource getDataSource() {
        return this.dataSource;
    }
}
