package com.floxie.auth.infrastructure.config.security.filters;

import com.floxie.auth.infrastructure.config.security.services.AccessTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.commons.feature.shared.utils.GsonWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

  private static final GsonWrapper GSON_WRAPPER = new GsonWrapper();
  private final UserDetailsService userDetailsService;
  private final AccessTokenService jwtUtil;

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain chain)
      throws IOException, ServletException {
    var authorizationHeader = request.getHeader("Authorization");

    if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
      chain.doFilter(request, response);
      return;
    }

    try {
      String jwt = authorizationHeader.substring(7);
      boolean isTokenValid = jwtUtil.isAccessTokenValid(jwt);

      if (isTokenValid && SecurityContextHolder.getContext().getAuthentication() == null) {
        var email = jwtUtil.getEmailFromToken(jwt);

        var userDetails = userDetailsService.loadUserByUsername(email);

        var authToken =
            new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }

    } catch (Exception e) {
      handleUsernameNotFoundException(response, e);
      return;
    }

    chain.doFilter(request, response);
  }

  private void handleUsernameNotFoundException(HttpServletResponse response, Exception e)
      throws IOException {
    ProblemDetail problemDetail =
        ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, e.getMessage());
    problemDetail.setTitle("User Not Found");

    response.setStatus(HttpStatus.FORBIDDEN.value());
    response.setContentType("application/json");

    String json = GSON_WRAPPER.toJson(problemDetail);

    response.getWriter().write(json);
  }
}
