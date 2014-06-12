package personwebapp.repository;

public class RepositoryExceptions extends Exception{

	public RepositoryExceptions() {}
	
	public RepositoryExceptions(String message)
	{
		super(message);
	}
	
	public RepositoryExceptions(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public RepositoryExceptions(Throwable cause)
	{
		super(cause);
	}


}
