package com.pru.token.app.loginapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.pru.token.app.user.LogoutUserToken;

@CrossOrigin
@RestController
@RequestMapping("/logout")
public class LogoutApi {
	
	@Autowired
	private LogoutUtil util;
	
	@PreAuthorize("hasAnyRole({'ROLE_ASSOCIATE','ROLE_OFFBOARDING_REVIEWER','ROLE_OFFBOARDING_MANAGER'})")
	@GetMapping("/token")
	public ResponseEntity<?> logout(@RequestHeader("Authorization") String authorization){
		boolean status = util.update(authorization.substring(7));
		return ResponseEntity.ok().body(status);
	}

}