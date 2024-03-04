package com.wolfred.gym.Controllers;

import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wolfred.gym.Config.Auth.TokenProvider;
import com.wolfred.gym.Dto.SignInDto;
import com.wolfred.gym.Dto.SignUpDto;
import com.wolfred.gym.Enums.ErrorResponse;
import com.wolfred.gym.Models.User;
import com.wolfred.gym.Response.ResponseUtil;
import com.wolfred.gym.Service.AuthService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping("/api")
@CrossOrigin
@Validated // Necesario para habilitar la validación de parámetros
public class AuthController {
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private AuthService service;
  @Autowired
  private TokenProvider tokenService;


  @PostMapping("/signup")
  public ResponseEntity<?> signUp(@RequestBody @Valid SignUpDto data, BindingResult result) {
    if(result.hasErrors()){
        return ResponseUtil.statusBadRequestResponse(result);
    }
    if(service.signUp(data) == null){
        return ResponseUtil.statusConflictResponse(ErrorResponse.UserWithProvidedEmailAlreadyExists.getMessage());
    }
    return ResponseUtil.statusCreatedResponse();
  }

  @PostMapping("/login")
  public ResponseEntity<?> signIn(@RequestBody @Valid SignInDto data, BindingResult result) throws AuthenticationException,Exception{
    if(result.hasErrors()){
        return ResponseUtil.statusBadRequestResponse(result);
    }
    Map<String, Object> response = new HashMap<>();
    response.clear();
    var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
    try {
        var authUser = authenticationManager.authenticate(usernamePassword);
        User user = (User)authUser.getPrincipal();
        var accessToken = tokenService.generateAccessToken(user);
        response.put("user_id", user.getId());
        response.put("accessToken", accessToken);
        response.put("role", user.getRole());
        return ResponseUtil.statusOkResponse(response);
    } catch (AuthenticationException e) {
        return ResponseUtil.statusUnauthorizedResponse("Email or Password incorrect");
    } catch (Exception ex) {
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
    }
  }
  @GetMapping("/check/availableEmail/{email}")
    public ResponseEntity<?> getUser(@PathVariable("email") 
                               @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$", 
                                        message = "Invalid email format") 
                               String email) throws Exception {
      return ResponseUtil.statusOkResponse(service.loadUserByUsername(email) == null);
    }
    
}
