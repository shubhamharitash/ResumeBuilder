package com.builder.ResumeBuilder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "resume_header")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeHeader {
    String title;
    String subsTitle;
}
