package se.kth.iv1350.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import se.kth.iv1350.model.Amount;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static java.lang.System.out;
import static java.lang.System.setOut;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ErrorMessageHandlerTest {
    private ErrorMessageHandler instanceToTest;
    private PrintStream printStream;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        instanceToTest = new ErrorMessageHandler();
        printStream = out;
        outputStream = new ByteArrayOutputStream();
        setOut(new PrintStream(outputStream));

    }
    @AfterEach
    public void tearDown() {
        instanceToTest = null;
        setOut(printStream);
        outputStream = null;
    }

    @ParameterizedTest
    @ValueSource(strings = {"TIME","ERROR", " MESSAGE", "TEST"})
    public void errorMsgTest(String string) {
        instanceToTest.showErrorMsg(string);
        assertTrue(outputStream.toString().contains(string));
    }
}
