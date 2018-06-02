package com.jactravel.spring.configuration;

import com.jactravel.spring.domain.BoardBasis;
import com.jactravel.spring.domain.Contract;
import com.jactravel.spring.domain.idClass.ContractPK;
import com.jactravel.spring.ignitecache.store.BoardBasisCacheStore;
import com.jactravel.spring.ignitecache.store.ContractCacheStore;
import org.apache.ignite.*;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.DataRegionConfiguration;
import org.apache.ignite.configuration.DataStorageConfiguration;
import org.apache.ignite.logger.java.JavaLogger;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.cache.configuration.FactoryBuilder;
import java.util.Arrays;

@Configuration
public class IgniteConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(IgniteConfiguration.class);
    @Autowired
    Ignite ignite;

    @Bean(name = "ignite")
    @Lazy
    public Ignite getIgnite() {
        Ignition.setClientMode(true);
        org.apache.ignite.configuration.IgniteConfiguration igniteConfiguration = new org.apache.ignite.configuration.IgniteConfiguration();

        DataStorageConfiguration storageCfg = new DataStorageConfiguration();
        DataRegionConfiguration dataRegionConfiguration = new DataRegionConfiguration();
        dataRegionConfiguration.setName("Default_Region");
        dataRegionConfiguration.setMaxSize(4L * 1024 * 1024 * 1024);
        dataRegionConfiguration.setPersistenceEnabled(true);
        storageCfg.setDataRegionConfigurations(dataRegionConfiguration);
        igniteConfiguration.setDataStorageConfiguration(storageCfg);


        TcpDiscoverySpi tcpDiscoverySpi = new TcpDiscoverySpi();
        TcpDiscoveryMulticastIpFinder ipFinder = new TcpDiscoveryMulticastIpFinder();
        ipFinder.setAddresses(Arrays.asList("127.0.0.1:47500..47502"));
        tcpDiscoverySpi.setIpFinder(ipFinder);
        igniteConfiguration.setDiscoverySpi(tcpDiscoverySpi);

        IgniteLogger log = new JavaLogger(true);
        igniteConfiguration.setGridLogger(log);
        Ignite ignite = Ignition.start();
        IgniteCluster cluster = ignite.cluster();
        cluster.active();
        LOGGER.info(">>>>>>>>>>>>>>>>Ignite Cache connected successfully");
        return ignite;
    }

    @Bean(name = "contractCache")
    @Lazy
    public IgniteCache<ContractPK, Contract> getContractCache() {
        LOGGER.info("creating contract Cache");
        return createCache("contractCache", ContractCacheStore.class);
    }

    @Bean(name = "boardBasisCache")
    @Lazy
    public IgniteCache<Integer, BoardBasis> getBoardBasisCache() {
        LOGGER.info("creating contract Cache");
        return createCache("boardBasisCache", BoardBasisCacheStore.class);
    }

    private IgniteCache createCache(String cacheName, Class clazz) {
        CacheConfiguration contractCacheConfig = new CacheConfiguration();
        contractCacheConfig.setName(cacheName);
//        contractCacheConfig.setReadThrough(true);
//        contractCacheConfig.setWriteThrough(true);
//        contractCacheConfig.setWriteBehindEnabled(true);
        contractCacheConfig.setBackups(1);
        contractCacheConfig.setCacheMode(CacheMode.PARTITIONED);
        contractCacheConfig.setCacheStoreFactory(FactoryBuilder.factoryOf(clazz));
        IgniteCache cache = ignite.getOrCreateCache(contractCacheConfig);
        LOGGER.info(cacheName + " created.");
        return cache;
    }
}
