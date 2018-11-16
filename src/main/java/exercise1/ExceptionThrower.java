package exercise1;

public class ExceptionThrower {
	void throwACustomExceptionWhenValueIs42(int value) {
		if(value==42) throw new RuntimeException();
	}
}
