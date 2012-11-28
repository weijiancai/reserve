package com.wjc.activiti.demo.bean;

import javax.sql.DataSource;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * 流程引擎配置信息Bean
 */
@XmlRootElement
public class ProcessEngineConfigurationBean {
    protected String processEngineName;
    protected int idBlockSize;
    protected String history;
    protected boolean jobExecutorActivate;

    protected String mailServerHost;
    protected String mailServerUsername; // by default no name and password are provided, which
    protected String mailServerPassword; // means no authentication for mail server
    protected int mailServerPort;
    protected boolean useTLS = false;
    protected String mailServerDefaultFrom;

    protected String databaseType;
    protected String databaseSchemaUpdate;
    protected String jdbcDriver;
    protected String jdbcUrl;
    protected String jdbcUsername;
    protected String jdbcPassword;
    protected String dataSourceJndiName = null;
    protected int jdbcMaxActiveConnections;
    protected int jdbcMaxIdleConnections;
    protected int jdbcMaxCheckoutTime;
    protected int jdbcMaxWaitTime;
    protected boolean jdbcPingEnabled = false;
    protected String jdbcPingQuery = null;
    protected int jdbcPingConnectionNotUsedFor;
    protected DataSource dataSource;
    protected boolean transactionsExternallyManaged = false;

    protected String jpaPersistenceUnitName;
    protected Object jpaEntityManagerFactory;
    protected boolean jpaHandleTransaction;
    protected boolean jpaCloseEntityManager;

    public String getProcessEngineName() {
        return processEngineName;
    }

    public void setProcessEngineName(String processEngineName) {
        this.processEngineName = processEngineName;
    }

    public int getIdBlockSize() {
        return idBlockSize;
    }


    public void setIdBlockSize(int idBlockSize) {
        this.idBlockSize = idBlockSize;
    }


    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }


    public String getMailServerHost() {
        return mailServerHost;
    }


    public void setMailServerHost(String mailServerHost) {
        this.mailServerHost = mailServerHost;
    }


    public String getMailServerUsername() {
        return mailServerUsername;
    }


    public void setMailServerUsername(String mailServerUsername) {
        this.mailServerUsername = mailServerUsername;
    }


    public String getMailServerPassword() {
        return mailServerPassword;
    }


    public void setMailServerPassword(String mailServerPassword) {
        this.mailServerPassword = mailServerPassword;
    }


    public int getMailServerPort() {
        return mailServerPort;
    }


    public void setMailServerPort(int mailServerPort) {
        this.mailServerPort = mailServerPort;
    }


    public boolean getMailServerUseTLS() {
        return useTLS;
    }


    public void setMailServerUseTLS(boolean useTLS) {
        this.useTLS = useTLS;
    }


    public String getMailServerDefaultFrom() {
        return mailServerDefaultFrom;
    }


    public void setMailServerDefaultFrom(String mailServerDefaultFrom) {
        this.mailServerDefaultFrom = mailServerDefaultFrom;
    }


    public String getDatabaseType() {
        return databaseType;
    }


    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }


    public String getDatabaseSchemaUpdate() {
        return databaseSchemaUpdate;
    }


    public void setDatabaseSchemaUpdate(String databaseSchemaUpdate) {
        this.databaseSchemaUpdate = databaseSchemaUpdate;
    }


    public String getDataSource() {
        return dataSource.toString();
    }


    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public String getJdbcDriver() {
        return jdbcDriver;
    }


    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }


    public String getJdbcUrl() {
        return jdbcUrl;
    }


    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }


    public String getJdbcUsername() {
        return jdbcUsername;
    }


    public void setJdbcUsername(String jdbcUsername) {
        this.jdbcUsername = jdbcUsername;
    }


    public String getJdbcPassword() {
        return jdbcPassword;
    }


    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }


    public boolean isTransactionsExternallyManaged() {
        return transactionsExternallyManaged;
    }


    public void setTransactionsExternallyManaged(boolean transactionsExternallyManaged) {
        this.transactionsExternallyManaged = transactionsExternallyManaged;
    }


    public int getJdbcMaxActiveConnections() {
        return jdbcMaxActiveConnections;
    }


    public void setJdbcMaxActiveConnections(int jdbcMaxActiveConnections) {
        this.jdbcMaxActiveConnections = jdbcMaxActiveConnections;
    }


    public int getJdbcMaxIdleConnections() {
        return jdbcMaxIdleConnections;
    }


    public void setJdbcMaxIdleConnections(int jdbcMaxIdleConnections) {
        this.jdbcMaxIdleConnections = jdbcMaxIdleConnections;
    }


    public int getJdbcMaxCheckoutTime() {
        return jdbcMaxCheckoutTime;
    }


    public void setJdbcMaxCheckoutTime(int jdbcMaxCheckoutTime) {
        this.jdbcMaxCheckoutTime = jdbcMaxCheckoutTime;
    }


    public int getJdbcMaxWaitTime() {
        return jdbcMaxWaitTime;
    }

    public void setJdbcMaxWaitTime(int jdbcMaxWaitTime) {
        this.jdbcMaxWaitTime = jdbcMaxWaitTime;
    }

    public boolean isJdbcPingEnabled() {
        return jdbcPingEnabled;
    }

    public void setJdbcPingEnabled(boolean jdbcPingEnabled) {
        this.jdbcPingEnabled = jdbcPingEnabled;
    }

    public String getJdbcPingQuery() {
        return jdbcPingQuery;
    }

    public void setJdbcPingQuery(String jdbcPingQuery) {
        this.jdbcPingQuery = jdbcPingQuery;
    }

    public int getJdbcPingConnectionNotUsedFor() {
        return jdbcPingConnectionNotUsedFor;
    }

    public void setJdbcPingConnectionNotUsedFor(int jdbcPingNotUsedFor) {
        this.jdbcPingConnectionNotUsedFor = jdbcPingNotUsedFor;
    }

    public boolean isJobExecutorActivate() {
        return jobExecutorActivate;
    }


    public void setJobExecutorActivate(boolean jobExecutorActivate) {
        this.jobExecutorActivate = jobExecutorActivate;
    }


    public String getJpaEntityManagerFactory() {
        return jpaEntityManagerFactory.toString();
    }


    public void setJpaEntityManagerFactory(Object jpaEntityManagerFactory) {
        this.jpaEntityManagerFactory = jpaEntityManagerFactory;
    }


    public boolean isJpaHandleTransaction() {
        return jpaHandleTransaction;
    }


    public void setJpaHandleTransaction(boolean jpaHandleTransaction) {
        this.jpaHandleTransaction = jpaHandleTransaction;
    }


    public boolean isJpaCloseEntityManager() {
        return jpaCloseEntityManager;
    }


    public void setJpaCloseEntityManager(boolean jpaCloseEntityManager) {
        this.jpaCloseEntityManager = jpaCloseEntityManager;
    }

    public String getJpaPersistenceUnitName() {
        return jpaPersistenceUnitName;
    }

    public void setJpaPersistenceUnitName(String jpaPersistenceUnitName) {
        this.jpaPersistenceUnitName = jpaPersistenceUnitName;
    }

    public String getDataSourceJndiName() {
        return dataSourceJndiName;
    }

    public void setDataSourceJndiName(String dataSourceJndiName) {
        this.dataSourceJndiName = dataSourceJndiName;
    }
}
