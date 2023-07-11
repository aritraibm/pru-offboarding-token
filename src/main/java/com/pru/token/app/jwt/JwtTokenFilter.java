package com.pru.token.app.jwt;

import com.pru.token.app.user.Role;
import com.pru.token.app.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class JwtTokenFilter extends OncePerRequestFilter {
	@Autowired
	private JwtTokenUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
				HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (!hasAuthorizationBearer(request)) {
			filterChain.doFilter(request, response);
			return;
		}

		String token = getAccessToken(request);

		setAuthenticationContext(token, request);
		filterChain.doFilter(request, response);
	}

	private boolean hasAuthorizationBearer(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		return !ObjectUtils.isEmpty(header) && header.startsWith("Bearer");
	}

	private String getAccessToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		return header.split(" ")[1].trim();
	}

	private void setAuthenticationContext(String token, HttpServletRequest request) {
		UserDetails userDetails = getUserDetails(token);

		UsernamePasswordAuthenticationToken 
			authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

		authentication.setDetails(
				new WebAuthenticationDetailsSource().buildDetails(request));

		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private UserDetails getUserDetails(String token) {
		User userDetails = new User();
		Claims claims = Jwts.parserBuilder().setSigningKey(jwtUtil.getSignKey()).build().parseClaimsJws(token).getBody();
		String subject = (String) claims.get(Claims.SUBJECT);
		String roles = (String) claims.get("roles");
		
		roles = roles.replace("[", "").replace("]", "");
		String[] roleNames = roles.split(",");
		
		for (String aRoleName : roleNames) {
			userDetails.setRole(new Role(aRoleName));
		}
		
		String[] jwtSubject = subject.split(",");

		userDetails.setId(jwtSubject[0]);
		userDetails.setEmail(jwtSubject[1]);

		return userDetails;
	}
}
