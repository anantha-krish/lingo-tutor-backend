package com.lingotutor.userservice.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lingotutor.userservice.entity.UserInfo; 

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> { 
	Optional<UserInfo> findByName(String username); 
}
