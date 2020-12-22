package br.com.fiap.api.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private JwtToken jwtTokenUtil;
    private JwtUserDetails userDetailService;

    public JwtRequestFilter(JwtToken jwtTokenUtil, JwtUserDetails userDetailService){
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailService = userDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String token = request.getHeader("Authorization");
        String username = null;
        if(token != null && token.startsWith("Bearer ")){
            try{
                username = jwtTokenUtil.getUsernameFromToken(token);
            } catch (IllegalArgumentException illegalArgumentException){
                logger.info(illegalArgumentException.getMessage());
            } catch (ExpiredJwtException expiredJwtException){
                logger.info(expiredJwtException.getMessage());
            }
        }else{
            logger.warn("Token inexistente ou token não Bearer");
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()); // roles

            userToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(userToken);
        }

        filterChain.doFilter(request, response);
    }
}
