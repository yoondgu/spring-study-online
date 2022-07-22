package kr.co.hta.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.hta.criteria.CourseCriteria;
import kr.co.hta.mapper.CategoryMapper;
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
	private CategoryMapper categoryMapper;
	@Autowired
	private CourseMapper courseMapper;
	
	public List<Category> getAllCategories() {
		return categoryMapper.getAllCategories();
	}
	
	public List<String> getAllTags() {
		return courseMapper.getAllTags();
	}
	
	public List<Course> searchCourses(CourseCriteria criteria) {
		return courseMapper.getCoursesByCriteria(criteria);
	}
	
	public List<Course> getMyCourses(String userId) {
		return courseMapper.getCoursesByUserId(userId);
	}
	
	public List<Course> getRecentAddedCourses(String userId) {
		// 실제로는 최근 값만 조회되도록 하기
		return courseMapper.getCoursesByUserId(userId);
	}
	
	public List<Course> getCoursesByCategoryId(String categoryId) {
		return courseMapper.getCoursesByCategoryId(categoryId);
	}
	
	public Course getCourseDetailByNo(int no) {
		return courseMapper.getCourseByNo(no);
	}

	public void addNewCourse(User loginUser, CourseRegisterForm courseRegisterForm) throws IOException {
		// 강의정보 저장
		Course course = new Course();
		
		// course와 courseRegisterForm에 이름은 같지만 타입이 다른 멤버변수들이 있기 때문에 여기서는 copyProperties를 사용하지 않았다.
		course.setTitle(courseRegisterForm.getTitle());
		course.setGrade(courseRegisterForm.getGrade());
		course.setDescription(courseRegisterForm.getDescription());
		course.setPrice(courseRegisterForm.getPrice());
		course.setPeriod(courseRegisterForm.getPeriod());
		course.setCertificateCompletion(courseRegisterForm.getCertificate());
		course.setImagename(courseRegisterForm.getImagename());
		course.setUser(loginUser);
		
		// List<CourseCategory> ... 는 조회할 때 쓰기 위한 멤버변수이므로 여기서 대입해줄 필요 없다.
		// 이 때는 course의 멤버변수 no의 값이 대입되어있지 않다.
		// 선언적 트랜잭션 처리가 되므로 DB에 먼저 저장할 수 있다.
		courseMapper.insertCourse(course);
		// 이제 insert구문 내의 selectKey를 통해 course의 멤버변수 no의 값이 대입되어있다. 이 번호를 다른 DB작업에서 사용한다.
		
		// 강의 카테고리 정보 저장하기
		List<String> categoryIds = courseRegisterForm.getCategoryIds();
		for (String categoryId : categoryIds) {
			courseMapper.insertCourseCategory(new CourseCategory(course.getNo(), categoryId));
		}
		
		// 강의 학습내용 정보 저장하기
		List<String> learnings = courseRegisterForm.getLearnings();
		for (String learning : learnings) {
			courseMapper.insertCourseLearning(new CourseLearning(course.getNo(), learning));
		}
		
		// 강의 대상 정보 저장하기
		List<String> targets = courseRegisterForm.getTargets();
		for (String target : targets) {
			courseMapper.insertCourseRecommendation(new CourseRecommendation(course.getNo(), target));
		}
		
		// 강의 태그 정보 저장하기
		List<String> tags = courseRegisterForm.getTags();
		for (String tag : tags) {
			courseMapper.insertCourseTag(new CourseTag(course.getNo(), tag));
		}
	}

}
