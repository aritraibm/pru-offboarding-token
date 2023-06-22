package com.pru.token.app.loginapi;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
	public ResponseEntity<?> getUser(@RequestBody LoginUserRequest request) throws MessagingException{
		System.out.println("user login");
		LoginUserResponse response=user.getUser(request);
		return ResponseEntity.ok().body(response);
	}
		
	@GetMapping("/user")
	public ResponseEntity<?> otpMatch(@RequestHeader(value="empId") String empId,
			  @RequestHeader(value="password") String password) throws MessagingException {
		System.out.println(empId+" emp otp "+password);
		String token = otps.otpVerifyToken(empId,Integer.parseInt(password));	
		return ResponseEntity.ok().body("Otp is success : "+token);
	}
}
