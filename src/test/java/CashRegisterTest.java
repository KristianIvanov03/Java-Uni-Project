

import org.example.solution.*;
import org.example.solution.exceptions.ExpiredProductException;
import org.example.solution.exceptions.InsufficientStockException;
import org.example.solution.util.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class CashRegisterTest {
    private CashRegister cashRegister;
    private Store store;
    private Cashier cashier;
    private Product product;

    @BeforeEach
    public void setUp() {
        // Create a test store with markup percentages and discount percentage
        Map<Category, Double> markupPercentages = new HashMap<>();
        markupPercentages.put(Category.FOOD, 10.0);
        store = new Store("TestStore", markupPercentages, 7, 5.0);

        // Create a test cashier
        cashier = new Cashier("TestCashier", 1000.0);

        // Create a test product
        product = new Product("TestProduct", 10.0, Category.FOOD, LocalDate.now().plusDays(10), 50);
        store.addProduct(product);

        // Create a cash register with the store and cashier
        cashRegister = new CashRegister();
        cashRegister.setStore(store);
        cashRegister.setCashier(cashier);
    }

    @Test
    public void testAddSale() {
        try {
            // Add a sale of the test product
            cashRegister.addSale(product, 10);

            // Check if the sale is added to the cash register
            assertEquals(1, cashRegister.getSales().size());
            Sale sale = cashRegister.getSales().get(0);
            assertEquals(product, sale.getProduct());
            assertEquals(10, sale.getQuantity());
        } catch (ExpiredProductException | InsufficientStockException e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void testGenerateReceipt() {
        try {
            // Add a sale of the test product
            cashRegister.addSale(product, 10);

            // Generate the receipt
            cashRegister.generateReceipt();

            // Check if the sales list is cleared and receipt is added to the store
            assertEquals(0, cashRegister.getSales().size());
            assertEquals(1, store.getReceipts().size());
        } catch (ExpiredProductException | InsufficientStockException e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }
    }
}

