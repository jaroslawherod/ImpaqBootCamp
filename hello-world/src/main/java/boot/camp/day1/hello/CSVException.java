package boot.camp.day1.hello;

public class CSVException extends RuntimeException {

	private static final long serialVersionUID = -3568102064030972020L;

	public CSVException() {
		super();
	}

	public CSVException(String message, Throwable cause) {
		super(message, cause);	
	}

	public CSVException(String message) {
		super(message);
		
	}

	public CSVException(Throwable cause) {
		super(cause);
		
	}
	
}
