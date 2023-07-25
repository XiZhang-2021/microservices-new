package org.programming.service;

import jakarta.transaction.Transactional;
import org.programming.bean.User;
import org.programming.dao.ProfileDao;
import org.programming.dao.UserDao;
import org.programming.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private ProfileDao profileDao;

    public List<User> getAll(){
        return userDao.findAll();
    }
    @Transactional
    public Optional<User> getByUsername(String username){
        return userDao.findByUsername(username);
    }
    @Transactional
    public User getUserByKeyword(String keyword) {
        try{
            int id = Integer.parseInt(keyword);
            Optional<User> useridOp = userDao.findById(id);
            if(useridOp.isPresent()){
                return useridOp.get();
            }else{
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        Optional<User> userOp = userDao.findByUsername(keyword);
        if(userOp.isPresent()){
            return userOp.get();
        }else{
            return null;
        }
    }
//    @Transactional
//    public void deleteById(int id) {
//        userDao.deleteById(id);
//    }
    @Transactional
    public Optional<User> getUserById(int userid) {
        return userDao.findById(userid);
    }

    @Transactional
    public void deleteByUserid(int uid) {
        userDao.deleteByUserid(uid);
    }

}
