package com.pru.token.app.loginapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pru.token.app.otp.OtpService;

@CrossOrigin
@RestController
@RequestMapping("/loginuser")
public class LoginApi {
	
	@Autowired
	private AuthenticatedUser user;
		
	@Autowired
	private OtpService otps;

	@PostMapping("/user")
	public ResponseEntity<?> getUser(@RequestBody LoginUserRequest request){
		LoginUserResponse response=user.getUser(request);
		return ResponseEntity.ok().body(response);
	}
		
	@GetMapping("/otpverify/{empid}/{otp}")
	public ResponseEntity<?> otpVerify(@PathVariable("empid") String empid,@PathVariable("otp") int otp){
		System.out.println(empid+" emp otp "+otp);
		String token = otps.otpVerifyToken(empid,otp);		
		return ResponseEntity.ok().body("Otp is success : "+token);
	}
}
