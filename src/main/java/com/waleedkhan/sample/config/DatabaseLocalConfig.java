package com.waleedkhan.sample.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class DatabaseLocalConfig {
    @Bean
    public DataSource dataSource() throws IOException {
        Properties properties = new Properties();
        properties.put("sql.syntax_pgs", "true");

        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setConnectionProperties(properties);

        SimpleDriverDataSourceFactory dataSourceFactory = new SimpleDriverDataSourceFactory(dataSource);

        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
        embeddedDatabaseBuilder.setDataSourceFactory(dataSourceFactory)
                .setType(EmbeddedDatabaseType.HSQL)
                .setName("DEVDB");

        return embeddedDatabaseBuilder.build();
    }
}
