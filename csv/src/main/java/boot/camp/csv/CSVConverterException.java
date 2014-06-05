package boot.camp.csv;

public class CSVConverterException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CSVConverterException(String message) {
		super(message);
	}

	public CSVConverterException(String message, Throwable cause) {
		super(message, cause);
	}

	public CSVConverterException(Throwable cause) {
		super(cause);
	}

}
