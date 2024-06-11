package se.kth.iv1350.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.Receipt;
import se.kth.iv1350.model.Sale;
import se.kth.iv1350.view.ErrorMessageHandler;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static java.lang.System.out;
import static java.lang.System.setOut;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrinterTest {
    private Controller instanceToTest;
    private PrintStream printStream;
    private ByteArrayOutputStream outputStream;
    @BeforeEach
    public void setUp() {
        instanceToTest = new Controller();
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
    @Test
    public void printerTest() {
        instanceToTest.startSale();
        instanceToTest.setRunningTotal();
        instanceToTest.payment(new Amount(0));
        instanceToTest.printReciept();
        assertAll(
                () -> assertTrue(outputStream.toString().toLowerCase().contains("begin receipt")),
                () -> assertTrue(outputStream.toString().toLowerCase().contains("total: 0.0")),
                () -> assertTrue(outputStream.toString().toLowerCase().contains("total vat: 0.0")),
                () -> assertTrue(outputStream.toString().toLowerCase().contains("change: 0.0")),
                () -> assertTrue(outputStream.toString().toLowerCase().contains("cash: 0.0")),
                () -> assertTrue(outputStream.toString().toLowerCase().contains("sale time:")),
                () -> assertTrue(outputStream.toString().toLowerCase().contains("end receipt"))
        );

    }
}
