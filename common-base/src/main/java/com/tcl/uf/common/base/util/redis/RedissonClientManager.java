package com.tcl.uf.common.base.util.redis;

import jodd.util.StringUtil;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author dengyuanheng
 */
public class RedissonClientManager {
    private static final Logger logger = LoggerFactory.getLogger(RedissonClientManager.class);
    private static RedissonClient client;

    public static RedissonClient initSingleServer(String host, int dbIndex, String pw, int threads, int nettyThreads) {
        logger.info("Load RedissonClientManager Config. host:{},dbIndex:{},pw:{},threads:{},nettyThreads:{}",
                host, dbIndex, pw, threads, nettyThreads);
        Config config = new Config();
        SingleServerConfig singleServer = config.useSingleServer();
        singleServer.setAddress("redis://" + host);
        singleServer.setDatabase(dbIndex);
        if (!StringUtil.isEmpty(pw)) {
            singleServer.setPassword(pw);
        }

        if (threads > 0) {
            config.setThreads(threads);
        }

        if (nettyThreads > 0) {
            config.setNettyThreads(nettyThreads);
        }

        client = Redisson.create(config);
        return client;
    }

    public static RedissonClient initSentinelServers(String hosts, String masterName, String pw, int threads, int nettyThreads) {
        logger.info("Load RedissonClientManager Config. host:{},masterName:{},pw:{},threads:{},nettyThreads:{}",
                hosts, masterName, pw, threads, nettyThreads);
        Config config = new Config();
        SentinelServersConfig serversConfig = config.useSentinelServers();
        serversConfig.setMasterName(masterName);
        String[] hostsStr = hosts.split(",");
        String[] addressList = new String[hostsStr.length];
        for (int i = 0; i < hostsStr.length; i++) {
            addressList[i] = "redis://" + hostsStr[i];
        }
        serversConfig.addSentinelAddress(addressList);
        if (!StringUtil.isEmpty(pw)) {
            serversConfig.setPassword(pw);
        }

        if (threads > 0) {
            config.setThreads(threads);
        }

        if (nettyThreads > 0) {
            config.setNettyThreads(nettyThreads);
        }

        client = Redisson.create(config);
        return client;
    }

    public static RedissonClient getClient() {
        if (null == client) {
            throw new IllegalStateException("RedissonClientManager uninitialized");
        }

        return client;
    }
}
