package com.prasad.school;

import java.util.List;

import org.springframework.stereotype.Service;
// import org.springframework.web.client.RestTemplate;
// import org.springframework.web.reactive.function.client.WebClient;

import com.prasad.school.client.StudentClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;
    private final StudentClient client;
    // private final RestTemplate restTemplate;
    // private final WebClient.Builder webclient;


    public void save(School school) {
        schoolRepository.save(school);
    }

    public List<School> findAllStudents() {
        return schoolRepository.findAll();
    }
    
    public FullSchoolResponce findSchoolDetailsById(Integer schoolId) {
        System.out.println(schoolId);
        var school = schoolRepository.findById(schoolId)
                .orElse(School.builder()
                .name("NOT_FOUND")
                .email("NOT_FOUND")
                .build());
        var students = client.findAllStudentsBySchoolId(schoolId);
        // By using RestTemplate
        // var students1 = restTemplate.getForObject("http://localhost:8222/api/v1/students/school/"+schoolId,  List.class);

        //  var students2 = webclient.build()
        //         .get()
        //         .uri("http://localhost:8222/api/v1/students/school/"+schoolId)
        //         .retrieve()
        //         .bodyToMono(List.class)
        //         .block();
        System.out.println("sent the responce");
        return FullSchoolResponce
                .builder()
                .name(school.getName())
                .email(school.getEmail())
                .students(students)
                // .students(students1)
                .build();
    }

  
}
