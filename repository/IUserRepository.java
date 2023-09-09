package com.bookstore.bookstoreapplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookstore.bookstoreapplication.model.UserModel;

@Repository
public interface IUserRepository extends JpaRepository<UserModel, Integer>{

	@Query(value="Select * from user_model where email =:email",nativeQuery = true)
	Optional<UserModel> findByEmail(String email);


	 public UserModel findByUserId(int userId);
}
