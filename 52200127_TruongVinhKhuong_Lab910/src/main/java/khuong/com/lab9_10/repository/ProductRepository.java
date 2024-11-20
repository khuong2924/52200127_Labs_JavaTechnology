package khuong.com.lab9_10.repository;

import khuong.com.lab9_10.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {}