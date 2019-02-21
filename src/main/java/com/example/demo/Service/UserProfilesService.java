package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import com.example.demo.Entity.UserProfilesEntity;

public interface UserProfilesService {
	
	Optional<UserProfilesEntity> findById(String id);
	Optional<UserProfilesEntity> findByUsername(String username);
	void saveUserProfiles(UserProfilesEntity userProfilesEntity);
	void updateUserProfiles(UserProfilesEntity userProfilesEntity);
	void deleteUserProfilesById(String id);
	void deleteUserProfilesAll();
	List<UserProfilesEntity> findUserProfilesAll();
	boolean isExistUserProfilesById(String id);

}
