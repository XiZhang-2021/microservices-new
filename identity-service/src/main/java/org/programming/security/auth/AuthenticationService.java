package org.programming.security.auth;

import jakarta.transaction.Transactional;
import org.programming.bean.Profile;
import org.programming.bean.User;
import org.programming.dao.ProfileDao;
import org.programming.dao.UserDao;
import org.programming.dto.ChangeAddr;
import org.programming.dto.ChangePayDto;
import org.programming.dto.UserChangeDto;
import org.programming.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    private UserDao userDao;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ProfileDao profileDao;
    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {
        if (request.getPassword() == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setCity(request.getCity());
        user.setState(request.getState());
        user.setCardnumber(request.getCardnumber());
        user.setZip(request.getZip());
        user.setCvv(request.getCvv());
        user.setValidateexpdate(request.getValidateexpdate());
        System.out.println(request.getType());
        Profile profile = profileDao.findByType(request.getType());
        if (profile == null) {
            throw new IllegalArgumentException("No profile found with type: " + request.getType());
        }
        user.setProfile(profile);

        userDao.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).success(true).user(user).build();
    }
    @Transactional
    public AuthenticationResponse login(AuthenticationRequest request)  {
        System.out.println("hit authenticate");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var op = userDao.findByUsername(request.getUsername());
        if(op.isPresent()){
            User user = op.get();
            var jwtToken = jwtService.generateToken(user);
//            Profile profile = user.getProfile();
//            List<Profile> list = new ArrayList<>();
//            list.add(profile);
//            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                    user,
//                    null,
//                    list
//            );
//            SecurityContextHolder.getContext().setAuthentication(authToken);
            return AuthenticationResponse.builder().token(jwtToken).success(true).user(user).build();
        }
        return null;
    }

    @Transactional
    public void changePassword(UserChangeDto userChangeDto){
        String oldPassword = userChangeDto.getPassword();
        String newPassword = passwordEncoder.encode(userChangeDto.getNewPassword());
        String username = userChangeDto.getUsername();
        Optional<User> userOp = userDao.findByUsername(username);
        if(userOp.isPresent()){
            User user = userOp.get();
            if(!passwordEncoder.matches(oldPassword, user.getPassword())){
                throw new RuntimeException("password is not right");
            }else{
                user.setPassword(newPassword);
                userDao.save(user);
            }
        }
    }

    @Transactional
    public void changePayment(ChangePayDto changePayDto) {
        Optional<User> userOp = userDao.findById(changePayDto.getUserid());
        if(userOp.isPresent()){
            User user = userOp.get();
            user.setCardnumber(changePayDto.getCardnumber());
            user.setValidateexpdate(changePayDto.getExpiredate());
            user.setCvv(changePayDto.getCvv());
            userDao.save(user);
        }else{
            throw new RuntimeException("no such user!");
        }
    }
    @Transactional
    public void changeAddress(ChangeAddr changeAddr) {
        Optional<User> userOp = userDao.findById(changeAddr.getUserid());
        if(userOp.isPresent()){
            User user = userOp.get();
            user.setAddress(changeAddr.getAddress());
            user.setZip(changeAddr.getZip());
            user.setCity(changeAddr.getCity());
            user.setState(changeAddr.getState());
            user.setPhone(changeAddr.getPhone());
            userDao.save(user);
        }else{
            throw new RuntimeException("no such user!");
        }
    }
}
