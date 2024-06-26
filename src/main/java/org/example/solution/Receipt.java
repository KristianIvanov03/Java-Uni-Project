package org.example.solution;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Receipt {
    private static int receiptCounter = 0;
    private final int receiptNumber;
    private final Cashier cashier;
    private final LocalDateTime dateTime;
    private final List<Sale> sales;
    private final double totalAmount;

    public Receipt(Cashier cashier, List<Sale> sales) {
        this.receiptNumber = generateReceiptNumber();
        this.cashier = cashier;
        this.dateTime = LocalDateTime.now();
        this.sales = sales;
        this.totalAmount = calculateTotalAmount();
    }

    private synchronized int generateReceiptNumber() {
        return receiptCounter++;
    }

    private double calculateTotalAmount() {
        return sales.stream()
                .mapToDouble(sale -> sale.getProduct().calculateSellingPrice() * sale.getQuantity())
                .sum();
    }

    public int getReceiptNumber() {
        return receiptNumber;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void saveToFile() {
        String directoryPath = "src/main/java/org/example/solution/receipts";
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String filename = directoryPath + "/receipt_" + receiptNumber + ".txt";
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(toString());
        } catch (IOException e) {
            System.err.println("Error writing receipt to file: " + e.getMessage());
        }
    }
    public static void printReceiptFromFile(int receiptNumber) {
        String directoryPath = "src/main/java/org/example/solution/receipts";
        String filename = directoryPath + "/receipt_" + receiptNumber + ".txt";
        File file = new File(filename);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error reading receipt file: " + e.getMessage());
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Receipt Number: ").append(receiptNumber).append("\n");
        sb.append("Cashier: ").append(cashier.getName()).append("\n");
        sb.append("Date and Time: ").append(dateTime).append("\n");
        sb.append("Sales:\n");
        for (Sale sale : sales) {
            sb.append(" - ").append(sale.getProduct().getName())
                    .append(", Quantity: ").append(sale.getQuantity())
                    .append(", Price per unit: ").append(sale.getProduct().calculateSellingPrice())
                    .append("\n");
        }
        sb.append("Total Amount: ").append(totalAmount).append("\n");
        return sb.toString();
    }
}
