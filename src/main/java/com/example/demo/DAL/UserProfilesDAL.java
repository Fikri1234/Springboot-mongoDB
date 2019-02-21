package com.example.demo.DAL;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.UserProfilesEntity;

@Repository
public interface UserProfilesDAL extends MongoRepository<UserProfilesEntity, String>{

	Optional<UserProfilesEntity> findByName(String name);
	//Optional<UserProfilesEntity> findOne(Long id);
}
