package se.kth.iv1350.controller;


/**
 * Thrown when an operation fails
 */
public class OperationFailedException extends Exception
{

   /**
    * Creates a new instance with the specified message and root cause.
    *
    * @param msg   The exception message.
    * @param cause The exception that caused this exception.
    */
   public OperationFailedException (String msg, Exception cause){
      super(msg, cause);
   }
}
