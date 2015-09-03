

package com.waleedkhan.sample.config;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.waleedkhan.sample.dao.MyBatisMapper;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.waleedkhan.sample.dao", annotationClass = MyBatisMapper.class)
public class PersistenceConfig {
    private static final Logger LOGGER = Logger.getLogger(PersistenceConfig.class.getSimpleName());

    private static final String MYBATIS_CONFIGURATION = "mybatis/MyBatisConfiguration.xml";

    private static final String MYBATIS_XML_MAPPERS = "classpath*:mybatis/mappers/*.xml";

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) throws SQLException {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) throws IOException {
        ClassPathResource configLocation = new ClassPathResource(MYBATIS_CONFIGURATION);
        Resource[] mapperLocations = getMappers();

        logDiscoveredMappers(mapperLocations);

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setFailFast(true);
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfigLocation(configLocation);
        sqlSessionFactoryBean.setMapperLocations(mapperLocations);
        sqlSessionFactoryBean.setDatabaseIdProvider(databaseIdProvider());

        return sqlSessionFactoryBean;
    }

    private VendorDatabaseIdProvider databaseIdProvider() {
        Properties properties = new Properties();
        properties.put("HSqlDb", "HSqlDb");

        VendorDatabaseIdProvider vendorDatabaseIdProvider = new VendorDatabaseIdProvider();
        vendorDatabaseIdProvider.setProperties(properties);

        return vendorDatabaseIdProvider;
    }

    private Resource[] getMappers() throws IOException {
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources(MYBATIS_XML_MAPPERS);

        // A mapper can be referenced by more than one JAR. We ensure we don't register duplicates.
        Map<String, Resource> mappers = new HashMap<>();
        for (Resource mapperLocation : resources) {
            mappers.put(mapperLocation.getFilename(), mapperLocation);
        }

        return mappers.values().toArray(new Resource[mappers.size()]);
    }

    private void logDiscoveredMappers(Resource[] mapperLocations) {
        for (Resource mapperLocation : mapperLocations) {
            LOGGER.info("MyBatis mapper discovered: " + mapperLocation);
        }
    }
}
