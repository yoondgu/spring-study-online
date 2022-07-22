package kr.co.hta.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import kr.co.hta.exception.OnlineApplicationException;
import kr.co.hta.mapper.UserMapper;
import kr.co.hta.vo.User;
import kr.co.hta.web.form.UserRegisterForm;

@Service
public class UserService {
	
	
	@Value("${online.profile.image.save-directory}")
	private String profileImageSaveDirecotry;

	@Autowired
	private UserMapper userMapper;
	

	public User getUserDetail(String userId) {
		return userMapper.getUserById(userId);
	}
	
	/**
	 * 폼 입력값을 전달받아서 새 사용자를 등록한다.
	 * 이미 사용중인 이메일을 입력했을 경우, 예외를 발생시킨다.
	 * @param userRegisterForm
	 * @throws Exception
	 */
	public void addNewUser(UserRegisterForm userRegisterForm) throws Exception {
		// 파일 업로드 시 exception 발생하므로 던지기
		// 같은 이메일이 있으면 예외 던지기
		User user = userMapper.getUserByEmail(userRegisterForm.getEmail());
		if (user != null) {
			throw new OnlineApplicationException("이미 사용중인 이메일입니다.");
		}
		
		user = new User();
		// 첨부파일을 제외한 폼 입력값(email, password, name, phone)이 user 객체에 복사된다.
		// 첨부파일은 데이터타입이 다르므로 전처리를 해준 뒤 저장해야 함
		BeanUtils.copyProperties(userRegisterForm, user);
		
		user.setId(UUID.randomUUID().toString());
		
		MultipartFile upfile = userRegisterForm.getProfileFile();
		// 첨부파일의 존재여부를 체크한다.
		if (!upfile.isEmpty()) {
			// 첨부파일명을 조회해서 User객체에 저장한다.
			String filename = upfile.getOriginalFilename();
			user.setProfileImage(filename);
			
			// 업로드된 첨부파일을 resources/images/profile 폴더에 저장시킨다. **
			InputStream in = upfile.getInputStream(); // 업로드된 첨부파일은 임시디렉토리에 저장되는데, 그 파일을 읽어오는 스트림이다.
			FileOutputStream out = new FileOutputStream(new File(profileImageSaveDirecotry, filename)); // 설정해둔 경로에 파일을 저장한다.
			FileCopyUtils.copy(in, out);
		}
		
		userMapper.insertUser(user);
	}

	/**
	 * 이메일, 비밀번호를 전달받아서 사용자정보를 조회해서 반환한다.
	 * 사용자정보가 존재하지 않거나, 비밀번호가 일치하지 않으면 예외를 발생시킨다.
	 * @param email
	 * @param password
	 * @return user 로그인 사용자정보
	 */
	public User login(String email, String password) {
		User user = userMapper.getUserByEmail(email);
		if (user == null) {
			throw new OnlineApplicationException("아이디 혹은 비밀번호가 올바르지 않습니다.");
		}
		if (!user.getPassword().equals(password)) {
			throw new OnlineApplicationException("아이디 혹은 비밀번호가 올바르지 않습니다.");
		}
		return user;
	}
	
	public void confirmTeacher(String userId) {
		// 최신의 사용자정보를 획득하기 위해서 userId를 이용해 다시 조회한다.
		// 이 대신, 변경될때마다 컨트롤러에서 session의 사용자정보를 갱신해주는 방법도 있다.
		User user = userMapper.getUserById(userId);
		if (user == null) {
			throw new OnlineApplicationException("사용자정보가 존재하지 않습니다");
		}
		if ("Y".equals(user.getDisabled())) {
			throw new OnlineApplicationException("탈퇴한 사용자는 서비스를 이용할 수 없습니다.");
		}
		if ("Y".equals(user.getTeacher())) {
			throw new OnlineApplicationException("지식 공유자로 이미 등록되어 있습니다.");
		}
		user.setTeacher("Y");
		userMapper.updateUser(user);
	}

}
