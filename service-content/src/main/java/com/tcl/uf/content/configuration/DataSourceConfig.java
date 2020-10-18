package com.tcl.uf.content.configuration;

public class DataSourceConfig {

   /* @Autowired
    private Environment props;
    *//**
     * 主库
     *//*
    @Bean(destroyMethod = "close", name="master")
    @Primary
    public HikariDataSource masterDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
        hikariConfig.setJdbcUrl(props.getProperty("spring.shardingsphere.datasource.ds_master.jdbc-url"));
        hikariConfig.setUsername(props.getProperty("spring.shardingsphere.datasource.ds_master.username"));
        hikariConfig.setPassword(props.getProperty("spring.shardingsphere.datasource.ds_master.password"));
        return new HikariDataSource(hikariConfig);
    }

    *//**
     * 从库
     *//*
    @Bean(destroyMethod = "close", name="slave")
    public HikariDataSource slaveDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(props.getProperty("spring.shardingsphere.datasource.ds_slave.jdbc-url"));
        hikariConfig.setUsername(props.getProperty("spring.shardingsphere.datasource.ds_slave.username"));
        hikariConfig.setPassword(props.getProperty("spring.shardingsphere.datasource.ds_slave.password"));
        return new HikariDataSource(hikariConfig);
    }

    @Bean(name="dynamicDataSource")
    public DataSource dynamicDataSource() throws IOException {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("master", masterDataSource());
        targetDataSources.put("slave", slaveDataSource());

        AbstractRoutingDataSource dynamicDataSource = new AbstractRoutingDataSource() {
            @Override
            protected Object determineCurrentLookupKey() {
                String lookupKey = TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? "slave" : "master";
                System.out.println("connected DataSource :" + lookupKey);
                return lookupKey;
            }
        };

        dynamicDataSource.setDefaultTargetDataSource(targetDataSources.get("master"));
        dynamicDataSource.setTargetDataSources(targetDataSources);
        return dynamicDataSource;
    }

    @Bean
    public DataSource dataSource() throws IOException {
        return new LazyConnectionDataSourceProxy(dynamicDataSource());
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) throws IOException {
        return builder.dataSource(dataSource()).packages("com.tcl.uf.content.model").build();
    }

    @Bean(name = "transactionManager")
    JpaTransactionManager transactionManager(EntityManagerFactoryBuilder builder) throws IOException {
        return new JpaTransactionManager(entityManagerFactory(builder).getObject());
    }*/
}
