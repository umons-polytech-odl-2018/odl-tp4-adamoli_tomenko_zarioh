package exercise2;

import java.io.Serializable;
import java.time.LocalDate;

public class Student extends Person implements Serializable {
	public Student(String firstName, String lastName, LocalDate birthDate) {
		super(firstName, lastName, birthDate);
	}
}
