package kr.co.hta.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.hta.criteria.CourseCriteria;
import kr.co.hta.vo.Course;
import kr.co.hta.vo.CourseCategory;
import kr.co.hta.vo.CourseLearning;
import kr.co.hta.vo.CourseRecommendation;
import kr.co.hta.vo.CourseTag;

@Mapper
public interface CourseMapper {

	void insertCourse(Course course);
	void insertCourseCategory(CourseCategory courseCategory);
	void insertCourseLearning(CourseLearning courseLearning);
	void insertCourseRecommendation(CourseRecommendation courseRecommendation);
	void insertCourseTag(CourseTag courseTag);
	
	List<String> getAllTags();
	
	List<Course> getCoursesByCriteria(CourseCriteria criteria);
	
	List<Course> getCoursesByUserId(String userId);
	List<CourseCategory> getCourseCategoriesByCourseNo(int courseNo);
	List<CourseLearning> getCourseLearningsByCourseNo(int courseNo);
	List<CourseRecommendation> getCourseRecommendationsByCourseNo(int courseNo);
	List<CourseTag> getCourseTagsByCourseNo(int courseNo);
	
	Course getCourseByNo(int no);
	List<Course> getCoursesByCategoryId(@Param("categoryId") String categoryId);
}
