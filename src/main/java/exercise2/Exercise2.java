package exercise2;

import javax.sound.sampled.Line;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

public class Exercise2 {
	public static void save(Classroom classroom, Path filePath) {
		try(OutputStream output = Files.newOutputStream(filePath)){
			ObjectOutput objectOutput = new ObjectOutputStream(output);
			objectOutput.writeObject((classroom));
		} catch (Exception e){}

	}

	public static Classroom load(Path filePath) {
		ArrayList<String> scripts = new ArrayList<String>();
		Charset utf8Charset = Charset.forName("UTF-8");
		try(BufferedReader reader = Files.newBufferedReader(filePath, utf8Charset)){
			reader.lines().forEach(line->scripts.add(line));
		}catch (Exception e){}

		Teacher teacher = new Teacher(scripts.get(0), scripts.get(1), LocalDate.parse(scripts.get(2)), new PhoneNumber(scripts.get(3)), new Location(scripts.get(4), scripts.get(5));

		return null;
	}

	public static void main(String[] args) throws IOException {
		Teacher teacher = new Teacher("Claire", "Barnett",
			LocalDate.of(1975, 3, 7), new PhoneNumber("+32 65 123 456"),
			new Location("Ho.23", "Houdain"));
		Student[] students = {
			new Student("Jo", "Henderson", LocalDate.of(1981, 3, 8)),
			new Student("Caleb", "Stephen", LocalDate.of(1983, 5, 9)),
			new Student("Diana", "Shelton", LocalDate.of(1981, 2, 9))
		};
		Classroom classroom = new Classroom(teacher, students);

		Path filePath = Paths.get("classroom.ser");

		save(classroom, filePath);

		System.out.printf("Classroom saved to %s, size=%d bytes\n", filePath.toString(), Files.size(filePath));

		Classroom loadedClassroom = load(filePath);

		System.out.printf("Classroom loaded from %s: %s\n", filePath.toString(), loadedClassroom.toString());
	}
}
