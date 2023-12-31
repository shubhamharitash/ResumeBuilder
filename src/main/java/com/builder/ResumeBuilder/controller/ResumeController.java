package com.builder.ResumeBuilder.controller;

import com.builder.ResumeBuilder.dao.Resume;
import com.builder.ResumeBuilder.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ResumeController {

    @Autowired
    ResumeService resumeService;

    @GetMapping("/fetch/resume")
    public ResponseEntity<Resume> getResume(@RequestParam String userId){
       Optional<Resume> resume= resumeService.getResume(userId);

       if (resume.isPresent())
       return ResponseEntity.ok().body(resume.get());
       else
           return ResponseEntity.notFound().build();
    }
    @GetMapping("/fetch/allResume")
    public ResponseEntity<List<Resume>> getAllResume(){
        List<Resume> resume= resumeService.getAllResume();

        if (!resume.isEmpty())
            return ResponseEntity.ok().body(resume);
        else
            return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/allResume")
    public void deleteAllResume(){
        resumeService.deleteAllResume();
    }

    @GetMapping("/generate/resume")
    public ResponseEntity<?> generateResume(String userId, HttpServletResponse response) throws IOException {
        resumeService.generateResume(userId,response);
       return ResponseEntity.ok().build();
    }

    @PostMapping("/add/resume")
    public  ResponseEntity<Resume> addResume(@RequestBody Resume resume){
       return ResponseEntity.ok().body(resumeService.saveResume(resume));
    }
}
