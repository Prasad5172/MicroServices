package com.prasad.school;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/schools")
@RequiredArgsConstructor
@RefreshScope // used for refreshing message.school from properties file without stopping the server using localhost:port/acutator/refresh end point it refreshes the scope
public class SchoolController {

   private final SchoolService schoolService;
   private Logger logger = LoggerFactory.getLogger(SchoolController.class);

   @Value("${message.school}")
   private String msg;
   @Value("${server.port}")
   private String port;

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public void saveSchool(@RequestBody School school) {
      schoolService.save(school);
   }

   @GetMapping
   public ResponseEntity<List<School>> findAllSchools() {
      return ResponseEntity.ok(schoolService.findAllStudents());
   }
   @GetMapping("/test")
   public String test() {
      return msg;
   }

   @GetMapping("/with-students/{schoolId}")
   @CircuitBreaker(name = "studentBreaker", fallbackMethod = "studentFallbackMethod")
   public ResponseEntity<FullSchoolResponce> findSchoolDetailsById(@PathVariable("schoolId") Integer schoolId) {
      System.out.println("port : "+ port);
      return ResponseEntity.ok(schoolService.findSchoolDetailsById(schoolId));
   }
   

   public ResponseEntity<FullSchoolResponce> studentFallbackMethod(Integer schoolId,Exception ex){
      // logger.info("Fallback is executed because service is down : ", ex.getMessage());
      // ex.printStackTrace();
            System.out.println("port : "+ port);

      return ResponseEntity.ok(FullSchoolResponce.builder()
        .name("not found")
        .email("not found")
        .students(Arrays.asList(Student.builder()
                .email("xyz")
                .firstname("xyz")
                .lastname("xyz")
                .build())
         ).build());
   }

   @GetMapping("/message")
   public String message() {
      return msg;
   }
}
