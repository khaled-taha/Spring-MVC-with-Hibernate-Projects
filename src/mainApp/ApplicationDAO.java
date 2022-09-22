package mainApp;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mainApp.entity.Student;

@Repository
public class ApplicationDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public List<Student> getAllStudents(){
		// Create Session
		Session session = this.sessionFactory.getCurrentSession();
		// Get all students from DB
		return session.createQuery("from Student", Student.class).getResultList();
	}
	
	@Transactional
	public Student getStudent(int id){
		// Create Session
		Session session = this.sessionFactory.getCurrentSession();
		// Get all students from DB
		return session.get(Student.class, id);
	}
	
	
	@Transactional
	public void SaveStudent(Student student) {
		this.sessionFactory.getCurrentSession().
		saveOrUpdate(student);
	}
	
	
	@Transactional
	public void deleteStudent(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Student student = session.get(Student.class, id);
		session.delete(student);
		
	}
	
	
}
