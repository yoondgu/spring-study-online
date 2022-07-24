package kr.co.hta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.hta.criteria.CourseCriteria;
import kr.co.hta.mapper.CourseMapper;
import kr.co.hta.vo.Category;
import kr.co.hta.vo.Course;
import kr.co.hta.vo.CourseCategory;
import kr.co.hta.vo.CourseLearning;
import kr.co.hta.vo.CourseRecommendation;
import kr.co.hta.vo.CourseTag;
import kr.co.hta.vo.User;
import kr.co.hta.web.form.CourseRegisterForm;

@Service
@Transactional
public class CourseService {

	@Autowired
	private CourseMapper courseMapper;
	
	public List<Category> getAllCategories() {
		return courseMapper.getAllCategories();
	}
	
	public List<Course> getMyCourses(String userId) {
		return courseMapper.getCoursesByUserId(userId);
	}
	
	public void addNewCourse(String userId, CourseRegisterForm courseRegisterForm) {
		// userId, courseRegisterForm을 이용해서 course에 값 설정
		Course course = new Course();
		User user = new User();
		user.setId(userId);
		
		course.setUser(user);
		course.setTitle(courseRegisterForm.getTitle());
		course.setGrade(courseRegisterForm.getGrade());
		course.setDescription(courseRegisterForm.getDescription());
		course.setPrice(courseRegisterForm.getPrice());
		course.setPeriod(courseRegisterForm.getPeriod());
		course.setCertificateCompletion(courseRegisterForm.getCertificate());
		course.setImagename(courseRegisterForm.getImagename());
		
		// db 저장: course
		//insertCourse(course)에서 course에 no값이 저장됨 
		courseMapper.insertCourse(course);
		
		// db 저장: courseCategory, courseLearning, courseRecommendation, courseTag
		List<String> categoryIds = courseRegisterForm.getCategoryIds();
		for (String categoryId : categoryIds) {
			courseMapper.insertCourseCategory(new CourseCategory(course.getNo(), categoryId));
		}
		
		List<String> learnings = courseRegisterForm.getLearnings();
		for (String learning : learnings) {
			courseMapper.insertCourseLearning(new CourseLearning(course.getNo(), learning));
		}
		
		List<String> targets = courseRegisterForm.getTargets();
		for (String target : targets) {
			courseMapper.insertCourseRecommendation(new CourseRecommendation(course.getNo(), target));
		}
		
		List<String> tags = courseRegisterForm.getTags();
		for (String tag : tags) {
			courseMapper.insertCourseTag(new CourseTag(course.getNo(), tag));
		}
	}

	public Course getCourseDetail(int courseNo) {
		return courseMapper.getCourseDetailByNo(courseNo);
	}

	public List<Course> getCoursesByCategoryId(String categoryId) {
		// 만약 categoryId가 null이면 categoryId 조건 없이 모든 Courses를 반환한다.
		return courseMapper.getCoursesByCategoryId(categoryId);
	}

	public List<String> getAllTags() {
		return courseMapper.getAllTags();
	}

	public List<Course> getCoursesByCourseCriteria(CourseCriteria courseCriteria) {
		return courseMapper.getCoursesByCourseCriteria(courseCriteria);
	}
}
