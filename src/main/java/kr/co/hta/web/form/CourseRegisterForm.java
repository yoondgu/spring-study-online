package kr.co.hta.web.form;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CourseRegisterForm {

	private List<String> categoryIds; // name=categoryIds인 폼 입력값이 모두 담긴다.
	private String title;
	private String grade;
	private String period;
	private String certificate;
	private int price;
	private MultipartFile imageFile;
	private String imagename;
	private String description;
	private List<String> learnings;
	private List<String> targets;
	private List<String> tags;
}
