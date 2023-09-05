package com.pru.token.app.loginapi;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pru.token.app.user.LogoutUserToken;
import com.pru.token.app.user.LogoutUserTokenRepository;

@Component
public class LogoutUtil {
	
	@Autowired
	private LogoutUserTokenRepository lg;
	
	public boolean update(String token) {
		Optional<LogoutUserToken> opl = lg.findByToken(token);
		if(opl.isPresent()) {
			LogoutUserToken lu= opl.get();
			lu.setLogout(true);
			lg.save(lu);
			return true;
		}
		return false;
	}
	
//	public LogoutUserToken getUsrLogout(String token) {
//		Optional<LogoutUserToken> opl = lg.findByToken(token);
//		if(opl.isPresent()) {
//			System.out.println("opl "+opl.get());
//			return opl.get();
//		}
//		System.out.println("not prsnt ");
//		return null;
//	}

}
