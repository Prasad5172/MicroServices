package com.prasad.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
   @Value("${server.port}")
   private Integer port;

    
   private final StudentService studentService;

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public void save(@RequestBody Student student){
        studentService.save(student);
   }

   @GetMapping
   public ResponseEntity<List<Student>> findAllStudents(){
    return ResponseEntity.ok(studentService.findAllStudents());
   }

   @GetMapping("/school/{schoolId}")
   public ResponseEntity<List<Student>> findAllStudentsBySchoolId(@PathVariable("schoolId") Integer schoolId){
      System.out.println("port "+ port);
    return ResponseEntity.ok(studentService.findAllStudentsBySchoolId(schoolId));
   }

    
}
