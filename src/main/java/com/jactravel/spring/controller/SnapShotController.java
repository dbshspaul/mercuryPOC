package com.jactravel.spring.controller;

import com.jactravel.spring.domain.BoardBasis;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.ScanQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.cache.Cache;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SnapShotController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SnapShotController.class);

    @Autowired
    IgniteCache<Integer, BoardBasis> boardBasisIgniteCache;

    @GetMapping(value = "/sync", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<?> autoSyncTask() {
        LOGGER.info(">>>>>>>>>>>>>>>>Sync started ...");
        Map<String, String> response = new HashMap<>();


        try (QueryCursor<Cache.Entry<Integer, BoardBasis>> cursor = boardBasisIgniteCache.query(new ScanQuery<Integer, BoardBasis>((k, p) -> !p.isSync()))) {
            int i = 0;
            for (Cache.Entry<Integer, BoardBasis> p : cursor)
            {
                BoardBasis boardBasis = p.getValue();
                boardBasis.setSync(true);
                System.out.println("boardBasis = " + boardBasis);
                i++;
            }
            response.put("Contract", String.valueOf(i));
        }
        LOGGER.info("Sync complete with status "+response);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
}
