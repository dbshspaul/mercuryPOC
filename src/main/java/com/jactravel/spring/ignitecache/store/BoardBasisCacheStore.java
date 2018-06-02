package com.jactravel.spring.ignitecache.store;

import com.jactravel.spring.domain.BoardBasis;
import org.apache.ignite.cache.store.CacheStoreAdapter;

import javax.cache.Cache;
import javax.cache.integration.CacheLoaderException;
import javax.cache.integration.CacheWriterException;
import java.io.Serializable;

public class BoardBasisCacheStore extends CacheStoreAdapter<Integer, BoardBasis> implements Serializable {
    @Override
    public BoardBasis load(Integer integer) throws CacheLoaderException {
        return null;
    }

    @Override
    public void write(Cache.Entry<? extends Integer, ? extends BoardBasis> entry) throws CacheWriterException {

    }

    @Override
    public void delete(Object o) throws CacheWriterException {

    }
}
