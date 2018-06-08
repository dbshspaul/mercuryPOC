package com.jactravel.spring.component;

import com.datastax.driver.core.LocalDate;
import com.google.protobuf.InvalidProtocolBufferException;
import com.jactravel.kafka.messages.Model;
import com.jactravel.spring.domain.BoardBasis;
import com.jactravel.spring.domain.Contract;
import com.jactravel.spring.domain.idClass.ContractPK;
import org.apache.ignite.IgniteCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KfkaComponent {

    @Autowired
    IgniteCache<Integer, BoardBasis> boardBasisIgniteCache;

    @Autowired
    IgniteCache<ContractPK, Contract> contractCache;

    @KafkaListener(topics = "${kafka.consumer.topic.name.board.basis}")
    public void boardBasisReceive(byte[] payload) {
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

    @KafkaListener(topics = "${kafka.consumer.topic.name.contract}")
    public void contractReceive(byte[] payload) {
        try {
            Model.ContractProto contractProto = Model.ContractProto.parseFrom(payload);
            Contract contract = new Contract();
            ContractPK contractPK = new ContractPK();
            contractPK.setContract_id(contractProto.getContractId());
            contractPK.setProperty_id(contractProto.getPropertyId());
            contract.setBookingEndSate(LocalDate.fromDaysSinceEpoch(Integer.parseInt(contractProto.getBookingEndDate())));
            contract.setContractPK(contractPK);
            contract.setBookingStartSate(LocalDate.fromDaysSinceEpoch(Integer.parseInt(contractProto.getBookingStartDate())));
            contract.setCommissionOverride(contractProto.getCommissionOverride()==1);
            contract.setCompleteStayOnly(contractProto.getIsCompleteStayOnly()==1);
            contract.setContractBasis(contractProto.getContractBasis());
            contract.setContractType(contractProto.getContractType());
            contract.setCurrencyId(contractProto.getCurrencyId());
            contract.setCurrencyType(contractProto.getCurrencyType());
            contract.setPriceType(contractProto.getPriceType());
            contract.setSourcePricePriority(contractProto.getSourcePricePriority());
            contract.setStayEndDate(LocalDate.fromDaysSinceEpoch(Integer.parseInt(contractProto.getStayEndDate())));
            contract.setStayStartSate(LocalDate.fromDaysSinceEpoch(Integer.parseInt(contractProto.getStayStartDate())));
            contract.setSuppliedBy(contractProto.getSuppliedBy());
            contract.setSupplierId(contractProto.getSupplierId());
            contract.setSync(false);
            contractCache.put(contract.getContractPK(), contract);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }

}
