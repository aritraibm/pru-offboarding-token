package com.pru.token.app.otp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pru.token.app.user.LogoutUserToken;
import com.pru.token.app.user.LogoutUserTokenRepository;
import com.pru.token.app.user.User;
import com.pru.token.app.user.UserRepository;

@Service
public class OtpService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private LogoutUserTokenRepository logoutUserRepo;

	public String otpVerifyToken(String empid,int otp) {
		Optional<User> ouser= repository.findByEmployeeId(empid);
		Optional<LogoutUserToken> lop= logoutUserRepo.findByEmpId(empid);
		if(ouser.isPresent() && lop.isPresent()) {
			LogoutUserToken lut = lop.get();
			if(ouser.get().getOtp()==otp) {
				System.out.println("Otp verified ..."+ouser.get().getOtp());
				return lut.getToken();
			}else {
				System.out.println("Otp is not matching.....");
				lut.setLogout(true);
				logoutUserRepo.save(lut);
				return "Otp is not matching.....";
			}
		}
		
		return null;
	}
	
	public void updateOtp(String empid,int otp) {
		Optional<User> ouser= repository.findByEmployeeId(empid);
		User user = ouser.get();
		user.setOtp(otp);
		repository.save(user);
		System.out.println("otp updated...."+otp);
	}
	
	
}
