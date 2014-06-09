package boot.camp.service;

public class RepositoryServiceException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RepositoryServiceException(String message) {
		super(message);
	}

	public RepositoryServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public RepositoryServiceException(Throwable cause) {
		super(cause);
	}

}
