package com.bookstore.bookstoreapplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookstore.bookstoreapplication.model.BookModel;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Integer> {

	@Query(value="Select * from book_model where book_name LIKE :bookName%",nativeQuery = true)
	Optional<BookModel> findByBookName(String bookName);

}
