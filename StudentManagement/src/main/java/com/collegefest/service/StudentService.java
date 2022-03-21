package com.collegefest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.collegefest.model.Student;

@Service
public interface StudentService {
	public List<Student> findAll();

	public Student findById(int theId);

	public void save(Student theStudent);

	public void deleteById(int theId);

	public List<Student> searchBy(String name, String department);

}
