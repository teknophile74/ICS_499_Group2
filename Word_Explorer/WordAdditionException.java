
public class WordAdditionException extends Exception 
{
	/**
	 * Used for file version
	 */
	private static final long serialVersionUID = 7731035979932265448L;
	/**
	 * The message to pass along with any exceptions thrown
	 */
	private String exceptionMessage = "";
	/**
	 * Constructor
	 * @param a_message is the message for the exception
	 */
	WordAdditionException(String a_message)
	{
		exceptionMessage = a_message; 
	}
	/**
	 * Provides a method to retrieve the exception's error message
	 * @return returns a string that represents the message
	 */
	public String getMessage()
	{
		return exceptionMessage;
	}
}
