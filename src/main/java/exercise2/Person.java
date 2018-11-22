package exercise2;

import java.io.Serializable;
import java.time.LocalDate;//pour la localdate
import java.util.Objects;//pour faire des opérations entre objets, comme comparer

public class Person implements Serializable {
	private final String firstName;
	private final String lastName;
	private final LocalDate birthDate;
	//seriazable sur la classe mère, et les filles en héritent !
	protected Person(String firstName, String lastName, LocalDate birthDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Person person = (Person) o;
		return Objects.equals(firstName, person.firstName) &&
			Objects.equals(lastName, person.lastName) &&
			Objects.equals(birthDate, person.birthDate);
	}//c'est un override de equal de java.util.objects

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName, birthDate);
	}
	//le meme principe ici
}
