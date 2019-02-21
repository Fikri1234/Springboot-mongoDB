package com.example.demo.DALImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DAL.UserProfilesDAL;
import com.example.demo.Entity.UserProfilesEntity;
import com.example.demo.Service.UserProfilesService;

@Service("UserProfilesService")
@Transactional
public class UserProfilesDALImpl implements UserProfilesService{
	
	@Autowired
	UserProfilesDAL userProfilesDAL;
	
	@Override
	public Optional<UserProfilesEntity> findById(String id) {
		return userProfilesDAL.findById(id);
	}

	@Override
	public Optional<UserProfilesEntity> findByUsername(String name) {
		return userProfilesDAL.findByName(name);
	}
	
	@Override
	public void saveUserProfiles(UserProfilesEntity userProfilesEntity) {
		userProfilesDAL.save(userProfilesEntity);
	}
	
	@Override
	public void updateUserProfiles(UserProfilesEntity userProfilesEntity) {
		saveUserProfiles(userProfilesEntity);
	}
	
	@Override
	public void deleteUserProfilesById(String id) {
		userProfilesDAL.deleteById(id);
	}
	
	@Override
	public void deleteUserProfilesAll() {
		userProfilesDAL.deleteAll();
	}
	
	@Override
	public List<UserProfilesEntity> findUserProfilesAll(){
		return userProfilesDAL.findAll();
	}
	
	@Override
	public boolean isExistUserProfilesById(String id) {
		return userProfilesDAL.existsById(id);
	}
}
