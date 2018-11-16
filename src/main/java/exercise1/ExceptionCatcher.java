package exercise1;

public class ExceptionCatcher {
	private final ExceptionThrower exceptionThrower;

	public ExceptionCatcher(ExceptionThrower exceptionThrower) {
		this.exceptionThrower = exceptionThrower;
	}

	void executeExceptionThrowerSafely(int value) {
		try {
			exceptionThrower.throwACustomExceptionWhenValueIs42(value);	//statements that may cause an exception
		} finally {

		}

	}
}
