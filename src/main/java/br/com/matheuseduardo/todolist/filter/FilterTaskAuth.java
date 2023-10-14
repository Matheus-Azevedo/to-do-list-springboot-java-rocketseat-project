package br.com.matheuseduardo.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.matheuseduardo.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

  @Autowired
  private IUserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
        // Get the path
    var path = request.getServletPath();
    // Check if the path is /users
    if (path.startsWith("/tasks/")) {
      // Get authentication
      var authorization = request.getHeader("Authorization");
  
      if (authorization == null || !authorization.startsWith("Basic")) {
        response.sendError(HttpStatus.UNAUTHORIZED.value());
        return;
      }
  
      var base64 = authorization.substring("Basic".length()).trim();
      byte[] authDecoded = Base64.getDecoder().decode(base64);
      var username = new String(authDecoded).split(":")[0];
      var password = new String(authDecoded).split(":")[1];
      // Valid user
      var user = userRepository.findByUsername(username);
  
      if (user == null) {
        response.sendError(HttpStatus.UNAUTHORIZED.value());
        return;
      } else {
        // Valid password
        var validation = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
        if (!validation.verified) {
          response.sendError(HttpStatus.UNAUTHORIZED.value());
          return;
        }
        // Follow the flow
        request.setAttribute("idUser", user.getId());
        filterChain.doFilter(request, response);
      }
    }
    // Follow the flow
    filterChain.doFilter(request, response);
  }
}
