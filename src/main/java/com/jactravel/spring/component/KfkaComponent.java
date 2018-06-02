package com.jactravel.spring.component;

import com.google.protobuf.InvalidProtocolBufferException;
import com.jactravel.kafka.messages.Model;
import com.jactravel.spring.domain.BoardBasis;
import org.apache.ignite.IgniteCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KfkaComponent {

    @Autowired
    IgniteCache<Integer, BoardBasis> boardBasisIgniteCache;

    @KafkaListener(topics = "${kafka.consumer.topic.name.board.basis}")
    public void receive(byte[] payload) {
        try {
            Model.BoardBasisProto boardBasisProto = Model.BoardBasisProto.parseFrom(payload);
            BoardBasis boardBasis = new BoardBasis();
            boardBasis.setMealBasisCode(boardBasisProto.getMealBasisCode());
            boardBasis.setMealBasisId(boardBasisProto.getMealBasisId());
            boardBasis.setSync(false);
            boardBasisIgniteCache.put(boardBasis.getMealBasisId(), boardBasis);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }

}
