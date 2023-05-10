package mainApp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "student")
public class Student {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	int id;
	
	@Column(name = "firstName")
	@Pattern(regexp = "[a-zA-Z]{3,}", message = "length must be 3 or higher (letters only)")
	String firstName;
	
	@Column(name = "lastName")
	@Pattern(regexp = "[a-zA-Z]{3,}", message = "length must be 3 or higher (letters only)")
	String lastName;
	
	@Column(name = "email")
	@Pattern(regexp = "^\\w+([\\.-]?\\w+)*@gmail\\.com$", message = "Invalid gmail")
	String email;

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}
