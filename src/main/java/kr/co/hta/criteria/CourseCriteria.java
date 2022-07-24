package kr.co.hta.criteria;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CourseCriteria {

	String categoryId;
	List<String> tags;
	List<String> pays;
	List<String> grades;
	String sort;
	
}
