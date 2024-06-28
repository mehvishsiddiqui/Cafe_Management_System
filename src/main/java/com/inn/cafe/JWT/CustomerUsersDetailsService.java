package com.inn.cafe.JWT;

import java.util.ArrayList;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.inn.cafe.dao.UserDao;
import com.inn.cafe.rest.UserRest;


@Service
public class CustomerUsersDetailsService implements UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(UserRest.class); // this is working instead of the Slf4J
	
	@Autowired
	UserDao userDao;
	
	private  com.inn.cafe.POJO.User userDetail;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("Inside loadUserByName {}" , username);
         userDetail = userDao.findByEmailId(username);
         if(!Objects.isNull(userDetail)) {
            return new User (userDetail.getEmail(), userDetail.getPassword() , new ArrayList<>());}
            else {
            	
                throw new UsernameNotFoundException("User  Not Found.");}
	}
	
	
	public com.inn.cafe.POJO.User getUserDetail(){
		return userDetail;
	}

}
