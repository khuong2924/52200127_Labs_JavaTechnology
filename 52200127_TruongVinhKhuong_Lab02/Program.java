import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Program {
    private static Connection connection;
    private static ProductDAO productDAO;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Program <connection_url>");
            return;
        }

        String connectionUrl = args[0];

        try {
            connection = DriverManager.getConnection(connectionUrl);
            productDAO = new ProductDAO(connection);
            initializeDatabase();
            runMenu();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void initializeDatabase() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS ProductManagement");
            stmt.executeUpdate("USE ProductManagement");

            stmt.executeUpdate("DROP TABLE IF EXISTS Product");
            stmt.executeUpdate("CREATE TABLE Product (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255) NOT NULL, " +
                    "price DOUBLE NOT NULL)");
        }
    }

    private static void runMenu() {
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Read all products");
            System.out.println("2. Read product by ID");
            System.out.println("3. Add new product");
            System.out.println("4. Update product");
            System.out.println("5. Delete product");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    readAllProducts();
                    break;
                case 2:
                    readProductById();
                    break;
                case 3:
                    addNewProduct();
                    break;
                case 4:
                    updateProduct();
                    break;
                case 5:
                    deleteProduct();
                    break;
                case 6:
                    exitProgram();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void readAllProducts() {
        List<Product> products = productDAO.readAll();
        if (products.isEmpty()) {
            System.out.println("No products found.");
        } else {
            products.forEach(System.out::println);
        }
    }

    private static void readProductById() {
        System.out.print("Enter product ID: ");
        int id = scanner.nextInt();
        Product product = productDAO.read(id);
        if (product != null) {
            System.out.println(product);
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void addNewProduct() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        int id = productDAO.add(new Product(name, price));
        System.out.println("Product added with ID: " + id);
    }

    private static void updateProduct() {
        System.out.print("Enter product ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();  
        System.out.print("Enter new product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new product price: ");
        double price = scanner.nextDouble();
        boolean success = productDAO.update(new Product(id, name, price));
        if (success) {
            System.out.println("Product updated.");
        } else {
            System.out.println("Failed to update product.");
        }
    }

    private static void deleteProduct() {
        System.out.print("Enter product ID to delete: ");
        int id = scanner.nextInt();
        boolean success = productDAO.delete(id);
        if (success) {
            System.out.println("Product deleted.");
        } else {
            System.out.println("Failed to delete product.");
        }
    }

    private static void exitProgram() {
        System.out.println("Exiting program...");
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
