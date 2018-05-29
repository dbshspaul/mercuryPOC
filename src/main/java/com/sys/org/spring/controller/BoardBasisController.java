package com.sys.org.spring.controller;

import com.sys.org.protobuf.Model;
import com.sys.org.spring.component.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

@org.springframework.web.bind.annotation.RestController
public class BoardBasisController {

    @Autowired
    Sender sender;

    @Value("${kafka.consumer.topic.name}")
    String topicName;

    @GetMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    @GetMapping("/test")
    @ResponseBody
    ResponseEntity test() {
        MultiValueMap<String, String> header = new HttpHeaders();
        header.put("schema", Arrays.asList("BoardBasisProto"));
        header.put("Content-Type", Arrays.asList("application/x-protobuf"));
        header.put("format", Arrays.asList("binary"));
        Model.BoardBasisProto boardBasis = Model.BoardBasisProto.newBuilder()
                .setMealBasisCode("C100")
                .setMealBasisId(100)
                .build();

        sender.send(topicName, boardBasis.toString());
        return new ResponseEntity(boardBasis, header, HttpStatus.FOUND);
    }
}
