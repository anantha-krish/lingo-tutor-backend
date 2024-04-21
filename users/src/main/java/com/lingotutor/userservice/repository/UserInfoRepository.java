package com.lingotutor.userservice.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lingotutor.userservice.entity.UserInfo; 

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> { 
	Optional<UserInfo> findByUsername(String username); 
	Optional<List<UserInfo>> findAllByRolesContaining(String role);
}
