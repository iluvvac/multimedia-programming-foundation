package main;

public class user {
	private String email;
	private String password;
	private String country;
	private Integer age;
	
	
//	alt + shift + s +o
	
	public user(String email, String password, String country, Integer age) {
		super();
		this.email = email;
		this.password = password;
		this.country = country;
		this.age = age;
	}
	
//	alt + shift + s + r


	public String getEmail() {
		return email;
	}


	public String getPassword() {
		return password;
	}


	public String getCountry() {
		return country;
	}


	public Integer getAge() {
		return age;
	}
	
}
