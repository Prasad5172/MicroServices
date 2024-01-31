package com.prasad.school.client;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.prasad.school.Student;

@FeignClient(name = "students" )
// @RibbonClient(name = "students",configuration = RibbonConfig.class)
public interface StudentClient {
    @GetMapping(value = "/api/v1/students/school/{schoolId}")
    List<Student> findAllStudentsBySchoolId(@PathVariable("schoolId") Integer schoolId);
}
