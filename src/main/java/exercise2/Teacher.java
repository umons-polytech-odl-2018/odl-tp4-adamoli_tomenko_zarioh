package exercise2;

import java.io.Serializable;
import java.time.LocalDate;

public class Teacher extends Person implements Serializable {
	private final PhoneNumber officePhone;
	private final Location officeLocation;

	public Teacher(String firstName, String lastName, LocalDate birthDate, PhoneNumber officePhone, Location officeLocation) {
		super(firstName, lastName, birthDate);
		this.officePhone = officePhone;
		this.officeLocation = officeLocation;
	}

	public PhoneNumber getOfficePhone() {
		return officePhone;
	}

	public Location getOfficeLocation() {
		return officeLocation;
	}

	@Override
	public String toString() {
		return String.format("Teacher[%s %s]", getFirstName(), getLastName());
	}
}
