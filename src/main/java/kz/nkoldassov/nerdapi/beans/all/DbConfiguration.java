package kz.nkoldassov.nerdapi.beans.all;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import kz.nkoldassov.nerdapi.configs.DbConfig;
import kz.nkoldassov.nerdapi.dao.BeanConfigDao;
import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.concurrent.atomic.AtomicReference;

@EnableTransactionManagement
@MapperScan(basePackageClasses = BeanConfigDao.class)
@Configuration
public class DbConfiguration {

    @Autowired
    private DbConfig dbConfig;

    private final AtomicReference<HikariDataSource> dataSourceRef = new AtomicReference<>(null);

    @Bean
    public DataSource getDataSource() {

        {
            final var ret = this.dataSourceRef.get();
            if (ret != null) {
                return ret;
            }
        }

        synchronized (this.dataSourceRef) {
            {
                final var ret = this.dataSourceRef.get();
                if (ret != null) {
                    return ret;
                }
            }

            {
                final var dataSource = this.createDataSource();
                this.dataSourceRef.set(dataSource);
                return dataSource;
            }
        }
    }

    private HikariDataSource createDataSource() {

        var config = new HikariConfig();
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl(dbConfig.url());
        config.setUsername(dbConfig.username());
        config.setPassword(dbConfig.password());

        return new HikariDataSource(config);
    }

    @Bean
    public JdbcTemplate applicationDataConnection(){
        return new JdbcTemplate(getDataSource());
    }

    @Bean
    public DataSourceTransactionManager txManager() {
        return new DataSourceTransactionManager(getDataSource());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        var configuration = new org.apache.ibatis.session.Configuration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setLogImpl(Slf4jImpl.class);
        configuration.setMapUnderscoreToCamelCase(true);

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(getDataSource());
        sqlSessionFactoryBean.setConfiguration(configuration);

        return sqlSessionFactoryBean.getObject();
    }
}
