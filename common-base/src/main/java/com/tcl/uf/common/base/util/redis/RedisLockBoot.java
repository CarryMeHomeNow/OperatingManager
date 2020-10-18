package com.tcl.uf.common.base.util.redis;

/**
 * @author dengyuanheng
 */
public class RedisLockBoot {

    private String host = "localhost";

    private String masterName = "master1";
    private int dbIndex = 0;

    private String pw;

    private int threads = -1;

    private int nettyThreads = -1;

    public void initSingleServer() {
        RedissonClientManager.initSingleServer(host, dbIndex, pw, threads, nettyThreads);
    }

    public void initSentinelServers() {
        RedissonClientManager.initSentinelServers(host, masterName, pw, threads, nettyThreads);
    }

    public void destroy() {
        RedissonClientManager.getClient().shutdown();
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setDbIndex(int dbIndex) {
        this.dbIndex = dbIndex;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    public void setNettyThreads(int nettyThreads) {
        this.nettyThreads = nettyThreads;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }
}
