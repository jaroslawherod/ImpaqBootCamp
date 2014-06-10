package boot.camp.springjsf.service;

public class PeopleServiceException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8854507274257768932L;

	public PeopleServiceException(String message) {
		super(message);
	}

	public PeopleServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public PeopleServiceException(Throwable cause) {
		super(cause);
	}

}
