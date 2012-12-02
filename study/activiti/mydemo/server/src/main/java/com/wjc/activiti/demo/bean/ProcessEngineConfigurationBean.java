package com.wjc.activiti.demo.bean;

import javax.sql.DataSource;
import javax.xml.bind.annotation.XmlAttribute;
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

    @XmlAttribute
    public String getProcessEngineName() {
        return processEngineName;
    }

    public void setProcessEngineName(String processEngineName) {
        this.processEngineName = processEngineName;
    }

    @XmlAttribute
    public int getIdBlockSize() {
        return idBlockSize;
    }


    public void setIdBlockSize(int idBlockSize) {
        this.idBlockSize = idBlockSize;
    }

    @XmlAttribute
    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    @XmlAttribute
    public String getMailServerHost() {
        return mailServerHost;
    }


    public void setMailServerHost(String mailServerHost) {
        this.mailServerHost = mailServerHost;
    }

    @XmlAttribute
    public String getMailServerUsername() {
        return mailServerUsername;
    }


    public void setMailServerUsername(String mailServerUsername) {
        this.mailServerUsername = mailServerUsername;
    }

    @XmlAttribute
    public String getMailServerPassword() {
        return mailServerPassword;
    }


    public void setMailServerPassword(String mailServerPassword) {
        this.mailServerPassword = mailServerPassword;
    }

    @XmlAttribute
    public int getMailServerPort() {
        return mailServerPort;
    }


    public void setMailServerPort(int mailServerPort) {
        this.mailServerPort = mailServerPort;
    }

    @XmlAttribute
    public boolean getMailServerUseTLS() {
        return useTLS;
    }


    public void setMailServerUseTLS(boolean useTLS) {
        this.useTLS = useTLS;
    }

    @XmlAttribute
    public String getMailServerDefaultFrom() {
        return mailServerDefaultFrom;
    }


    public void setMailServerDefaultFrom(String mailServerDefaultFrom) {
        this.mailServerDefaultFrom = mailServerDefaultFrom;
    }

    @XmlAttribute
    public String getDatabaseType() {
        return databaseType;
    }


    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }

    @XmlAttribute
    public String getDatabaseSchemaUpdate() {
        return databaseSchemaUpdate;
    }


    public void setDatabaseSchemaUpdate(String databaseSchemaUpdate) {
        this.databaseSchemaUpdate = databaseSchemaUpdate;
    }

    @XmlAttribute
    public String getDataSource() {
        return dataSource == null ? "" : dataSource.toString();
    }


    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @XmlAttribute
    public String getJdbcDriver() {
        return jdbcDriver;
    }


    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    @XmlAttribute
    public String getJdbcUrl() {
        return jdbcUrl;
    }


    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    @XmlAttribute
    public String getJdbcUsername() {
        return jdbcUsername;
    }


    public void setJdbcUsername(String jdbcUsername) {
        this.jdbcUsername = jdbcUsername;
    }

    @XmlAttribute
    public String getJdbcPassword() {
        return jdbcPassword;
    }


    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }

    @XmlAttribute
    public boolean isTransactionsExternallyManaged() {
        return transactionsExternallyManaged;
    }


    public void setTransactionsExternallyManaged(boolean transactionsExternallyManaged) {
        this.transactionsExternallyManaged = transactionsExternallyManaged;
    }

    @XmlAttribute
    public int getJdbcMaxActiveConnections() {
        return jdbcMaxActiveConnections;
    }


    public void setJdbcMaxActiveConnections(int jdbcMaxActiveConnections) {
        this.jdbcMaxActiveConnections = jdbcMaxActiveConnections;
    }

    @XmlAttribute
    public int getJdbcMaxIdleConnections() {
        return jdbcMaxIdleConnections;
    }


    public void setJdbcMaxIdleConnections(int jdbcMaxIdleConnections) {
        this.jdbcMaxIdleConnections = jdbcMaxIdleConnections;
    }

    @XmlAttribute
    public int getJdbcMaxCheckoutTime() {
        return jdbcMaxCheckoutTime;
    }


    public void setJdbcMaxCheckoutTime(int jdbcMaxCheckoutTime) {
        this.jdbcMaxCheckoutTime = jdbcMaxCheckoutTime;
    }

    @XmlAttribute
    public int getJdbcMaxWaitTime() {
        return jdbcMaxWaitTime;
    }

    public void setJdbcMaxWaitTime(int jdbcMaxWaitTime) {
        this.jdbcMaxWaitTime = jdbcMaxWaitTime;
    }

    @XmlAttribute
    public boolean isJdbcPingEnabled() {
        return jdbcPingEnabled;
    }

    public void setJdbcPingEnabled(boolean jdbcPingEnabled) {
        this.jdbcPingEnabled = jdbcPingEnabled;
    }

    @XmlAttribute
    public String getJdbcPingQuery() {
        return jdbcPingQuery;
    }

    public void setJdbcPingQuery(String jdbcPingQuery) {
        this.jdbcPingQuery = jdbcPingQuery;
    }

    @XmlAttribute
    public int getJdbcPingConnectionNotUsedFor() {
        return jdbcPingConnectionNotUsedFor;
    }

    public void setJdbcPingConnectionNotUsedFor(int jdbcPingNotUsedFor) {
        this.jdbcPingConnectionNotUsedFor = jdbcPingNotUsedFor;
    }

    @XmlAttribute
    public boolean isJobExecutorActivate() {
        return jobExecutorActivate;
    }

    public void setJobExecutorActivate(boolean jobExecutorActivate) {
        this.jobExecutorActivate = jobExecutorActivate;
    }

    @XmlAttribute
    public String getJpaEntityManagerFactory() {
        return jpaEntityManagerFactory == null ? "" : jpaEntityManagerFactory.toString();
    }

    public void setJpaEntityManagerFactory(Object jpaEntityManagerFactory) {
        this.jpaEntityManagerFactory = jpaEntityManagerFactory;
    }

    @XmlAttribute
    public boolean isJpaHandleTransaction() {
        return jpaHandleTransaction;
    }

    public void setJpaHandleTransaction(boolean jpaHandleTransaction) {
        this.jpaHandleTransaction = jpaHandleTransaction;
    }

    @XmlAttribute
    public boolean isJpaCloseEntityManager() {
        return jpaCloseEntityManager;
    }

    public void setJpaCloseEntityManager(boolean jpaCloseEntityManager) {
        this.jpaCloseEntityManager = jpaCloseEntityManager;
    }

    @XmlAttribute
    public String getJpaPersistenceUnitName() {
        return jpaPersistenceUnitName;
    }

    public void setJpaPersistenceUnitName(String jpaPersistenceUnitName) {
        this.jpaPersistenceUnitName = jpaPersistenceUnitName;
    }

    @XmlAttribute
    public String getDataSourceJndiName() {
        return dataSourceJndiName;
    }

    public void setDataSourceJndiName(String dataSourceJndiName) {
        this.dataSourceJndiName = dataSourceJndiName;
    }
}
