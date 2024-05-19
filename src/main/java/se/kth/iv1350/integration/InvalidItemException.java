package se.kth.iv1350.integration;

/**
 * Thrown when controller sends an Invalid itemID
 */
public class InvalidItemException extends Exception
{
    /**
     * Creates a new instance with the specified message.
     *
     * @param message  The exception message.
     */
    public InvalidItemException( String message){
        super(message);
    }
}
