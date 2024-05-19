package se.kth.iv1350.integration;

/**
 * Thrown when Database is unavailable
 */
public class DatabaseUnavailableException extends RuntimeException{

    /**
     * Creates a new instance with the specified message.
     *
     * @param message The exception message.
     */
    public DatabaseUnavailableException( String message){
        super(message);

    }
}
