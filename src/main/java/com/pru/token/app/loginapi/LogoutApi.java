package com.pru.token.app.loginapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pru.token.app.user.LogoutUserToken;

@CrossOrigin
@RestController
@RequestMapping("/logout")
public class LogoutApi {
	
	@Autowired
	private LogoutUtil util;
	
	@PostMapping("/token")
	public ResponseEntity<?> logout(@RequestParam("token") String token){
		boolean b = util.update(token);
		return ResponseEntity.ok().body(b);
	}
	
	@PostMapping("/out")
	public ResponseEntity<?> getLogoutToken(@RequestBody String token){
		System.out.println("called getlogout..."+token);
		LogoutUserToken lt= util.getUsrLogout(token);
		System.out.println("logout in getl "+lt);
		return ResponseEntity.ok().body(lt);
	}

}
