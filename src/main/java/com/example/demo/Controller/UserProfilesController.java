package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.Common.CommonUtil;
import com.example.demo.DTO.DepartmentDTO;
import com.example.demo.DTO.UserProfilesDTO;
import com.example.demo.Entity.UserProfilesEntity;
import com.example.demo.Service.UserProfilesService;



@RestController
@RequestMapping("/account")
public class UserProfilesController {
	
	public static final Logger logger = LoggerFactory.getLogger(UserProfilesController.class);

	CommonUtil commonUtil = new CommonUtil();
	
	@Autowired
	Environment environment;
	
	@Autowired
	UserProfilesService userProfilesService;
	
	//  --------------- retreave single user ------------------
	@GetMapping("/userprofiles/{id}")
	public ResponseEntity<UserProfilesDTO> retrieveUserProfiles(@PathVariable("id") String id) {
		
		logger.info("test id 1 port: {}",environment.getProperty("local.server.port"));
		Optional<UserProfilesEntity> userProfilesEntity = userProfilesService.findById(id);
		logger.info("tets id 2");
		
		List<String> strList = new ArrayList<String>();
		String str;
		logger.info("boolean "+userProfilesEntity.isPresent());
		if (userProfilesEntity.isPresent()) {
			logger.info("id array suks");

			if (userProfilesEntity.get().getChildren().size() > 0) {
				for (String model : userProfilesEntity.get().getChildren()) {
					str = new String();
					str = model;
					logger.info("str: "+str);
					strList.add(str);
				}
			}
			
			String strDate =  commonUtil.convertDateToStringStripMMM(userProfilesEntity.get().getBirth());
			UserProfilesDTO userProfilesDTO = new UserProfilesDTO(userProfilesEntity.get().getId(), userProfilesEntity.get().getUsername(), userProfilesEntity.get().getName(), userProfilesEntity.get().getBirth(), strDate, userProfilesEntity.get().getAge(), userProfilesEntity.get().getNationality(), userProfilesEntity.get().getPhone(), userProfilesEntity.get().getMarital(), userProfilesEntity.get().getSize(), strList, userProfilesEntity.get().getDepartment());
			
			/*Map<String, String> uriVariable = new HashMap<>();
			uriVariable.put("id", userProfilesDTO.getDepartment().toString());
			
			ResponseEntity<DepartmentDTO> responseEntity = new RestTemplate().getForEntity("http://localhost:8060/department/{id}", DepartmentDTO.class, uriVariable);
			
			DepartmentDTO response = responseEntity.getBody();
			logger.info("nama department: {}",response.getName());*/
			
			return new ResponseEntity<UserProfilesDTO>(userProfilesDTO,HttpStatus.OK);
		}else {
			logger.error("id not found: ",id);
			return new ResponseEntity<UserProfilesDTO>(HttpStatus.NOT_FOUND);
		}
		
	}

	// ---------------- retrieve all data ----------------------
	@RequestMapping(value = "/userprofiles/", method = RequestMethod.GET)
	public ResponseEntity<?> getAllUserProfiles(){
		logger.info("test 1");
		List<UserProfilesEntity> userProfiles = userProfilesService.findUserProfilesAll();
		logger.info("test 2");
		if (userProfiles.isEmpty()) {
			return new ResponseEntity<UserProfilesDTO>(HttpStatus.NO_CONTENT);
		}
		
		logger.info("test 3");
		
		UserProfilesDTO data;
		List<UserProfilesDTO> listData = new ArrayList<UserProfilesDTO>();
		for (UserProfilesEntity model : userProfiles) {
			data = new UserProfilesDTO();
			data.setId(model.getId());
			data.setUsername(model.getUsername());
			data.setName(model.getName());
			data.setStrBirth(commonUtil.convertDateToStringStripMM(model.getBirth()));
			data.setAge(model.getAge());
			data.setNationality(model.getNationality());
			data.setPhone(model.getPhone());
			data.setMarital(model.getMarital());
			data.setSize(model.getSize());
			List<String> strList = new ArrayList<String>();
			String str;
			if (model.getChildren().size() > 0) {
				for (String mod : model.getChildren()) {
					str = new String();
					logger.info("mod: "+mod);
					str = mod;
					logger.info("str: "+str);
					strList.add(str);
				}
			}
			data.setChildren(strList);
			data.setDepartment(model.getDepartment());
			listData.add(data);
		}
		
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/userprofiles/", method = RequestMethod.POST, produces= {MediaType.APPLICATION_JSON_VALUE},
			consumes= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> createUserProfiles(@RequestBody UserProfilesDTO userProfilesDTO, UriComponentsBuilder ucBuilder){
		try {
			UserProfilesEntity uProfilesEntity = new UserProfilesEntity();
			//uProfilesEntity.setId(ObjectId.get());
			uProfilesEntity.setUsername(userProfilesDTO.getUsername());
			uProfilesEntity.setName(userProfilesDTO.getName());
			uProfilesEntity.setBirth(userProfilesDTO.getBirth());
			uProfilesEntity.setAge(userProfilesDTO.getAge());
			uProfilesEntity.setNationality(userProfilesDTO.getNationality());
			uProfilesEntity.setPhone(userProfilesDTO.getPhone());
			uProfilesEntity.setMarital(userProfilesDTO.getMarital());
			uProfilesEntity.setSize(userProfilesDTO.getSize());
			uProfilesEntity.setChildren(userProfilesDTO.getChildren());
			uProfilesEntity.setDepartment(userProfilesDTO.getDepartment());
			
			userProfilesService.saveUserProfiles(uProfilesEntity);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(uProfilesEntity.getId()).toUri());
			
			return new ResponseEntity<>(headers, HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(value = "/userprofiles/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUserProfiles(@PathVariable("id") String id, @RequestBody UserProfilesDTO uProfilesDTO){
		logger.info("Updating userProfiles with id {}",id);
		
		Optional<UserProfilesEntity> currentUserProfiles = userProfilesService.findById(id);
		if (currentUserProfiles == null || currentUserProfiles.isPresent()==false) {
			logger.error("Unable to update. UserProfiles with id {} not found",id);
			return new ResponseEntity<>("Unable to update. User with id "+id+" not found", HttpStatus.NOT_FOUND);
		}
		
		UserProfilesEntity uProfilesEntity = new UserProfilesEntity();
		uProfilesEntity.setId(uProfilesDTO.getId());
		uProfilesEntity.setUsername(uProfilesDTO.getUsername());
		uProfilesEntity.setName(uProfilesDTO.getName());
		uProfilesEntity.setBirth(uProfilesDTO.getBirth());
		uProfilesEntity.setAge(uProfilesDTO.getAge());
		uProfilesEntity.setNationality(uProfilesDTO.getNationality());
		uProfilesEntity.setPhone(uProfilesDTO.getPhone());
		uProfilesEntity.setMarital(uProfilesDTO.getMarital());
		uProfilesEntity.setSize(uProfilesDTO.getSize());
		uProfilesEntity.setChildren(uProfilesDTO.getChildren());
		uProfilesEntity.setDepartment(uProfilesDTO.getDepartment());
		
		userProfilesService.saveUserProfiles(uProfilesEntity);
		
		return new ResponseEntity<>(currentUserProfiles, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/userprofiles/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUserProfilesById(@PathVariable("id") String id){
		logger.info("Fetching & Deleting UserProfiles with id {}", id);
		
		Optional<UserProfilesEntity> currentUserProfiles = userProfilesService.findById(id);
		if (currentUserProfiles == null || currentUserProfiles.isPresent()==false) {
			logger.error("Unable to update. UserProfiles with id {} not found",id);
			return new ResponseEntity<>("Unable to update. User with id "+id+" not found", HttpStatus.NOT_FOUND);
		}
		
		userProfilesService.deleteUserProfilesById(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/userprofiles/", method = RequestMethod.DELETE)
	public ResponseEntity<UserProfilesEntity> deleteUserProfiles(){
		logger.info("Deleting All UserProfiles");
		
		userProfilesService.deleteUserProfilesAll();
		
		return new ResponseEntity<UserProfilesEntity>(HttpStatus.NO_CONTENT);
	}
}
