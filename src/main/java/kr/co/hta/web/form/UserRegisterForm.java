package kr.co.hta.web.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * 회원가입 입력폼에 최적화된 객체.
 * 요청핸들러메소드에서 매개변수에 이 타입의 매개변수를 정의하면, 스프링이 요청파라미터값과 같은 이름을 가진 변수를 연결시켜준다.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserRegisterForm {

	@NotBlank(message = "이메일은 필수 입력값입니다.")
	@Email(message = "유효한 이메일 형식이 아닙니다.")
	private String email;
	
	@NotBlank(message = "비밀번호는 필수 입력값입니다.")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$", message = "비밀번호는 숫자, 영어 대소문자, 특수문자가 1개 이상씩, 8 ~ 16글자까지 허용됩니다.")
	private String password;
	
	@NotBlank(message = "이름은 필수 입력값입니다.")
	@Pattern(regexp = "^[가-힣]{2,}$", message = "이름은 한글로 2글자 이상만 허용됩니다.")
	private String name;
	
	@NotBlank(message = "전화번호는 필수 입력값입니다.")
	@Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "유효한 전화번호 형식이 아닙니다.")
	private String phone;
	
	private MultipartFile profileFile;
	
}
