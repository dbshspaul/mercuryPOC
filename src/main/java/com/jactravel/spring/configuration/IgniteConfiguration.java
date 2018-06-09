package com.jactravel.spring.configuration;

import com.jactravel.spring.domain.BoardBasis;
import com.jactravel.spring.domain.Contract;
import com.jactravel.spring.domain.Property;
import com.jactravel.spring.domain.PropertyRoomType;
import com.jactravel.spring.domain.idClass.ContractPK;
import com.jactravel.spring.ignitecache.store.BoardBasisCacheStore;
import com.jactravel.spring.ignitecache.store.ContractCacheStore;
import com.jactravel.spring.ignitecache.store.PropertyCacheStore;
import com.jactravel.spring.ignitecache.store.PropertyRoomTypeCacheStore;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.DataStorageConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

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
        org.apache.ignite.configuration.IgniteConfiguration cfg = new org.apache.ignite.configuration.IgniteConfiguration();

        DataStorageConfiguration storageCfg = new DataStorageConfiguration();
        storageCfg.getDefaultDataRegionConfiguration().setPersistenceEnabled(true);
        cfg.setDataStorageConfiguration(storageCfg);

        TcpDiscoverySpi tcpDiscoverySpi = new TcpDiscoverySpi();
        TcpDiscoveryMulticastIpFinder ipFinder = new TcpDiscoveryMulticastIpFinder();
        ipFinder.setAddresses(Arrays.asList("127.0.0.1:47500..47502"));
        tcpDiscoverySpi.setIpFinder(ipFinder);
        cfg.setDiscoverySpi(tcpDiscoverySpi);

        Ignite ignite = Ignition.start(cfg);
        ignite.active(true);
        LOGGER.info(">>>>>>>>>>>>>>>>Ignite  Persistent Store Node Started successfully");
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
        LOGGER.info("creating boardBasisCache Cache");
        return createCache("boardBasisCache", BoardBasisCacheStore.class);
    }

    @Bean(name = "propertyCache")
    @Lazy
    public IgniteCache<Integer, Property> getPropertyCache() {
        LOGGER.info("creating propertyCache Cache");
        return createCache("propertyCache", PropertyCacheStore.class);
    }

    @Bean(name = "boardBasisCache")
    @Lazy
    public IgniteCache<Integer, PropertyRoomType> getPropertyRoomTypeCache() {
        LOGGER.info("creating boardBasisCache Cache");
        return createCache("boardBasisCache", PropertyRoomTypeCacheStore.class);
    }

    private IgniteCache createCache(String cacheName, Class clazz) {
        CacheConfiguration contractCacheConfig = new CacheConfiguration();
        contractCacheConfig.setName(cacheName);
//        contractCacheConfig.setReadThrough(true);
//        contractCacheConfig.setWriteThrough(true);
//        contractCacheConfig.setWriteBehindEnabled(true);
//        contractCacheConfig.setBackups(1);
        contractCacheConfig.setCacheMode(CacheMode.PARTITIONED);
        contractCacheConfig.setCacheStoreFactory(FactoryBuilder.factoryOf(clazz));
        IgniteCache cache = ignite.getOrCreateCache(contractCacheConfig);
        LOGGER.info(cacheName + " created.");
        return cache;
    }
}
