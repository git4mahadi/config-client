package com.edu.configclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RefreshScope
@RestController
@RequestMapping("/rest")
public class MessageResource {

    @Value("${message: Salam Mahadi}")
    private String message;
    @Value("${server.port}")
    private Integer port;
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/message")
    public String getMessage() {
        return message;
    }

    @PostMapping("/person")
    public Person savePerson() {
        return personRepository.save(new Person("mahadi",30));
    }


    @GetMapping("/persons")
    public List<Person> getPersonList() {
        return personRepository.findAll().stream()
                .map(o-> {
                    o.setPort(this.port);
                    return o;
                }).collect(Collectors.toList());
    }
}
