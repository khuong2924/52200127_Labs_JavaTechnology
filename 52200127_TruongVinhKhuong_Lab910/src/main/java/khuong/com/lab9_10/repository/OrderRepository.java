package khuong.com.lab9_10.repository;

import khuong.com.lab9_10.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {}
