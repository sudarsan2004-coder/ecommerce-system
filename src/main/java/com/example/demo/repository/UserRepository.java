package com.example.demo.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByMobileAndPassword(String mobile, String password);


    // ✅ REQUIRED FOR DUPLICATE CHECK
    User findByMobile(String mobile);

    // (optional cleaner way)
    boolean existsByMobile(String mobile);
}

