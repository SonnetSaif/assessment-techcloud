package com.example.demo.book.controller;

import com.example.demo.book.entity.BookEntity;
import org.apache.tomcat.util.http.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/v1/book")
public class BookController {

    private final Logger log = LoggerFactory.getLogger(BookController.class);

    BookEntity bookEntity1 = new BookEntity("1", "abcd");
    BookEntity bookEntity2 = new BookEntity("2", "xyz");
    BookEntity bookEntity3 = new BookEntity("3", "qert");

    @GetMapping("")
    public ResponseEntity<List<BookEntity>> getAllBooks() {
        log.debug("REST request to get List of Books");
        List<BookEntity> bookEntity = new ArrayList<BookEntity>();
        bookEntity.add(bookEntity1);
        bookEntity.add(bookEntity2);
        bookEntity.add(bookEntity3);
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        return new ResponseEntity(bookEntity, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookEntity> getBookById(@PathVariable String id) {
        log.debug("REST request to Book by oid : {}", id);
        List<BookEntity> bookEntity = new ArrayList<BookEntity>();
        bookEntity.add(bookEntity1);
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        return new ResponseEntity(bookEntity, HttpStatus.OK);
    }
}
