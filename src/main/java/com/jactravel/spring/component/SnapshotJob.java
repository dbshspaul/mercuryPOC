package com.jactravel.spring.component;

import com.jactravel.spring.domain.BoardBasis;
import com.jactravel.spring.domain.Contract;
import com.jactravel.spring.domain.Property;
import com.jactravel.spring.domain.PropertyRoomType;
import com.jactravel.spring.domain.idClass.ContractPK;
import com.jactravel.spring.repositories.BoardBasisRepository;
import com.jactravel.spring.repositories.ContractRepository;
import com.jactravel.spring.repositories.PropertyRepository;
import com.jactravel.spring.repositories.PropertyRoomTypeRepository;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.ScanQuery;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.cache.Cache;
import java.util.HashMap;
import java.util.Map;

@Component
public class SnapshotJob implements Job {
    private static final Logger LOGGER = LoggerFactory.getLogger(SnapshotJob.class);

    @Autowired
    IgniteCache<Integer, BoardBasis> boardBasisIgniteCache;
    @Autowired
    IgniteCache<Integer, Property> propertyIgniteCache;
    @Autowired
    IgniteCache<Integer, PropertyRoomType> propertyRoomTypeIgniteCache;
    @Autowired
    IgniteCache<ContractPK, Contract> contractIgniteCache;

    @Autowired
    BoardBasisRepository boardBasisRepository;
    @Autowired
    ContractRepository contractRepository;
    @Autowired
    PropertyRepository propertyRepository;
    @Autowired
    PropertyRoomTypeRepository propertyRoomTypeRepository;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOGGER.info(">>>>>>>>>>>>>>>>Sync started ...");
        Map<String, String> response = new HashMap<>();


        try (QueryCursor<Cache.Entry<Integer, BoardBasis>> cursor = boardBasisIgniteCache.query(new ScanQuery<Integer, BoardBasis>((k, p) -> !p.isSync()))) {
            int i = 0;
            for (Cache.Entry<Integer, BoardBasis> p : cursor) {
                BoardBasis boardBasis = p.getValue();
                boardBasisRepository.save(boardBasis);
                boardBasis.setSync(true);
                boardBasisIgniteCache.put(boardBasis.getMealBasisId(), boardBasis);
                i++;
            }
            response.put("BoardBasis", String.valueOf(i));
        }

        try (QueryCursor<Cache.Entry<ContractPK, Contract>> cursor = contractIgniteCache.query(new ScanQuery<ContractPK, Contract>((k, p) -> !p.isSync()))) {
            int i = 0;
            for (Cache.Entry<ContractPK, Contract> p : cursor) {
                Contract contract = p.getValue();
                contractRepository.save(contract);
                contract.setSync(true);
                contractIgniteCache.put(contract.getContractPK(), contract);
                i++;
            }
            response.put("Contract", String.valueOf(i));
        }

        try (QueryCursor<Cache.Entry<Integer, Property>> cursor = propertyIgniteCache.query(new ScanQuery<Integer, Property>((k, p) -> !p.isSync()))) {
            int i = 0;
            for (Cache.Entry<Integer, Property> p : cursor) {
                Property property = p.getValue();
                propertyRepository.save(property);
                property.setSync(true);
                propertyIgniteCache.put(property.getPropertyId(), property);
                i++;
            }
            response.put("Property", String.valueOf(i));
        }

        try (QueryCursor<Cache.Entry<Integer, PropertyRoomType>> cursor = propertyRoomTypeIgniteCache.query(new ScanQuery<Integer, PropertyRoomType>((k, p) -> !p.isSync()))) {
            int i = 0;
            for (Cache.Entry<Integer, PropertyRoomType> p : cursor) {
                PropertyRoomType propertyRoomType = p.getValue();
                propertyRoomTypeRepository.save(propertyRoomType);
                propertyRoomType.setSync(true);
                propertyRoomTypeIgniteCache.put(propertyRoomType.getRoomTypeId(), propertyRoomType);
                i++;
            }
            response.put("property Room Type", String.valueOf(i));
        }


        LOGGER.info("Sync complete with status " + response);
    }
}
