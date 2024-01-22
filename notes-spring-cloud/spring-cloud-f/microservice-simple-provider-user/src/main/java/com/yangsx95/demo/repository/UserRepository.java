package com.yangsx95.demo.repository;

import com.yangsx95.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yangsx
 * @version 1.0
 * @date 2019/5/8
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
