package com.coursera.spring.repository;

import com.coursera.spring.web.model.Compositions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompositionsRepository extends JpaRepository<Compositions, Long> {
}
