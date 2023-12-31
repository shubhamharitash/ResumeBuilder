package com.builder.ResumeBuilder.util;

import com.builder.ResumeBuilder.dao.Resume;
import com.builder.ResumeBuilder.dto.Education;
import com.builder.ResumeBuilder.dto.Experience;
import com.builder.ResumeBuilder.dto.Project;
import com.builder.ResumeBuilder.enums.FieldType;
import com.lowagie.text.DocumentException;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PdfGenerator {
    public static void generate(Resume resume, HttpServletResponse response) throws DocumentException, IOException {
        // Creating the Object of Document
        Document document = new Document(PageSize.A4);
        // Getting instance of PdfWriter
        PdfWriter.getInstance(document, response.getOutputStream());
        // Opening the created document to change it
        document.open();
        // Creating font
        // Setting font style and size
        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        Font fontSubTitle=FontFactory.getFont(FontFactory.TIMES);
        fontTiltle.setSize(20);
        // Creating paragraph
        Paragraph paragraph1 = new Paragraph(resume.getResumeHeader().getTitle(), fontTiltle);
        // Aligning the paragraph in the document

        Paragraph paragraph2 = new Paragraph(resume.getResumeHeader().getSubsTitle(), fontSubTitle);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");



        String personalDetails=String.format("Email: %s\n Mob: %s\n Address: %s\n LinkedIn: %s\n Github: %s",
                resume.getPersonalDetails().getEmailId(),
                resume.getPersonalDetails().getMobNumber(),
                resume.getPersonalDetails().getAddress(),
                resume.getPersonalDetails().getLinkedInUrl(),
                resume.getPersonalDetails().getGithubUrl());

        Paragraph paragraph3 = new Paragraph(personalDetails, fontSubTitle);
        // Aligning the paragraph in the document
        paragraph1.setAlignment(Paragraph.ALIGN_LEFT);
        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);
        paragraph3.setAlignment(Paragraph.ALIGN_RIGHT);
        // Adding the created paragraph in the document
        document.add(paragraph1);
        document.add(paragraph2);
        document.add(paragraph3);

        // Creating a table of the 4 columns
        PdfPTable table = new PdfPTable(1);
        // Setting width of the table, its columns and spacing
        table.setWidthPercentage(100f);
        table.setWidths(new int[] {12});
        table.setSpacingBefore(5);
        // Create Table Cells for the table header
        PdfPCell headerCell= new PdfPCell();
        PdfPCell bodyCell=new PdfPCell();
        // Setting the background color and padding of the table cell
        headerCell.setBackgroundColor(CMYKColor.gray);
        headerCell.setPadding(5);


        // Creating font
        // Setting font style and size
        Font titleFont = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        titleFont.setColor(CMYKColor.RED);

        Font bodyFont = FontFactory.getFont(FontFactory.COURIER_OBLIQUE);
        titleFont.setColor(CMYKColor.BLACK);

        Font dateFont = FontFactory.getFont(String.valueOf(FontFactory.getFont(String.valueOf(Font.ITALIC),16, Color.CYAN)));

        // Adding headings in the created table cell or  header
        // Adding Cell to table

        headerCell.setPhrase(new Phrase(FieldType.Education.toString(), titleFont));
        table.addCell(headerCell);
        List<Education> educationList= resume.getEducations();
        for (Education education:educationList) {
            String achivements=education.getAchivements().stream().map(e->e+".").collect(Collectors.joining("\n"));
            String output=String.format("%s\n\n\n%s\t ( %s\t - %s ) \n\n%s\n",education.getDegreeName(),
                    education.getSchoolName(),
                    dateFormat.format(education.getFromDate()),
                    dateFormat.format(education.getTillDate()),
                    achivements);
            bodyCell.setPhrase(new Phrase(output,bodyFont));
            table.addCell(bodyCell);
        }


        headerCell.setPhrase(new Phrase(FieldType.Experience.toString(), titleFont));
        table.addCell(headerCell);
        List<Experience> experienceList=resume.getExperiences();
        for (Experience experience:experienceList) {
            String achivements=experience.getAchivements().stream().map(e->e+".").collect(Collectors.joining("\n"));
            String endDate=dateFormat.format(experience.getTillDate());
            if (experience.isCurrentJob() || experience.getTillDate()==null)
                endDate="Present";

            log.info("Date: {}",dateFormat.format(experience.getFromDate()));
            String output=String.format("%s\n\n\n%s\t ( %s\t - %s ) \n\n%s\n",
                    experience.getDesignation(),
                    experience.getCompanyName(),
                    dateFormat.format(experience.getFromDate()),
                    endDate,
                    achivements);
            bodyCell.setPhrase(new Phrase(output,bodyFont));
            table.addCell(bodyCell);
        }

        headerCell.setPhrase(new Phrase(FieldType.Projects.toString(), titleFont));
        table.addCell(headerCell);
        List<Project> projectList= resume.getProjects();
        for (Project project:projectList) {
            String projectDetails=project.getProjectDetails().stream().map(e->e+".").collect(Collectors.joining("\n"));

            String output=String.format("%s\t ( %s\t - %s ) \n\n%s\n",
                    project.getProjectName(),
                    dateFormat.format(project.getFromDate()),
                    dateFormat.format(project.getTillDate()),
                    projectDetails);
            bodyCell.setPhrase(new Phrase(output,bodyFont));
            table.addCell(bodyCell);
        }


        headerCell.setPhrase(new Phrase(FieldType.Skills.toString(), titleFont));
        table.addCell(headerCell);

        String skills=resume.getSkills().stream().collect(Collectors.joining(","));
        bodyCell.setPhrase(new Phrase(skills,bodyFont));
        table.addCell(bodyCell);

        // Adding the created table to the document
        document.add(table);
        // Closing the document
        document.close();
    }


}
