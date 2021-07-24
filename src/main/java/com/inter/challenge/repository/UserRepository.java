package com.inter.challenge.repository;

import com.inter.challenge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for challenge.
 * 14/07/2021
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
