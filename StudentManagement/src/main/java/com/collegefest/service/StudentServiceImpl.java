package com.collegefest.service;

import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.collegefest.model.Student;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@Repository
public class StudentServiceImpl implements StudentService {


	private SessionFactory sessionFactory;

	// create session
	private Session session;

	@Autowired
	StudentServiceImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
	}


	@Transactional
	public List<Student> findAll() {
		//		Session session;
		//
		//		try {
		//		    session = sessionFactory.getCurrentSession();
		//		} catch (HibernateException e) {
		//		    session = sessionFactory.openSession();
		//		}
		Transaction tx = session.beginTransaction();

		// find all the records from the database table
		List<Student> students=session.createQuery("from Student").list();

		tx.commit();


		return students;
	}

	@Transactional
	public Student findById(int id) {

		Student student = new Student();

		Transaction tx = session.beginTransaction();

		// find record with Id from the database table
		student = session.get(Student.class, id);

		tx.commit();


		return student;
	}

	@Transactional
	public void save(Student theStudent) {

		
		Transaction tx = session.beginTransaction();

		// save transaction
		session.saveOrUpdate(theStudent);


		tx.commit();


	}
	
	@Transactional
	public void deleteById(int id) {

		Transaction tx = session.beginTransaction();

		// get transaction
		Student student = session.get(Student.class, id);

		// delete record
		session.delete(student);

		tx.commit();

	}

	@Transactional
	public List<Student> searchBy(String Name, String Department) {

		Transaction tx = session.beginTransaction();
		String query="";
		if(Name.length()!=0 && Department.length()!=0)
			query ="from Student where name like '%"+Name+"%' or department like '%"+Department+"%'";
		else if(Name.length()!=0)
			query ="from Student where name like '%"+Name+"%'";
		else if(Department.length()!=0)
			query ="from Student where department like '%"+Department+"%'";
		else
			System.out.println("Cannot search without input data");


		List<Student> student=session.createQuery(query).list();

		tx.commit();


		return student;
	}

	// print the loop
	@Transactional
	public void print(List<Student> student) {

		for(Student b:student) 
		{
			System.out.println(b);
		}
	}

}