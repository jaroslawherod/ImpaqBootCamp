package boot.camp.csv;

public class CSVWriterException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CSVWriterException(String message) {
		super(message);
	}

	public CSVWriterException(String message, Throwable cause) {
		super(message, cause);
	}

	public CSVWriterException(Throwable cause) {
		super(cause);
	}

}
