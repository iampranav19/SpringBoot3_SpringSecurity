package com.pranav.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pranav.model.Student;

@RestController
public class StudentController {

	List<Student> al=new ArrayList<>();
	
	@GetMapping("/get")
	@PreAuthorize("hasRole('PAID')")
	public List<Student> getStudent()
	{
		Student s1=new Student(101,"Pranav Saharawat");
		Student s2=new Student(102,"Nikhil Sharma");
		Student s3=new Student(103,"Ankit Deshwal");
		al.add(s1);
		al.add(s2);
		al.add(s3);
		return al;
	}
	
	@PostMapping("/save")
	@PreAuthorize("hasRole('ADMIN')")
	public Student createStudent(@RequestBody Student student)
	{
		al.add(student);
		return student;
	}
	
	@PreAuthorize("hasRole('TEST')")
	@GetMapping("/test")
	public String test()
	{
		return "Welcome to the Spring Security with Spring Boot 3!";
	}

}
