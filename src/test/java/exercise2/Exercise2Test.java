package exercise2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class Exercise2Test {
	private static final Student[] STUDENTS = {
		new Student("Jo", "Henderson", LocalDate.of(1981, 3, 8)),
		new Student("Caleb", "Stephen", LocalDate.of(1983, 5, 9)),
		new Student("Diana", "Shelton", LocalDate.of(1981, 2, 9))
	};
	private static final Teacher TEACHER = new Teacher("Claire", "Barnett",
		LocalDate.of(1975, 3, 7), new PhoneNumber("+32 65 123 456"),
		new Location("Ho.23", "Houdain"));
	private Path tempFilePath;

	@Before
	public void createTempFile() throws IOException {
		tempFilePath = Files.createTempFile("classroom", ".ser");
	}

	@After
	public void cleanupTempFile() throws IOException {
		if (tempFilePath != null) {
			Files.deleteIfExists(tempFilePath);
		}
	}

	@Test
	public void save() throws IOException {
		Classroom classroom = new Classroom(TEACHER, STUDENTS);
		Exercise2.save(classroom, tempFilePath);
		assertThat(tempFilePath).exists();
		assertThat(Files.size(tempFilePath)).isGreaterThan(10l);
	}

	@Test
	public void save_and_load() throws IOException, ClassNotFoundException {
		Classroom classroom = new Classroom(TEACHER, STUDENTS);
		Exercise2.save(classroom, tempFilePath);
		Classroom loadedClassroom = Exercise2.load(tempFilePath);
		assertThat(loadedClassroom).isNotNull();
		assertThat(loadedClassroom.getTeacher()).isEqualTo(TEACHER);
		assertThat(loadedClassroom.getStudents()).containsOnly(STUDENTS);
	}
}
