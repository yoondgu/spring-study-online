package kr.co.hta.vo;

import lombok.ToString;
import lombok.Setter;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Alias("CourseCategory")
public class CourseCategory {

	private int courseNo;
	private Category category;
	
	public CourseCategory(int courseNo, String categoryId) {
		this.courseNo = courseNo;
		this.category = new Category(categoryId);
	}
}
