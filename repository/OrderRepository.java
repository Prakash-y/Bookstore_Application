package com.bookstore.bookstoreapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.bookstoreapplication.model.OrderModel;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Integer> {

}
