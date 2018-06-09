package com.jactravel.spring.ignitecache.store;

import com.jactravel.spring.domain.Contract;
import com.jactravel.spring.domain.PropertyRoomType;
import com.jactravel.spring.domain.idClass.ContractPK;
import org.apache.ignite.cache.store.CacheStoreAdapter;

import javax.cache.Cache;
import javax.cache.integration.CacheLoaderException;
import javax.cache.integration.CacheWriterException;
import java.io.Serializable;

public class PropertyRoomTypeCacheStore extends CacheStoreAdapter<Integer, PropertyRoomType> implements Serializable {

    @Override
    public PropertyRoomType load(Integer roomTypeId) throws CacheLoaderException {
        return null;
    }

    @Override
    public void write(Cache.Entry<? extends Integer, ? extends PropertyRoomType> entry) throws CacheWriterException {

    }

    @Override
    public void delete(Object o) throws CacheWriterException {

    }
}
