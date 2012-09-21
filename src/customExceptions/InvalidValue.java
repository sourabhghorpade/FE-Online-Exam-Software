package customExceptions;

public class InvalidValue extends Exception
	{

	private static final long serialVersionUID = 1L;
	String message;
	public InvalidValue(String message)
		{
			this.message=message;
		}
	public String toString()
		{
			return message;
		}
	}
