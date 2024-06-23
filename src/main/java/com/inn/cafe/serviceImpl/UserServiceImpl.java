package com.inn.cafe.serviceImpl;

import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.inn.cafe.POJO.User;
import com.inn.cafe.constents.CafeConstants;
import com.inn.cafe.dao.UserDao;
import com.inn.cafe.rest.UserRest;
import com.inn.cafe.service.UserService;
import com.inn.cafe.utils.CafeUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService{
	
	
	private static final Logger log = LoggerFactory.getLogger(UserRest.class);

	
	@Autowired
	UserDao userDao;
	
	@Override
	public ResponseEntity<String> signUp(Map<String, String> requestMap) {
	log.info("Inside signUp {}" , requestMap);
	try {
	 if(validateSignUpMap(requestMap)) {
		 User user = userDao.findByEmailId(requestMap.get("email"));
		 if(Objects.isNull(user)) {
			 userDao.save(getUserFromMap(requestMap));
			 
			 return CafeUtils.getResponseEntity("Successfully registered.", HttpStatus.OK);
		 }
		 else {
			 return CafeUtils.getResponseEntity("Email already exits. " , HttpStatus.BAD_REQUEST);
		 }
	}else {
		return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
	}
	 
  }catch(Exception ex) {
	
	  ex.printStackTrace();
}
	return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
	}
	
	private boolean validateSignUpMap(Map<String , String> requestMap) {
		if (requestMap.containsKey("name") && requestMap.containsKey("contactNumber") 
		&& requestMap.containsKey("email") && requestMap.containsKey("password"))
		{
			return true;
		}
		return false;
	    }
	
	 private User getUserFromMap(Map<String , String> requestMap) {
		 User user  = new User();
		 user.setName(requestMap.get("name"));
		 user.setConatctNumber(requestMap.get("conatactNumber"));
		 user.setEmail(requestMap.get("email"));
		 user.setPassword(requestMap.get("password"));
		 user.setStatus("false");
		 user.setRole("user");
		 
		 return user;
		 
	 }
	
	
	
	
	
	
	
	
	
	}
