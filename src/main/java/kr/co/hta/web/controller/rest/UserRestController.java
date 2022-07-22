package kr.co.hta.web.controller.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

	@GetMapping("/rest/user")
	public User user() {
		User user = new User("홍길동", "hong@gmail.com");
		
		return user;
	}
	
	@GetMapping("/rest/users")
	public List<User> users() {
		List<User> users = List.of(new User("홍길동", "hong@gmail.com"), new User("김유신", "kim@gmail.com"));
		return users;
	}
	
	public class User {
		String name;
		String email;
		
		public User(String name, String email) {
			super();
			this.name = name;
			this.email = email;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
		
	}
}
