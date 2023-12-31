package com.builder.ResumeBuilder;

import com.builder.ResumeBuilder.dto.PersonalDetails;
import com.builder.ResumeBuilder.dto.ResumeHeader;
import com.builder.ResumeBuilder.dto.Education;
import com.builder.ResumeBuilder.dto.Experience;
import com.builder.ResumeBuilder.dto.Project;
import com.builder.ResumeBuilder.dao.Resume;
import com.builder.ResumeBuilder.service.ResumeServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class ResumeBuilderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResumeBuilderApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ResumeServiceImpl resumeService){
		return args -> {


			String userId="shubhamHaritash";
			List<String> achivements=new ArrayList<>();
			achivements.add("District second topper");
			achivements.add("District second topper");
			achivements.add("District second topper");
			achivements.add("District second topper");
			Education education=new Education("Intermediate","ST xaviers",new Date(),new Date(System.currentTimeMillis()+1000*60*24*30*12),achivements);
			Education education2=new Education("B.tech","Jamia Millia Islamia",new Date(),new Date(System.currentTimeMillis()+1000*60*24*30*12),achivements);

			List<Education> educationList=new ArrayList<>();
			educationList.add(education);
			educationList.add(education2);

			List<String> companyAchivements=new ArrayList<>();
			companyAchivements.add("Prevented cost leakage of 40 cr");
			companyAchivements.add("Prevented cost leakage of 40 cr");
			companyAchivements.add("Prevented cost leakage of 40 cr");
			companyAchivements.add("Prevented cost leakage of 40 cr");
			Experience experience=new Experience("Software Engineer","Airtel",new Date(),new Date(System.currentTimeMillis()+1000*60*24*30*12),true,companyAchivements);
			Experience experience2=new Experience("Trainee","Kent",new Date(),new Date(System.currentTimeMillis()+1000*60*24*30*12),false,companyAchivements);

			List<Experience> experienceList=new ArrayList<>();
			experienceList.add(experience);
			experienceList.add(experience2);

			ResumeHeader resumeHeader=new ResumeHeader("Shubham Sharma","Backend Engineer");


			PersonalDetails personalDetails=new PersonalDetails("Ghaziabad","8172536620","shubham@gmail.com","linkedin.com","github.com");


			List<String> projectDetails=new ArrayList<>();
			projectDetails.add("Used Design patterns ");
			projectDetails.add("Used Design patterns ");
			projectDetails.add("Used Design patterns ");
			projectDetails.add("Used Design patterns ");
			Project project=new Project("SpiltWise",new Date(),new Date(System.currentTimeMillis()+1000*60*24*30*12),projectDetails);
			Project project2=new Project("ParkingLot",new Date(),new Date(System.currentTimeMillis()+1000*60*24*30*12),projectDetails);

			List<Project> projectList=new ArrayList<>();
			projectList.add(project);
			projectList.add(project2);

			List<String> skillList=new ArrayList<>();
			skillList.add("Java");
			skillList.add("SpringBoot");

			Resume resume=new Resume(userId,educationList,experienceList,resumeHeader,personalDetails,projectList,skillList);

			resumeService.saveResume(resume);

		};
	}
}
