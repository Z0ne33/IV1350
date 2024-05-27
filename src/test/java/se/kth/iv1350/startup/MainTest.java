package se.kth.iv1350.startup;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.DTO.ItemDTO;
import se.kth.iv1350.model.StoreItem;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static java.lang.System.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {
    private Main instanceToTest;
    private PrintStream printStream;
    ByteArrayOutputStream outputStream;

    String[] args;
    @BeforeEach
    public void setUp() {

        instanceToTest = new Main();
        printStream = out;
        outputStream = new ByteArrayOutputStream();
        args = new String[0];
        setOut(new PrintStream(outputStream));



    }
    @AfterEach
    public void tearDown() {
        instanceToTest = null;
        setOut(printStream);
        outputStream = null;

    }
    @ParameterizedTest
    @ValueSource(strings = {"new sale","receipt"})
    public void mainTest(String string) {
        instanceToTest.main(args);
        assertTrue(outputStream.toString().toLowerCase().contains(string.toLowerCase()));



    }
}
