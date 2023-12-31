package com.builder.ResumeBuilder.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Education {
    String degreeName;
    String schoolName;
    @JsonFormat(pattern = "dd/MM/yyyy")
    Date fromDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    Date tillDate;
    List<String> achivements;
}
