package contactscsvservice;

public class ContactsCsvServiceException extends Exception
{
	public ContactsCsvServiceException() {}
	
	public ContactsCsvServiceException(String message)
	{
		super(message);
	}
	
	public ContactsCsvServiceException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public ContactsCsvServiceException(Throwable cause)
	{
		super(cause);
	}
	
}
