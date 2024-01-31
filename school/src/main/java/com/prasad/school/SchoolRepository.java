package com.prasad.school;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface SchoolRepository extends JpaRepository<School,Integer> {
    List<School> findById(int id);
}
