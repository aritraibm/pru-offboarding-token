package com.pru.token.app.jwt;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/token")
public class JwtTokenApi {
	
	@Autowired
	private JwtTokenUtil util;
	
	@PostMapping("/validate-token")
	public boolean tokenValid(@RequestBody String toke) {
		return util.validateAccessToken(toke);
	}
	
	@PostMapping("/get-claim")
	public Map<String, Object> getClaim(@RequestBody String toke) {
		Claims claims=util.parseClaims(toke);
		Map<String, Object> expectedMap = new HashMap<String, Object>();
	    for(Entry<String, Object> entry : claims.entrySet()) {
	        expectedMap.put(entry.getKey() , entry.getValue());
	    }
		return expectedMap;
	}

}
