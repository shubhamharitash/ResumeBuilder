package com.builder.ResumeBuilder.service;

import com.builder.ResumeBuilder.dao.Resume;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ResumeService {
    Resume saveResume(Resume resume);
    Optional<Resume> getResume(String userId);
    List<Resume> getAllResume();
    void deleteAllResume();
    void generateResume(String userId, HttpServletResponse response) throws IOException;
}
