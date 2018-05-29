package com.sys.org.spring.component;

import com.sys.org.protobuf.Model;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KfkaComponent {

    @KafkaListener(topics = "${kafka.consumer.topic.name}")
    public void receive(Model.BoardBasisProto payload) {
        System.out.println(payload);
    }

}
