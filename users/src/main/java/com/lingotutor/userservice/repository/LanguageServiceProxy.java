package com.lingotutor.userservice.repository;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "languages")
public interface LanguageServiceProxy {
	@GetMapping("/languages")
}
