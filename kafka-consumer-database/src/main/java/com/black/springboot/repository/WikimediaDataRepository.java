package com.black.springboot.repository;

import com.black.springboot.entity.WikimediaData;
import org.springframework.data.jpa.repository.JpaRepository;

//Performs database operations.
public interface WikimediaDataRepository extends JpaRepository<WikimediaData, Long> {

}
