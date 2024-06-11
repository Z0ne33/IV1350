package se.kth.iv1350.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.integration.InventoryTest;
import se.kth.iv1350.model.StoreItem;
import se.kth.iv1350.startup.Main;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static java.lang.System.out;
import static java.lang.System.setOut;
import static org.junit.jupiter.api.Assertions.assertAll;
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
    @CsvSource({
            "orange, 1, 10, 'green apple 150g'",
            "apple, 7, 10, 'an orange that weights 100g'",
            "BigWheel Oatmeal, 4,50, 'BigWheel Oatmeal 500 g , whole grain oats , high fiber , gluten free'"
    })
    public void viewTest(String itemID, int quantity,double price, String desc) {

        String testItems = itemFormat(itemID, quantity, price);
        String testExternal = externalInventoryUpdateFormat(itemID, quantity);
        String testsItemReceipt = formatItemReceipt(itemID,quantity, price);
        instanceToTest.Execution();

        assertAll(
                () -> assertTrue(outputStream.toString().toLowerCase().contains(testItems.toLowerCase())),
                () -> assertTrue(outputStream.toString().toLowerCase().contains(testExternal.toLowerCase())),
                () -> assertTrue(outputStream.toString().toLowerCase().contains(testsItemReceipt.toLowerCase())),
                () -> assertTrue(outputStream.toString().toLowerCase().contains(desc.toLowerCase()))

        );

    }
    @Test
    public void viewTestRecieptInfo(){
        String testTotal = format("Total: "  , "218.4");
        String testVAT = format("Total VAT: ","32.0");
        String testDiscount = format("Discount: ", "93.6");
        String testPayment = format("Cash: ", "500.0");
        String testChange = format("Change: ", "281.6");

        instanceToTest.Execution();

       assertAll(
                () -> assertTrue(outputStream.toString().toLowerCase().contains(testTotal.toLowerCase())),
                () -> assertTrue(outputStream.toString().toLowerCase().contains(testVAT.toLowerCase())),
                () -> assertTrue(outputStream.toString().toLowerCase().contains(testDiscount.toLowerCase())),
                () -> assertTrue(outputStream.toString().toLowerCase().contains(testPayment.toLowerCase())),
                () -> assertTrue(outputStream.toString().toLowerCase().contains(testChange.toLowerCase()))
       );
    }

    private String itemFormat(String itemID, int quantity, double price){
        StringBuilder formattedItem = new StringBuilder();
        formattedItem.append("Add ").append(quantity).append(" item with item id: ").append(itemID).append("\r\n");
        formattedItem.append("Item ID: ").append(itemID).append("\r\n");
        formattedItem.append("Item cost: ").append(price).append("\r\n");
        return formattedItem.toString();
    }
    private String format(String text, String info){
        StringBuilder format = new StringBuilder();
        format.append(text).append(info);
        return format.toString();

    }
    private String externalInventoryUpdateFormat(String item, int quantity){
        StringBuilder format = new StringBuilder();
        format.append("Told external inventory system to decrease inventory quantity of item " + item + " by "+ quantity + " units");
        return format.toString();

    }
    private String formatItemReceipt(String item, int quantity, double price){
        StringBuilder format = new StringBuilder();
        format.append(item + " \t" + quantity +" x "+ price).append(" \t"+quantity * price + " SEK");
       return format.toString();
    }




}
