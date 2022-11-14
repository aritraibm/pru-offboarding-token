package com.pru.token.app.loginapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/loginuser")
public class LoginApi {
	
	@Autowired
	private AuthenticatedUser user;

	@PostMapping("/user")
	public ResponseEntity<?> getUser(@RequestBody LoginUserRequest request){
		LoginUserResponse response=user.getUser(request);
		return ResponseEntity.ok().body(response);
	}
}
