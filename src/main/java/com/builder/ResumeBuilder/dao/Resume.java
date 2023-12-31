package com.builder.ResumeBuilder.dao;

import com.builder.ResumeBuilder.dto.ResumeHeader;
import com.builder.ResumeBuilder.dto.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Resume")
@Data
@AllArgsConstructor
public class Resume {
    @Id
    @Indexed(unique = true)
    String userID;
    List<Education> educations;
    List<Experience> experiences;
    ResumeHeader resumeHeader;
    PersonalDetails personalDetails;
    List<Project> projects;
    List<String> skills;
}
