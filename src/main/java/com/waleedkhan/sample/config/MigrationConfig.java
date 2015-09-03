package com.waleedkhan.sample.config;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.apache.ibatis.migration.DataSourceConnectionProvider;
import org.apache.ibatis.migration.FileMigrationLoader;
import org.apache.ibatis.migration.operations.UpOperation;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.context.support.ServletContextResourceLoader;

@Configuration
public class MigrationConfig {
    private static final String CLASSES_PATH = "/WEB-INF/classes/";

    @Inject
    void registerMigrationConfig(ServletContext servletContext, DataSource dataSource) throws IOException {
        ServletContextResourceLoader servletContextResourceLoader
                = new ServletContextResourceLoader(servletContext);
        Resource resource = servletContextResourceLoader.getResource(CLASSES_PATH);

        new UpOperation().operate(
                new DataSourceConnectionProvider(dataSource),
                new FileMigrationLoader(resource.getFile(), null, null), null, System.out);
    }
}
