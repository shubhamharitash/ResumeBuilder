package com.builder.ResumeBuilder.repository;

import com.builder.ResumeBuilder.dao.Resume;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends MongoRepository<Resume,String> {

}
