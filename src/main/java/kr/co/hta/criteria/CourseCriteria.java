package kr.co.hta.criteria;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CourseCriteria {

	private String categoryId;
	private List<String> tags;
	private List<String> grades;
	private List<String> pays;
	private String sort;
}
