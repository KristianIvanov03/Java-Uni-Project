

import org.example.solution.Product;
import org.example.solution.Store;
import org.example.solution.util.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    private Store store;

    @BeforeEach
    public void setUp() {
        // Create a test store with markup percentages and discount percentage
        Map<Category, Double> markupPercentages = new HashMap<>();
        markupPercentages.put(Category.FOOD, 10.0);
        store = new Store("TestStore", markupPercentages, 7, 5.0);
    }

    @Test
    public void testCalculateSellingPriceWithFoodCategory() {
        // Create a test product with markup for FOOD category
        Product product = new Product("TestProduct", 10.0, Category.FOOD, LocalDate.now().plusDays(10), 50);
        product.setStore(store);

        // Calculate the expected selling price
        double expectedSellingPrice = 10.0 * (1 + 10.0 / 100);

        // Assert that the calculated selling price matches the expected value
        assertEquals(expectedSellingPrice, product.calculateSellingPrice());
    }

    @Test
    public void testCalculateSellingPriceWithNonFoodCategory() {
        Product product = new Product("TestProduct", 20.0, Category.NON_FOOD, LocalDate.now().plusDays(10), 50);
        product.setStore(store);

        double expectedSellingPrice = 20.0; // No markup

        assertEquals(expectedSellingPrice, product.calculateSellingPrice());
    }

    @Test
    public void testCalculateSellingPriceWithDiscount() {
        Product product = new Product("TestProduct", 30.0, Category.FOOD, LocalDate.now().minusDays(1), 50);
        product.setStore(store);

        double expectedSellingPrice = 30.0 * (1 + 10.0 / 100) * (1 - 5.0 / 100);

        assertEquals(expectedSellingPrice, product.calculateSellingPrice());
    }

    @Test
    public void testCalculateSellingPriceNoDiscount() {
        Product product = new Product("TestProduct", 40.0, Category.NON_FOOD, LocalDate.now().plusDays(10), 50);
        product.setStore(store);

        double expectedSellingPrice = 40.0 * (1 + 0.0 / 100); // No discount

        assertEquals(expectedSellingPrice, product.calculateSellingPrice());
    }
}


