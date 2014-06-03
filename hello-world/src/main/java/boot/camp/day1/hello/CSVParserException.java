package boot.camp.day1.hello;

public class CSVParserException extends Exception {
	
	public CSVParserException(String message) {
		super(message);
	}

	public CSVParserException(String message, Throwable cause) {
		super(message, cause);
	}

	public CSVParserException(Throwable cause) {
		super(cause);
	}

}
