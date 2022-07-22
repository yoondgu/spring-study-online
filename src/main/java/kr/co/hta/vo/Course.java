package kr.co.hta.vo;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Alias("Course")
public class Course {

	private int no;
	private String title;
	private String grade;
	private String description;
	private int price;
	private int discountPrice;
	private String period;
	private String certificateCompletion;
	private int reviewCount;
	private double reviewScore;
	private int studentCount;
	private String disabled;
	private Date createdDate;
	private Date updatedDate;
	private User user;
	private String imagename;
	
	private List<CourseCategory> categories;
	private List<CourseLearning> learnings;
	private List<CourseRecommendation> recommendations;
	private List<CourseTag> tags;
	
}
