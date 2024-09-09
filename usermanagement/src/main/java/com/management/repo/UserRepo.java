package com.management.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.entity.User;
@Repository
public interface UserRepo extends JpaRepository<User ,Integer > {
	public User findByEmail(String email);
	public User findByEmailAndPwd(String email,String pwd);

}
