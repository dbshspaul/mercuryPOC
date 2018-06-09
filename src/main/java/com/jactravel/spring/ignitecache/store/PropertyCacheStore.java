package com.jactravel.spring.ignitecache.store;

import com.jactravel.spring.domain.BoardBasis;
import com.jactravel.spring.domain.Property;
import org.apache.ignite.cache.store.CacheStoreAdapter;

import javax.cache.Cache;
import javax.cache.integration.CacheLoaderException;
import javax.cache.integration.CacheWriterException;
import java.io.Serializable;

public class PropertyCacheStore extends CacheStoreAdapter<Integer, Property> implements Serializable {

    @Override
    public Property load(Integer propertyId) throws CacheLoaderException {
        return null;
    }

    @Override
    public void write(Cache.Entry<? extends Integer, ? extends Property> entry) throws CacheWriterException {

    }

    @Override
    public void delete(Object o) throws CacheWriterException {

    }
}
