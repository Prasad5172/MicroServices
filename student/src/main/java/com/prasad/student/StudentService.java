package com.prasad.student;

import java.util.List;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    
    public void save(Student student){
        studentRepository.save(student);
    }
    public List<Student> findAllStudents(){
        return studentRepository.findAll();
    }
    public List<Student> findAllStudentsBySchoolId(Integer schoolId){
        return studentRepository.findBySchoolId(schoolId);
    }
}
