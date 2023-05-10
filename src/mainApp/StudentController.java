package mainApp;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import mainApp.entity.Student;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	private List<Student> students;
	private List<Student> sortedStudentsByFirstName;
	private List<Student> sortedStudentsByLastName;
	private List<Student> sortedStudentsByEmail;
	
	@Autowired
	private ApplicationDAO dao;
	
	@RequestMapping("/students-list")
	public String getStudentsList(Model model) {
		if(this.students == null) this.students = this.dao.getAllStudents();
		model.addAttribute("students", this.students);
		return "students-list";
	}
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		model.addAttribute("Student", new Student());
		return "student-form";
	}
	
	@RequestMapping("/saveStudent")
	public String saveStudent(Model model, @Valid @ModelAttribute("Student") Student student
			, BindingResult bindingResult) {

		
		if(bindingResult.hasErrors()) 
			return "student-form";
		// else 
	    this.dao.SaveStudent(student);
	    setAllLists();
		return "redirect:/student/students-list";
	}
	
	@RequestMapping("/showFormForUpdate")
	public String updateStudent(@RequestParam("studentId") int id, Model model) {
		Student student = this.searchInListByID(id);
		model.addAttribute("Student", student);
		return "student-form";
	}
	
	@RequestMapping("/delete")
	public String saveStudent(@RequestParam("studentId") int id) {
	    this.dao.deleteStudent(id);
	    this.students = null;
		return "redirect:/student/students-list";
	}
	
	
	@RequestMapping("/search")
	public String updateStudent(@RequestParam("theSearchName") String name, Model model) {
		List<Student> students = this.searchInListByName(name);
		model.addAttribute("students", students);
		return "students-list";
	}
	
	
	@RequestMapping("/sortByFirstName")
	public String getSortedStudentsByFirstName(Model model) {
		/*
		 if the sortedStudentsByFirstName list has already been sorted, 
		 this method will not sort it again
		 */
		this.sortByFirstName();
		List<Student> students = this.sortedStudentsByFirstName;
		model.addAttribute("students", students);
		return "students-list";
	}
	
	
	
	@RequestMapping("/sortByLastName")
	public String getSortedStudentsByLastName(Model model) {
		/*
		 if the sortedStudentsByFirstName list has already been sorted, 
		 this method will not sort it again
		 */
		this.sortByLastName();
		List<Student> students = this.sortedStudentsByLastName;
		model.addAttribute("students", students);
		return "students-list";
	}
	
	
	
	@RequestMapping("/sortByEmail")
	public String getSortedStudentsByEmail(Model model) {
		/*
		 if the sortedStudentsByFirstName list has already been sorted, 
		 this method will not sort it again
		 */
		this.sortByEmail();
		List<Student> students = this.sortedStudentsByEmail;
		model.addAttribute("students", students);
		return "students-list";
	}
	
	
	/**
	 * This method is used to search for a student by his id through 
	 * the <Strong>students</Strong> list
	 * @param id - student id that using to search 
	 * @return - object of Student
	 */
	private Student searchInListByID(int id) {
		Student updatedstudent = null;
		try {
		     updatedstudent = this.students.stream().
			              filter(student -> student.getId() == id).
			              collect(Collectors.toList()).get(0);
		}catch (Exception e) { }
		
		return updatedstudent;
	}
	
	/**
	 * This method is used to search for a student by his name through 
	 * the <Strong>students</Strong> list
	 * @param name - student name that using to search 
	 * @return - list of Student
	 */
	private List<Student> searchInListByName(String name) {
		
		if(this.students == null) this.students = this.dao.getAllStudents();
			return this.students.stream().
			              filter(student -> (student.getFirstName().equalsIgnoreCase(name)
			            		           ||student.getLastName().equalsIgnoreCase(name) 
			            		           ||(student.getFirstName() + " " + student.getLastName()).equalsIgnoreCase(name)
			            		             )).
			              collect(Collectors.toList());
	}
	
	
	/**
	 * This method is used to sort all students by their first names.
	 * The result will be stored in <Strong>sortedStudentsByFirstName</Strong>
	 * list.
	 * 
	 * The method will get all data for DB if the list is null first, then sort it,
	 * or sorts it directly if the list isn't null.
	 */
	private void sortByFirstName() {
		if(this.sortedStudentsByFirstName == null) {
		
		// step1: get all data
		this.sortedStudentsByFirstName = this.dao.getAllStudents();
		
		// step2: sorting
		Collections.sort(this.sortedStudentsByFirstName, 
				(student1, student2) ->
		         student1.getFirstName().charAt(0) - student2.getFirstName().charAt(0));
		}
	}
	
	
	/**
	 * This method is used to sort all students by their last names.
	 * The result will be stored in <Strong>sortedStudentsByLastName</Strong>
	 * list.
	 * 
	 * The method will get all data for DB if the list is null first, then sort it,
	 * or sorts it directly if the list isn't null.
	 */
	private void sortByLastName() {
		if(this.sortedStudentsByLastName == null) {
		
		// step1: get all data
		this.sortedStudentsByLastName = this.dao.getAllStudents();
		
		// step2: sorting
		Collections.sort(this.sortedStudentsByFirstName, 
				(student1, student2) ->
		         student1.getLastName().charAt(0) - student2.getLastName().charAt(0));
		}
	}
	
	/**
	 * This method is used to sort all students by their emails.
	 * The result will be stored in <Strong>sortedStudentsByEmail</Strong>
	 * list.
	 * 
	 * The method will get all data for DB if the list is null first, then sort it,
	 * or sorts it directly if the list isn't null.
	 */
	private void sortByEmail() {
		if(this.sortedStudentsByEmail == null) {
		
		// step1: get all data
		this.sortedStudentsByEmail = this.dao.getAllStudents();
		
		// step2: sorting
		Collections.sort(this.sortedStudentsByEmail, 
				(student1, student2) ->
		         student1.getEmail().charAt(0) - student2.getEmail().charAt(0));
		}
	}
	
	/**
	 * set for new updating (add, update, delete) before any sorting
	 */
	private void setAllLists() {
		// set for new updating (add, update, delete)
	    this.students = null;
	    this.sortedStudentsByFirstName = null;
	    this.sortedStudentsByLastName = null;
	    this.sortedStudentsByEmail = null;
	}
	
	

	
}


