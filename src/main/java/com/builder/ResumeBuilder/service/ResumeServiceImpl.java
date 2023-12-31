package com.builder.ResumeBuilder.service;

import com.builder.ResumeBuilder.dao.Resume;
import com.builder.ResumeBuilder.repository.ResumeRepository;
import com.builder.ResumeBuilder.util.PdfGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.AttributeNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ResumeServiceImpl implements ResumeService{

    @Autowired
    ResumeRepository resumeRepository;

    @Override
    public Resume saveResume(Resume resume) {
        log.info("Saving Resume to DB");
        return resumeRepository.save(resume);
    }

    @Override
    public Optional<Resume> getResume(String userId) {
        return resumeRepository.findById(userId);
    }

    @Override
    public List<Resume> getAllResume() {return resumeRepository.findAll();}

    @Override
    public void deleteAllResume() {
        resumeRepository.deleteAll();
    }

    @Override
    public void generateResume(String userId,  HttpServletResponse response) throws IOException {
        Optional<Resume> resumeOptional=getResume(userId);

        if (!resumeOptional.isPresent()){
            log.info("Resume not found for userId: "+userId);
            throw new RuntimeException("Resume not found for userId: "+userId);
        }


        Resume resume=resumeOptional.get();

        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename="+resume.getResumeHeader().getTitle() + currentDateTime + ".pdf";
        response.setHeader(headerkey, headervalue);

        PdfGenerator.generate(resume,response);

    }


}
