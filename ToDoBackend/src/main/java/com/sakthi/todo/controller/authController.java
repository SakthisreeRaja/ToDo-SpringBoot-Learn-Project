package com.sakthi.todo.controller;

import com.sakthi.todo.model.User;
import com.sakthi.todo.repository.UserRepository;
import com.sakthi.todo.service.UserService;
import com.sakthi.todo.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class authController {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserService userService;
    private final JwtUtil jwtutil;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Map<String,String> body){
        String email = body.get("email");
        String password = passwordEncoder.encode(body.get("password"));
        if(userRepository.findByEmail(email).isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
//            return new ResponseEntity<>("Email already exists",HttpStatus.CONFLICT);
        }
        else{
            userService.createUser(User.builder().email(email).password(password).build());// to use this the user entity must annotate with @builder...
            return new ResponseEntity<>("Successfully Created",HttpStatus.CREATED);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String,String> body){
        String email = body.get("email");
        String password = body.get("password");
        var optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()){
            return new ResponseEntity<>("Invalid Credentials",HttpStatus.UNAUTHORIZED);
        }
        User user = optionalUser.get();
        if(!passwordEncoder.matches(password,user.getPassword())){
            return new ResponseEntity<>("Invalid Credentials",HttpStatus.UNAUTHORIZED);
        }
        String token = jwtutil.generateToken(email);
        return ResponseEntity.ok(Map.of("token",token));
    }


}
