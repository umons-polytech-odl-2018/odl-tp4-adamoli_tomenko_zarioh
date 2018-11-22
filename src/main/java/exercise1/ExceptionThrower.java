package exercise1;

public class ExceptionThrower {
	void throwACustomExceptionWhenValueIs42(int value) {
		if(value==42) throw new RuntimeException();
	}
	/*idée : je fais passer un paramètre à cette fct,
	si ce param vaut 42, alors la fct renvoie une runtime exception à l'appelant*/
}
