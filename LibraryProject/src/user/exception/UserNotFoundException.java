 
package user.exception;

public class UserNotFoundException extends Exception
{
	public UserNotFoundException(String msg)
	{
		super(msg);
	}
	
	public UserNotFoundException(Throwable t)
	{
		super(t);
	}

	public UserNotFoundException(String msg, Throwable t)
	{
		super(msg, t);
	}

}
