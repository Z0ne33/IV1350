package se.kth.iv1350.startup;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.controller.OperationFailedException;
import se.kth.iv1350.integration.InvalidItemException;
import se.kth.iv1350.view.View;

import java.io.IOException;

/**
 * Starts the entire application, contains the main method used to start the application.
 */
public class Main {
    /**
     * The main method used to start the entire application.
     *
     * @param args The application does not take any command line parameters.
     */
    public static void main(String[] args) throws OperationFailedException, InvalidItemException {

        Controller contr = new Controller();
        View view = new View(contr);
        view.Execution();

    }
}
