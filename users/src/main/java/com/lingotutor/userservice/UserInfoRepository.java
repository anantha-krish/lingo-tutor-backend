package com.lingotutor.userservice;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; 

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> { 
	Optional<UserInfo> findByName(String username); 
}
