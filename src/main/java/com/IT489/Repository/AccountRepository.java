package com.IT489.Repository;

import com.IT489.Model.Accounts;
import com.IT489.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Integer> {

    Optional<Accounts> findByUserId(Integer userId);
//    @Query(value = "SELECT * FROM Accounts WHERE userId = ?1", nativeQuery = true)
//    Accounts findAllAccountsByUserId(Integer userId);
}
