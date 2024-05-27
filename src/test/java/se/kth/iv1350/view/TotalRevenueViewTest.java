package se.kth.iv1350.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.model.Amount;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static java.lang.System.out;
import static java.lang.System.setOut;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TotalRevenueViewTest {
    private TotalRevenueView instanceToTest;
    private ByteArrayOutputStream outputStream;
    private PrintStream printStream;
    @BeforeEach
    public void setUp() {

        instanceToTest = new TotalRevenueView();
        outputStream = new ByteArrayOutputStream();
        printStream = out;
        setOut(new PrintStream(outputStream));



    }
    @AfterEach
    public void tearDown() {
        setOut(printStream);
        instanceToTest = null;
        outputStream = null;
    }

    @Test
    public void totalRevenueTest() {
        instanceToTest.newSale(new Amount(100));
        assertTrue(outputStream.toString().contains("100"));
    }
}
