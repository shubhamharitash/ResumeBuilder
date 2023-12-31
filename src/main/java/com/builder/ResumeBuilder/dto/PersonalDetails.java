package com.builder.ResumeBuilder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "personal_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalDetails {
    String address;
    String mobNumber;
    String emailId;
    String linkedInUrl;
    String githubUrl;
}
