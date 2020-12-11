package com.example.demo;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(LessonsController.class)
public class LessonsControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    LessonRepository repository;

    @Test
    @Transactional
    @Rollback
    public void testGetLessons() throws Exception {
//        setup the database first
        Lesson lesson1 = new Lesson();
        lesson1.setTitle("something");
        Date date = new Date();
        lesson1.setDeliveredOn(date);
        repository.save(lesson1);

        Lesson lesson2 = new Lesson();
        lesson2.setTitle("something");
        Date date2 = new Date();
        lesson2.setDeliveredOn(date);
        repository.save(lesson2);

        MockHttpServletRequestBuilder request = get("/lessons")
                .contentType(MediaType.APPLICATION_JSON);
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", instanceOf(Number.class) ));
    }
    @Test
    @Transactional
    @Rollback
    public void testPostLesson() throws Exception {
        MockHttpServletRequestBuilder request = post("/lessons")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"testingNow\"}");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", instanceOf(Number.class) ));

//notice the id is 3 even though we rolled back the db
        MockHttpServletRequestBuilder request2 = get("/lessons")
                .contentType(MediaType.APPLICATION_JSON);
        this.mvc.perform(request2)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", instanceOf(Number.class) ));
    }
    @Test
    @Transactional
    @Rollback
    public void testPatchLesson() throws Exception {
        Lesson lesson1 = new Lesson();
        lesson1.setTitle("zzz");
        Date date = new Date();
        lesson1.setDeliveredOn(date);
        repository.save(lesson1);


//        MockHttpServletRequestBuilder request2 = get("/lessons")
//                .contentType(MediaType.APPLICATION_JSON);
//        this.mvc.perform(request2)
//                .andExpect(status().isOk())
//                .andExpect(content().string("show me the id"));


        MockHttpServletRequestBuilder request = patch("/lessons/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"testingNow\"}");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"title\":\"testingNow\",\"deliveredOn\":null}"));
    }
    @Test
    @Transactional
    @Rollback
    public void testFindByTitle() throws Exception {
        Lesson lesson1 = new Lesson();
        lesson1.setTitle("NewBook");
        Date date = new Date();
        lesson1.setDeliveredOn(date);
        repository.save(lesson1);

        this.mvc.perform(get("/lessons/find/NewBook"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title" , equalTo(lesson1.getTitle())));
    }
    @Test
    @Transactional
    @Rollback
    public void testFindBetweenID() throws Exception {
        Lesson lesson1 = new Lesson();
        lesson1.setTitle("something");
        Date date = new Date();
        lesson1.setDeliveredOn(date);
        repository.save(lesson1);

        Lesson lesson2 = new Lesson();
        lesson2.setTitle("somethingElse");
        Date date2 = new Date();
        lesson2.setDeliveredOn(date);
        repository.save(lesson2);

        this.mvc.perform(get("/lessons/find/between?id1=1&id2=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", equalTo(lesson1.getTitle())));
    }


}


//    Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException:
//    Error creating bean with name 'org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaConfiguration':
//    Unsatisfied dependency expressed through constructor parameter 0; nested exception is org.springframework.beans.factory.BeanCreationException:
//    Error creating bean with name 'dataSource' defined in class path resource [org/springframework/boot/autoconfigure/jdbc/DataSourceConfiguration$Hikari.class]:
//    Bean instantiation via factory method failed; nested exception is org.springframework.beans.BeanInstantiationException: Failed to instantiate
//    [com.zaxxer.hikari.HikariDataSource]: Factory method 'dataSource' threw exception; nested exception is java.lang.IllegalStateException:
//    Cannot load driver class: com.mysql.jdbc.Driver
