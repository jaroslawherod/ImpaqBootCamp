package boot.camp.day1.hello;

public class CSVParserException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
