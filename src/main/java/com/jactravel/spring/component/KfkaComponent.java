package com.jactravel.spring.component;

import com.datastax.driver.core.LocalDate;
import com.google.protobuf.InvalidProtocolBufferException;
import com.jactravel.kafka.messages.Model;
import com.jactravel.spring.domain.BoardBasis;
import com.jactravel.spring.domain.Contract;
import com.jactravel.spring.domain.Property;
import com.jactravel.spring.domain.PropertyRoomType;
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
    IgniteCache<Integer, Property> propertyIgniteCache;
    @Autowired
    IgniteCache<Integer, PropertyRoomType> propertyRoomTypeIgniteCache;
    @Autowired
    IgniteCache<ContractPK, Contract> contractIgniteCache;

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
            contract.setCommissionOverride(contractProto.getCommissionOverride() == 1);
            contract.setCompleteStayOnly(contractProto.getIsCompleteStayOnly() == 1);
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
            contractIgniteCache.put(contract.getContractPK(), contract);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }


    @KafkaListener(topics = "${kafka.consumer.topic.name.property.room.type}")
    public void propertyRoomTypeReceive(byte[] payload) {
        try {
            Model.PropertyRoomTypeProto propertyRoomTypeProto = Model.PropertyRoomTypeProto.parseFrom(payload);
            PropertyRoomType propertyRoomType = new PropertyRoomType();
            propertyRoomType.setAutoPromoteChildren(propertyRoomTypeProto.getAutoPromoteChildren());
            propertyRoomType.setChildAgeTo(propertyRoomTypeProto.getChildAgeTo());
            propertyRoomType.setExtraBedTypeId(propertyRoomTypeProto.getExtraBedTypeId());
            propertyRoomType.setInfants(propertyRoomTypeProto.getInfants());
            propertyRoomType.setInfantsOccupancy(propertyRoomTypeProto.getInfantsOccupancy());
            propertyRoomType.setMaxAdults(propertyRoomTypeProto.getMaxAdults());
            propertyRoomType.setMaxAdultsWithChildren(propertyRoomTypeProto.getMaxAdultsWithChildren());
            propertyRoomType.setMaxChildren(propertyRoomTypeProto.getMaxChildren());
            propertyRoomType.setMaxOcc(propertyRoomTypeProto.getMaxOcc());
            propertyRoomType.setMealBasisId(propertyRoomTypeProto.getMealBasisId());
            propertyRoomType.setMinOcc(propertyRoomTypeProto.getMinOcc());
            propertyRoomType.setPropertyId(propertyRoomTypeProto.getPropertyId());
            propertyRoomType.setRoom(propertyRoomTypeProto.getRoom());
            propertyRoomType.setRoomType(propertyRoomTypeProto.getRoomType());
            propertyRoomType.setRoomTypeId(propertyRoomTypeProto.getRoomTypeId());
            propertyRoomType.setRoomView(propertyRoomTypeProto.getRoomView());
            propertyRoomType.setStdOcc(propertyRoomTypeProto.getStdOcc());
            propertyRoomType.setYouth(propertyRoomTypeProto.getYouth());
            propertyRoomType.setYouthAgeFrom(propertyRoomTypeProto.getYouthAgeFrom());
            propertyRoomType.setYouthAgeTo(propertyRoomTypeProto.getYouthAgeTo());
            propertyRoomType.setYouthsCountAsAdults(propertyRoomTypeProto.getYouthsCountAsAdults());
            propertyRoomType.setSync(false);
            propertyRoomTypeIgniteCache.put(propertyRoomType.getRoomTypeId(), propertyRoomType);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }


    @KafkaListener(topics = "${kafka.consumer.topic.name.property}")
    public void propertyReceive(byte[] payload) {
        try {
            Model.PropertyProto propertyProto = Model.PropertyProto.parseFrom(payload);
            Property property = new Property();
            property.setName(propertyProto.getName());
            property.setPropertyId(propertyProto.getPropertyId());
            property.setPropertyRating(propertyProto.getPropertyRating());
            property.setRoomType(propertyProto.getRoomType());
            property.setSync(false);
            propertyIgniteCache.put(property.getPropertyId(), property);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }
}
