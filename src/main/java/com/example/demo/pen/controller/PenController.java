package com.example.demo.pen.controller;

import com.example.demo.pen.entity.PenEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/pen")
public class PenController {

    private final Logger log = LoggerFactory.getLogger(PenController.class);

    PenEntity penEntity1 = new PenEntity("1", "pen1");
    PenEntity penEntity2 = new PenEntity("2", "pen2");
    PenEntity penEntity3 = new PenEntity("3", "pen3");

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getAllBooks() {
        log.debug("REST request to get List of Books");
        List<PenEntity> penEntity = new ArrayList<PenEntity>();
        penEntity.add(penEntity1);
        penEntity.add(penEntity2);
        penEntity.add(penEntity3);
        Map<String, Object> response = new HashMap<>();
        response.put("data", penEntity);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PenEntity> getBookById(@PathVariable String id) {
        log.debug("REST request to Pen by oid : {}", id);
        List<PenEntity> penEntity = new ArrayList<PenEntity>();
        penEntity.add(penEntity1);
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        return new ResponseEntity(penEntity, HttpStatus.OK);
    }
}
