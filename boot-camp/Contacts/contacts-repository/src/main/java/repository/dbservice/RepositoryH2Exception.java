package repository.dbservice;

public class RepositoryH2Exception extends RuntimeException{

	public RepositoryH2Exception() {}
	
	public RepositoryH2Exception(String message)
	{
		super(message);
	}
	
	public RepositoryH2Exception(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public RepositoryH2Exception(Throwable cause)
	{
		super(cause);
	}


}
