package com.example.demo.DTO;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserProfilesDTO {

	private String id;
	private String username;
	private String name;
	private Date birth;
	private String strBirth;
	private Integer age;
	private String nationality;
	private String phone;
	private String marital;
	private Map<String, String> size = new HashMap<>();
	private List<String> children;
	private Integer department;
	
	public UserProfilesDTO() {
		
	}

	public UserProfilesDTO(String id, String username, String name, Date birth, String strBirth, Integer age, String nationality,
			String phone, String marital, Map<String, String> size, List<String> children, Integer department) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.birth = birth;
		this.strBirth = strBirth;
		this.age = age;
		this.nationality = nationality;
		this.phone = phone;
		this.marital = marital;
		this.size = size;
		this.children = children;
		this.department = department;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getStrBirth() {
		return strBirth;
	}

	public void setStrBirth(String strBirth) {
		this.strBirth = strBirth;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMarital() {
		return marital;
	}

	public void setMarital(String marital) {
		this.marital = marital;
	}

	public Map<String, String> getSize() {
		return size;
	}

	public void setSize(Map<String, String> size) {
		this.size = size;
	}

	public List<String> getChildren() {
		return children;
	}

	public void setChildren(List<String> children) {
		this.children = children;
	}

	public Integer getDepartment() {
		return department;
	}

	public void setDepartment(Integer department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "userProfilesDTO [id=" + id + ", username=" + name + ", name=" + name + ", birth=" + birth + ", strBirth=" + strBirth + ", age="
				+ age + ", nationality=" + nationality + ", phone=" + phone + ", marital=" + marital + ", size=" + size
				+ ", children=" + children + ", department=" + department + "]";
	}
}
