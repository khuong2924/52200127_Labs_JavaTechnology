package com.pk.servlet;

import com.pk.model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/")
public class ProductServlet extends HttpServlet {
    private final String JDBC_URL = "jdbc:mysql://localhost:3306/lab5_java?useSSL=false&serverTimezone=UTC";
    private final String USERNAME = "root";
    private final String PASSWORD = "123456";
    private final String driver = "com.mysql.cj.jdbc.Driver";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = getProducts();
        request.setAttribute("products", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        try {
            Class.forName(driver);  // Tải driver MySQL
            Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM products");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                products.add(product);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return products;
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action != null ? action.toLowerCase() : "") {
            case "add":
                addProduct(request);
                break;
            case "edit":
                editProduct(request);
                break;
            case "delete":
                deleteProduct(request);
                break;
            default:
                break;
        }

        doGet(request, response);
    }

    private void addProduct(HttpServletRequest request) {
        int newId = getMaxProductId() + 1;
        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");
        //check
        System.out.println("Adding product with ID: " + newId + ", Name: " + name + ", Price: " + priceStr);

        if (isValidProductData(name, priceStr)) {
            double price = Double.parseDouble(priceStr);
            String sql = "INSERT INTO products (id, name, price) VALUES (?, ?, ?)";
            executeUpdate(sql,newId, name, price);
        } else {
            System.out.println("Invalid product data.");
        }
    }

    private int getMaxProductId() {
        int maxId = 0;
        String sql = "SELECT MAX(id) AS max_id FROM products";

        try {
            Class.forName(driver);  //load driver trong trycatch phía ngoài của getConn
            System.out.println("Driver loaded successfully.");
            try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    maxId = rs.getInt("max_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Driver class not found: " + e.getMessage());
            e.printStackTrace();
        }

        return maxId;
    }


    private void editProduct(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");

        if (isValidProductData(name, priceStr)) {
            double price = Double.parseDouble(priceStr);
            String sql = "UPDATE products SET name = ?, price = ? WHERE id = ?";
            executeUpdate(sql, name, price, id);
        }
    }

    private void deleteProduct(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String sql = "DELETE FROM products WHERE id = ?";
        executeUpdate(sql, id);
    }

    private boolean isValidProductData(String name, String priceStr) {
        return name != null && !name.isEmpty() && priceStr != null && !priceStr.isEmpty();
    }

    private void executeUpdate(String sql, Object... params) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement pstate = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                pstate.setObject(i + 1, params[i]);
            }
            int rowsAffected = pstate.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
