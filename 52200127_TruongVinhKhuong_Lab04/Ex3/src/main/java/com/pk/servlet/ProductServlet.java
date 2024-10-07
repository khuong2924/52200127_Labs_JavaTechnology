package com.pk.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pk.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static List<Product> productList = new ArrayList<>();
    private static int productIdCounter = 1;

    public void init() throws ServletException {
        productList.add(new Product(productIdCounter++, "san pham A", 100));
        productList.add(new Product(productIdCounter++, "san pham B", 120));
        productList.add(new Product(productIdCounter++, "san pham C", 400));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();

        String idParam = request.getParameter("id");
        if (idParam == null) {
            out.print(mapper.writeValueAsString(createResponse(0, "Danh sách sản phẩm", productList)));
        } else {
            try {
                int id = Integer.parseInt(idParam);
                Product product = getProductById(id);
                if (product != null) {
                    out.print(mapper.writeValueAsString(createResponse(0, "Thông tin sản phẩm", product)));
                } else {
                    out.print(mapper.writeValueAsString(createResponse(1, "Sản phẩm không tồn tại", null)));
                }
            } catch (NumberFormatException e) {
                out.print(mapper.writeValueAsString(createResponse(1, "ID không hợp lệ", null)));
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();

        // Đọc dữ liệu từ request
        try {
            Product product = new ObjectMapper().readValue(request.getReader(), Product.class);
            if (product.getId() <= 0 || product.getPrice() <= 0 || getProductById(product.getId()) != null) {
                out.print(mapper.writeValueAsString(createResponse(1, "Thông tin sản phẩm không hợp lệ hoặc ID đã tồn tại", null)));
                return;
            }
            productList.add(product);
            productIdCounter++;
            out.print(mapper.writeValueAsString(createResponse(0, "Sản phẩm đã được thêm thành công", product)));
        } catch (Exception e) {
            out.print(mapper.writeValueAsString(createResponse(1, "Lỗi khi thêm sản phẩm", null)));
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();

        // Đọc dữ liệu từ request
        try {
            Product product = new ObjectMapper().readValue(request.getReader(), Product.class);
            Product existingProduct = getProductById(product.getId());
            if (existingProduct == null) {
                out.print(mapper.writeValueAsString(createResponse(1, "Sản phẩm không tồn tại", null)));
                return;
            }
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            out.print(mapper.writeValueAsString(createResponse(0, "Sản phẩm đã được cập nhật thành công", existingProduct)));
        } catch (Exception e) {
            out.print(mapper.writeValueAsString(createResponse(1, "Lỗi khi cập nhật sản phẩm", null)));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();

        String idParam = request.getParameter("id");
        if (idParam == null) {
            out.print(mapper.writeValueAsString(createResponse(1, "ID không được cung cấp", null)));
            return;
        }
        try {
            int id = Integer.parseInt(idParam);
            Product product = getProductById(id);
            if (product != null) {
                productList.remove(product);
                out.print(mapper.writeValueAsString(createResponse(0, "Sản phẩm đã được xóa thành công", null)));
            } else {
                out.print(mapper.writeValueAsString(createResponse(1, "Sản phẩm không tồn tại", null)));
            }
        } catch (NumberFormatException e) {
            out.print(mapper.writeValueAsString(createResponse(1, "ID không hợp lệ", null)));
        }
    }

    private Product getProductById(int id) {
        return productList.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    private Map<String, Object> createResponse(int errorCode, String message, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("id", errorCode);
        response.put("message", message);
        response.put("data", data);
        return response;
    }
}
