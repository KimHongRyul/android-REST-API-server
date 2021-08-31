package com.example.project05.config.jwt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.project05.config.auth.PrincipalDetails;
import com.example.project05.controller.dto.CMRespDto;
import com.example.project05.controller.dto.LoginReqDto;
import com.example.project05.controller.dto.SignupDto;
import com.example.project05.model.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		LoginReqDto loginReqDto = new LoginReqDto();
		// 1. 유저네임 패스워드 받아서
		// 2. 정상인지 로그인 시도를 해본다.
		try {
			ObjectMapper om = new ObjectMapper();
			User user = om.readValue(request.getInputStream(), User.class);
			System.out.println(user);
			loginReqDto.setUsername(user.getUsername());
			loginReqDto.setPassword(user.getPassword());
		
		
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				loginReqDto.getUsername(), loginReqDto.getPassword());

		// principalDetailsService의 loadUserByUsername() 함수가 실행되고 정상이면
		// authentication리턴해줌
		// 디비에 있는 유저네임 패스워드가 일치한다는 말과 같다.
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		// authentication에 로그인한 정보가 저장됨.
		
		PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
		System.out.println("로그인 완료 : " + principalDetails.getUser().getUsername());
		return authentication;
		} catch (Exception e) {
			return null;
		}
		// 리턴하면 authentication가 세션에 저장됨.
		// 리턴하는이유? 권한 관리를 시큐리티가 대신 해주기 때문에 편할려고
		// 굳이 jwt 사용하면서 세션에 넣는 이유는 권한 관리 때문에

	}

	// attemptAuthentication 에서 인증이 제대로 되었으면 해당 함수가 실행됨.
	// jwt 토큰을 여기서 만들어서 리퀘스트 요청한 사용자에게 토큰을 응답하면 됨
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		System.out.println("successfulAuthentication : 인증 완료 ");

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

		// RSA 방식이 아니고 Hash 암호방식
		String jwtToken = JWT.create().withSubject(JwtProps.SUBJECT)
				.withExpiresAt(new Date(System.currentTimeMillis() + JwtProps.EXPIRESAT))
				.withClaim("id", principalDetails.getUser().getUserId())
				.withClaim("username", principalDetails.getUser().getUsername())
				.sign(Algorithm.HMAC512(JwtProps.SECRET));

		resp.setContentType("application/json; charset=utf-8");
		resp.addHeader(JwtProps.HEADER, JwtProps.AUTH + jwtToken);

		// json으로 보내기
		ObjectMapper om = new ObjectMapper();
		User userEntity = new User(principalDetails.getUser().getUserId(), principalDetails.getUser().getUsername(),
				principalDetails.getUser().getEmail());

		CMRespDto<User> cmRespDto = new CMRespDto<User>(1, "auth success", userEntity);
		String cmRespDtoJson = om.writeValueAsString(cmRespDto);
		PrintWriter out = response.getWriter();
		out.print(cmRespDtoJson); // CMRespDto
		out.flush();
	}

}
