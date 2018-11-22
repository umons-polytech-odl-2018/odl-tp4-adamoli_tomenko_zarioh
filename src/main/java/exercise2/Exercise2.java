package exercise2;

import javax.sound.sampled.Line;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;//tout ces trucs files pour que les opérations sur les fichier fct
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Exercise2 implements Serializable{
	public static void save(Classroom classroom, Path filePath) throws IOException{
		try(OutputStream output = Files.newOutputStream(filePath)){
			ObjectOutput objectOutput = new ObjectOutputStream(output);
			objectOutput.writeObject((classroom));
		}
		/*
		commentaire sur la lecture de fichier à proprement parler :
		-OutputStream output = Files.newOutputStream(filePath) : je crée un canal de
		passage entre le pc et le fichier "extérieur", ce fichier est localisé en Path
		-ObjectOutput objectOutput = new ObjectOutputStream(output)
		objetoutputstream : je crée un "petit bateau" qui va (dans ce cas) écrire (et pas lire)
		les données que le code va lui donner sur le fichier.
		il fera donc des allers-retour entre le code et le fichier quand nécéssaire !
		-objectOutput.writeObject((classroom))
		writeobject : action du bateau qui se déplace pour déplacer les données du
		pc au fichier pour les sauvegarder !
		voir le super schéma résumé de thomas pour cette partie
		*/
		/*
		OutputStream output=null;
		try{
			output = Files.newOutputStream(filePath);
			ObjectOutput objectOutput = new ObjectOutputStream(output);
			objectOutput.writeObject((classroom));
			output.close();
		}
		catch(RuntimeException e){
			System.out.println("je suis dans mon catch et je close");
			if(output !=null){
				output.close();}
		}*/
		/*là y'a des choses à commenter sur le try catch et le try with ressource
		la seconde version (dans le block en commentaire) est une version plus
		"scolaire" de try catch : je tente de run mon code qui pourrait poser pb,
		je catch l'error eventuellement rencontrée (je dis ici arbitrairement
		que c'est une Runtime Exception, çà pourrait être un autre type)
		et je met une petite instruction à exec si erreur il y a + je ferme "la connection"

		des problèmes sont a remarquer :
		-si je veux close dans le catch, je suis obligé de déclarer mon outputoutstream
		en dehors du try, car en effet si cette ligne échoue dans le try, il est impossible
		de close l'output dans le catch ! en plus je suis obligé de l'initialiser à qqch
		(ici null, car si pas nul, alors je sui connecté à qqch)
		car sinon erreur : variable output may not have been initialized
		-il faut fermer manuellement la connection avec le output.close, aussi bien
		dans try que catch (car dans les 2 cas, réussi ou échec, il faut que mon flux de
		données soit fermé !



		l'autre version (celle de pierre, au dessus et qui run en ce moemnt)
		est un "try-with-ressources" (aller voir sur google pour infos compélmentaire,
		y'a un article intéressant sur oracle.com): ce que prend le try comme "argument"
		est une ressource : un objet qui doit être fermé dès que le programme
		en a fini avec. Les lignes entre {} sont la suite des instructions try,
		comme on le faisait plus haut, c'est jsute en fait que la première ligne
		"passe comme paramètre du try"
		Une particularité est que l'élement qui doit se close ("le paramètre")
		se close quel que soit ce qui se passe ensuite dans le try, ou même si
		l'ouverture a été ratée
		une telle notation permet de se passer du catch
		elle est beaucoup plus adaptée à la lecture de fichier car y'a pas le chipotage
		avec le close, vu que la ressource est gérée automatiquement*/
	}
	public static Classroom load(Path filePath) throws IOException, ClassNotFoundException {
		try(InputStream output = Files.newInputStream(filePath)){
			ObjectInput objectOutput = new ObjectInputStream(output);
			return (Classroom) objectOutput.readObject();
		}
		/*voir tout les commentaires à la fonction au dessus, c'est la meme chose ici,
		mais avec des input car ici on veut load des datas depuis un fichier,
		mais le principe du "petit bateau reste le meme)*/
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
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
	}//void main nécéssaire pour les tests unitaire, on devient des habitués
}
