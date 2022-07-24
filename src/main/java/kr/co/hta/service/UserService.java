package kr.co.hta.service;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.hta.exception.OnlineApplicationException;
import kr.co.hta.mapper.UserMapper;
import kr.co.hta.vo.User;
import kr.co.hta.web.form.UserRegisterForm;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	public User loginUser(String email, String password) {
		// userMapper에서 해당 이메일을 가진 사용자 정보 조회
		User user = userMapper.getUserByEmail(email);
		
		// 사용자정보가 없을 경우 예외 던지기
		if (user == null) {
			throw new OnlineApplicationException("아이디 또는 비밀번호가 올바르지 않습니다.");
		}
		// 사용자정보와 비밀번호가 일치하지 않을 경우 예외던지기
		if (!user.getPassword().equals(password)) {
			throw new OnlineApplicationException("아이디 또는 비밀번호가 올바르지 않습니다.");
		}
		
		// 모든 로그인 유효성검사 완료 시 조회한 사용자정보 전달
		return user;
	}

	public User getUserById(String id) {
		return userMapper.getUserById(id);
	}
	
	public void addNewUser(UserRegisterForm userRegisterForm) {
		// 같은 이메일을 가진 사용자가 존재하면 예외를 던진다.
		if (userMapper.getUserByEmail(userRegisterForm.getEmail()) != null) {
			throw new OnlineApplicationException("이미 사용 중인 이메일입니다.");
		}
		
		User user = new User();
		// 입력폼에서 받은 회원가입정보를 user객체에 옮기고, 필요한 값을 마저 담아서 db작업을 한다.
		// 입력폼 값의 유효성체크는 UserRegisterForm 객체에 대한 @Valid에서 이미 처리했다.
		BeanUtils.copyProperties(userRegisterForm, user);
		user.setId(UUID.randomUUID().toString());
		user.setProfileImage(userRegisterForm.getProfileFile().getOriginalFilename());
		userMapper.insertUser(user);
	}
	
	public void confirmTeacher(String userId) {
		User user = getUserById(userId);
		
		if (user == null) {
			throw new OnlineApplicationException("사용자 정보가 존재하지 않습니다.");
		}
		if ("Y".equals(user.getDisabled())) {
			throw new OnlineApplicationException("탈퇴한 사용자는 서비스를 이용할 수 없습니다.");
		}
		if ("Y".equals(user.getTeacher())) {
			throw new OnlineApplicationException("이미 지식 공유자로 등록되어 있습니다.");
		}
		user.setTeacher("Y");
		userMapper.updateUser(user);
	}

}
