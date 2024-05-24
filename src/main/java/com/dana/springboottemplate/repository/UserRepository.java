package com.dana.springboottemplate.repository;

import com.dana.springboottemplate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	@Query(value = "SELECT * FROM user u WHERE u.email = ?1 OR u.phone_number = ?1 ", nativeQuery = true)
	Optional<User> findByUsername(String username);

	Optional<User> findByEmailOrPhoneNumber(String email, String phoneNumber);

}
