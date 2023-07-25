package org.programming.controller;

import org.springframework.beans.factory.annotation.Value;
import org.programming.bean.User;
import org.programming.dto.CartCreateRequest;
import org.programming.dto.ChangeAddr;
import org.programming.dto.ChangePayDto;
import org.programming.dto.UserChangeDto;
import org.programming.security.auth.*;
import lombok.RequiredArgsConstructor;
import org.programming.service.JwtService;
import org.programming.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    @Value("${product.service.url}")
    private String productServiceUrl;

    @Autowired
    private AuthenticationService service;
    @Autowired
    private WebClient.Builder webClient;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        AuthenticationResponse authenticationResponse = service.register(request);
        Optional<User> op = userService.getByUsername(request.getUsername());
        if(op.isPresent()){
            List<? extends GrantedAuthority> collect = op.get().getAuthorities().stream().collect(Collectors.toList());
            if(collect.get(0).equals("admin")){
                return ResponseEntity.ok(authenticationResponse);
            }
            CartCreateRequest req = CartCreateRequest.builder().userid(op.get().getId()).build();
            System.out.println("sending post req to create cart");
            webClient.build()
                    .post()
                    .uri(productServiceUrl + "/cart/create")
                    .body(BodyInserters.fromValue(req))
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
        }
        return ResponseEntity.ok(authenticationResponse);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        System.out.println("hit login");
        return ResponseEntity.ok(service.login(request));
    }
    @GetMapping("/checklogin")
    public ResponseEntity<CheckLoginResponse> validToken(@RequestHeader(value="Authorization") String token){
        System.out.println(token);
        token = token.substring(7);
        String username = jwtService.extractUsername(token);
        if (username != null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtService.isTokenValid(token, userDetails)) {
                Optional<User> op = userService.getByUsername(username);
                if (op.isPresent()) {
                    User user = op.get();
                    return ResponseEntity.ok(CheckLoginResponse.builder().success(true).user(user).build());
                } else {
                    return ResponseEntity.ok(CheckLoginResponse.builder().success(false).user(null).build());
                }
            }
        }
        return ResponseEntity.ok(CheckLoginResponse.builder().success(false).user(null).build());

    }

    @PostMapping("/changepassword")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void changePassword(@RequestBody UserChangeDto userChangeDto){
        service.changePassword(userChangeDto);
    }

    @PostMapping("/changepaymethod")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void changePayment(@RequestBody ChangePayDto changePayDto){
        service.changePayment(changePayDto);
    }

    @PostMapping("/changeaddress")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void changeAddress(@RequestBody ChangeAddr changeAddr){
        service.changeAddress(changeAddr);
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsers(){
        return userService.getAll();
    }

    @GetMapping("/users/{keyword}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserByKeyword(@PathVariable String keyword){
        return userService.getUserByKeyword(keyword);
    }

    @DeleteMapping("/users/delete/{userid}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteUser(@PathVariable String userid){
        int uid = Integer.parseInt(userid);
        Optional<User> userOp = userService.getUserById(uid);
        User u = null;
        if(userOp.isPresent()){
            u = userOp.get();
        }
        //delete order
        if(u != null){
            webClient.build()
                    .delete()
                    .uri(productServiceUrl + "/order/delete/" + userid)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
        }
        //delete cart
        if(u != null){
            webClient.build()
                    .delete()
                    .uri(productServiceUrl + "/cart/delete/user/" + userid)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
        }

        //delete review
        if(u != null){
            webClient.build()
                    .delete()
                    .uri(productServiceUrl + "/deletereview/user/" + userid)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
        }
        if(u != null){
            webClient.build()
                    .delete()
                    .uri(productServiceUrl + "/favoriate/delete/user/" + userid)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
        }
        userService.deleteByUserid(uid);
    }
}


