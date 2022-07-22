package kr.co.hta.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
/*
 * @Alias
 * 		매퍼파일에서 사용할 별칭을 지정한다.
 *		(환경설정의 typeAliases보다 우선순위가 높다.)
 *		mybatis의 매퍼설정파일에서 kr.co.hta.vo.User 대신 User로 사용할 수 있게 한다.
 */
@Alias("User")
public class User {

	@JsonIgnore
	private String id;
	@JsonIgnore
	private String email;
	@JsonIgnore
	private String password;
	private String name;
	@JsonIgnore
	private String phone;
	@JsonIgnore
	private String profileImage;
	@JsonIgnore
	private String disabled;
	@JsonIgnore
	private String teacher;
	@JsonIgnore
	private Date createdDate;
	@JsonIgnore
	private Date updatedDate;
	
}
