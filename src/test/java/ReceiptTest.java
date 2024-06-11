

import org.example.solution.*;
import org.example.solution.util.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class ReceiptTest {
    private Cashier cashier;
    private Store store;
    private List<Sale> sales;

    @BeforeEach
    public void setUp() {
        // Create a test cashier
        cashier = new Cashier("TestCashier", 1000.0);

        // Create a test store with markup percentages and discount percentage
        Map<Category, Double> markupPercentages = new HashMap<>();
        markupPercentages.put(Category.FOOD, 10.0);
        store = new Store("TestStore", markupPercentages, 7, 5.0);

        // Create test sales
        LocalDate date = LocalDate.of(2024, 10, 22);
        Product product1 = new Product("Product1", 10.0, Category.FOOD, date, 10);
        Product product2 = new Product("Product2", 20.0, Category.NON_FOOD, date, 5);
        sales = Arrays.asList(new Sale(product1, 2), new Sale(product2, 1));

        // Set the store for the products
        product1.setStore(store);
        product2.setStore(store);
    }

    @Test
    public void testToString() {
        // Create a test receipt
        Receipt receipt = new Receipt(cashier, sales);

        // Generate the expected string representation
        String expected = "Receipt Number: " + receipt.getReceiptNumber() + "\n" +
                "Cashier: " + cashier.getName() + "\n" +
                "Date and Time: " + receipt.getDateTime() + "\n" +
                "Sales:\n" +
                " - " + sales.get(0).getProduct().getName() + ", Quantity: 2, Price per unit: " + sales.get(0).getProduct().calculateSellingPrice() + "\n" +
                " - " + sales.get(1).getProduct().getName() + ", Quantity: 1, Price per unit: " + sales.get(1).getProduct().calculateSellingPrice() + "\n" +
                "Total Amount: " + receipt.getTotalAmount() + "\n";

        // Compare the expected string with the actual string representation
        assertEquals(expected, receipt.toString());
    }

    @Test
    public void testSaveToFile() {
        // Create a test receipt
        Receipt receipt = new Receipt(cashier, sales);

        // Save the receipt to a file
        receipt.saveToFile();

        // Check if the file is created
        String filename = "src/main/java/org/example/solution/receipts/receipt_" + receipt.getReceiptNumber() + ".txt";
        File file = new File(filename);
        assertTrue(file.exists());

        // Check if the file content matches the expected content
        try {
            String expectedContent = receipt.toString();
            String actualContent = Files.readString(file.toPath());
            assertEquals(expectedContent, actualContent);
        } catch (IOException e) {
            fail("Error reading file: " + e.getMessage());
        } finally {
            // Clean up: delete the file after testing
            file.delete();
        }
    }
}

