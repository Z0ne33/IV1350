package se.kth.iv1350.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.startup.Main;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static java.lang.System.out;
import static java.lang.System.setOut;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ViewTest {

    private View instanceToTest;
    private PrintStream printStream;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {

        instanceToTest = new View(new Controller());
        printStream = out;
        outputStream = new ByteArrayOutputStream();
        setOut(new PrintStream(outputStream));


    }
    @AfterEach
    public void tearDown() {
        setOut(printStream);
        instanceToTest = null;
        outputStream = null;

    }

    @ParameterizedTest
    @ValueSource(strings = {"apple","orange", "BigWheel Oatmeal"})
    public void viewTest(String string) {
        instanceToTest.Execution();
        assertTrue(outputStream.toString().toLowerCase().contains(string.toLowerCase()));
    }
}
