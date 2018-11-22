package exercise1;

public class ExceptionCatcher {
	private final ExceptionThrower exceptionThrower;

	public ExceptionCatcher(ExceptionThrower exceptionThrower) {
		this.exceptionThrower = exceptionThrower;
	}
	/*méthide déjà fournie telle quel, la variable va être initialisé à la
	"valeur de l'exception", çà va servir pour le test unitaire*/

	void executeExceptionThrowerSafely(int value) {
		try {
			exceptionThrower.throwACustomExceptionWhenValueIs42(value);	//statements that may cause an exception
		} catch(RuntimeException e) {
			System.out.println("exception attrapée");//on pourrait aussi ne rien faire
		}
	}
	/*je try le code qui pourrait poser pb, puis je catch l'excpetion runtime si y'a un
	pb (ici va y'avoir un pb car la fonction rend une exception quand la value vaut 42),
	et je fais alors l'action dans le bloc catch (try implique d'office un catch ou un
	finally, potentiellement les 2*/
}
