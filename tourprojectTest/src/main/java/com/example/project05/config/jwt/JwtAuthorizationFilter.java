package com.example.project05.config.jwt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.project05.config.auth.PrincipalDetails;
import com.example.project05.controller.dto.CMRespDto;
import com.example.project05.model.user.User;
import com.example.project05.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

// 시큐리티가 filter를 가지고 있는데 그 필터중에
// BasicAuthenticationFilter 라는 게 있음
// 권한이나 인증이 필요한 특정 주소를 요청했을때
// 위 필터를 무조건 타게 되어있다.
// 만약 권한이나 인증이 필요한 주소가 아니면
// 이 필터를 타지 않는다. (=> 테스트 결과 :  corsconfig로 주소를 한번 걸러서 왔기 때문인진 모르겠지만 이 필터는 모든 주소가 거친다.)
public class JwtAuthorizationFilter extends BasicAuthenticationFilter{

	private UserRepository userRepository;
	
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager,UserRepository userRepository) {
		super(authenticationManager);
		this.userRepository=userRepository;
	}
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("인증여부 상관없이 필터탐");
		
		String jwtHeader = request.getHeader(JwtProps.HEADER);
		System.out.println("jwtHeader : "+jwtHeader);
		
		// 헤더가 있는지 확인
		if (jwtHeader == null || !jwtHeader.startsWith("Bearer ")) {
			chain.doFilter(request, response);
			return;
		}
		String username = null;
		// 토큰 검증.
		String jwtToken = request.getHeader(JwtProps.HEADER).replace(JwtProps.AUTH, "");
		//System.out.println("==============================걸리는부분");
		try { // 토큰 검증이 필요없는 주소가 만료된 토큰을 들고왔을 때 처리하는 곳
			username = JWT.require(Algorithm.HMAC512(JwtProps.SECRET))
					.build().verify(jwtToken).getClaim("username")
					.asString();
		} catch (Exception e) {
			chain.doFilter(request, response);
			return ;
		}
		
		// 서명이 정상적으로 됨
		if (username != null) {
			User userEntity = userRepository.findByUsername(username);
			
			PrincipalDetails principalDetails = new PrincipalDetails(userEntity);
			//jwt 토큰 서명을 통해 서명이 정상이면 authentication 객체를 만들어준다.
				Authentication authentication = 
						new UsernamePasswordAuthenticationToken(principalDetails,null,principalDetails.getAuthorities());
				// 강제로 시큐리티의 세션에 접근해서 authentication 저장
				SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		chain.doFilter(request, response);
		
	}

}
