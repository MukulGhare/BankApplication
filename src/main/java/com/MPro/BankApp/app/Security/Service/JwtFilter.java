package com.MPro.BankApp.app.Security.Service;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String token=null;
        String identityNo=null;

        if( authHeader != null && authHeader.startsWith("Bearer")){
            token = authHeader.substring(7);
            identityNo = jwtService.getIdentityNo(token);
        }

        if( identityNo != null && SecurityContextHolder.getContext().getAuthentication() == null){

            UserDetails userDetails = myUserDetailsService.loadUserByUsername(identityNo);
            if( jwtService.validateToken(token,userDetails )){

                // if token is validated send to next filter
                UsernamePasswordAuthenticationToken uPAToken = new
                        UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                uPAToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // setting authentication in securityContext
                SecurityContextHolder.getContext().setAuthentication(uPAToken);

            }

        }

        filterChain.doFilter(request,response);
    }
}
