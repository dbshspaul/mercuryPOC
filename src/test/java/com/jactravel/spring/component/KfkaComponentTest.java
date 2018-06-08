package com.jactravel.spring.component;

import com.datastax.driver.core.LocalDate;
import com.jactravel.kafka.messages.Model;
import com.jactravel.spring.domain.BoardBasis;
import com.jactravel.spring.domain.Contract;
import com.jactravel.spring.domain.idClass.ContractPK;
import org.apache.ignite.IgniteCache;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

class KfkaComponentTest {
    @Autowired
    Sender sender;

    @Value("${kafka.consumer.topic.name.board.basis}")
    String topicNameBoardBasis;

    @Value("${kafka.consumer.topic.name.contract}")
    String topicContract;

    @Autowired
    IgniteCache<ContractPK, Contract> contractCache;
    @Autowired
    IgniteCache<Integer, BoardBasis> boardBasisIgniteCache;

    @Test
    void boardBasisReceive() {
        Model.BoardBasisProto boardBasis = Model.BoardBasisProto.newBuilder()
                .setMealBasisCode("C100")
                .setMealBasisId(100)
                .build();
        sender.send(topicNameBoardBasis, boardBasis.toByteArray());

        BoardBasis basis = new BoardBasis();
        basis.setMealBasisId(100);
        basis.setMealBasisCode("c100");
        basis.setSync(false);

        Assertions.assertEquals(basis, boardBasisIgniteCache.get(100));
    }

    @Test
    void contractReceive() {
        Model.ContractProto contractProto = Model.ContractProto.newBuilder()
                .setContractId(12)
                .setPropertyId(32)
                .setBookingEndDate("4575")
                .setBookingStartDate("4577")
                .setCommissionOverride(1)
                .setIsCompleteStayOnly(1)
                .setContractBasis("hg")
                .setContractType("")
                .setCurrencyId(54)
                .setCurrencyType("sadasd")
                .setPriceType("efds")
                .setSourcePricePriority("dsfds")
                .setStayEndDate("54123")
                .setStayStartDate("45824")
                .setSuppliedBy("tyfh")
                .setSupplierId(43535)
                .build();
        sender.send(topicContract, contractProto.toByteArray());

        Contract contract = new Contract();
        ContractPK contractPK = new ContractPK();
        contractPK.setContract_id(12);
        contractPK.setProperty_id(32);
        contract.setContractPK(contractPK);

        contract.setBookingEndSate(LocalDate.fromDaysSinceEpoch(4575));
        contract.setBookingStartSate(LocalDate.fromDaysSinceEpoch(4577));
        contract.setCommissionOverride(true);
        contract.setCompleteStayOnly(true);
        contract.setContractBasis("hg");
        contract.setContractType("");
        contract.setCurrencyId(54);
        contract.setCurrencyType("sadasd");
        contract.setPriceType("efds");
        contract.setSourcePricePriority("dsfds");
        contract.setStayEndDate(LocalDate.fromDaysSinceEpoch(54123));
        contract.setStayStartSate(LocalDate.fromDaysSinceEpoch(45824));
        contract.setSuppliedBy("tyfh");
        contract.setSupplierId(43535);
        contract.setSync(false);

        Assertions.assertEquals(contract, contractCache.get(contractPK));
    }

}