package com.firstApi.cloudVendor.config;

import java.io.IOException;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Attr;
import com.firstApi.cloudVendor.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// to invoke this filter in filter chain, only once for every request
@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	JwtService jwtService;

	@Autowired
	ApplicationContext applicationContext;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// Bearer
		// eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjaGFpdGFueWEiLCJpYXQiOjE3NDU5MzI0MTAsImV4cCI6MTc0NTkzMjQ0Nn0.EhJ-kMACKv4Wc85S23Zlb7D15Q5TW_ykcAqobZS2CLE

		String authHeader = request.getHeader("Authorization");
		String token = null;
		String userName = null;

		if (authHeader != null) {
			if (authHeader.startsWith("Bearer")) {
				token = authHeader.substring(7);
				userName = jwtService.extractUserName(token);
			}

			if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = applicationContext.getBean(CustomerUserDetailsService.class)
						.loadUserByUsername(userName);

				if (jwtService.validateToken(token, userDetails)) {
					UsernamePasswordAuthenticationToken objPasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					objPasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(objPasswordAuthenticationToken);
				}
			}
		}

		filterChain.doFilter(request, response);

	}

}
