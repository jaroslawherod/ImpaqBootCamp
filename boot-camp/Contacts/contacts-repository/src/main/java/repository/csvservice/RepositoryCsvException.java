package repository.csvservice;

public class RepositoryCsvException extends RuntimeException{

	public RepositoryCsvException() {}
	
	public RepositoryCsvException(String message)
	{
		super(message);
	}
	
	public RepositoryCsvException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public RepositoryCsvException(Throwable cause)
	{
		super(cause);
	}


}
