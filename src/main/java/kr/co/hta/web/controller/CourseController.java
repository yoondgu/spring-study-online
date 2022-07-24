package kr.co.hta.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import kr.co.hta.annotation.LoginUser;
import kr.co.hta.criteria.CourseCriteria;
import kr.co.hta.service.CourseService;
import kr.co.hta.vo.Course;
import kr.co.hta.vo.User;
import kr.co.hta.web.form.CourseRegisterForm;

@Controller
@RequestMapping("/courses")
@SessionAttributes("courseRegisterForm")
public class CourseController {
	
	@Value("${online.course.image.save-directory}")
	String courseImageSaveDirectory;

	@Autowired
	private CourseService courseService;
	
	@GetMapping(path = "/form1")
	public String form1(@LoginUser User user, Model model) {
		if ("N".equals(user.getTeacher())) {
			return "redirect:/user/dashboard?fail=invalid";
		}
		// 카테고리 정보 전달하기
		model.addAttribute("categories", courseService.getAllCategories());
		// 폼값을 저장할 폼객체 세션에 저장해두기
		model.addAttribute("courseRegisterForm", new CourseRegisterForm());
		return "/course/form1";
	}
	
	@PostMapping(path = "/form2")
	public String form2(@LoginUser User user, @ModelAttribute("courseRegisterForm") CourseRegisterForm form) throws IOException {
		// 첨부파일 처리하기 (요청-응답 통신이 한번 끝나면 Multiipart 객체가 사용하는 임시파일이 삭제되어 객체에서 정보를 사용할 수 없게 되므로 이때 미리 로컬에 저장시키고, 파일명도 폼객체에 저장한다.) 
		MultipartFile file = form.getImageFile();
		if (!file.isEmpty()) {
			String filename = file.getOriginalFilename();
			form.setImagename(filename);
			
			InputStream in = file.getInputStream();
			FileOutputStream out = new FileOutputStream(new File(courseImageSaveDirectory, filename));
			FileCopyUtils.copy(in, out);
		}
		
		return "/course/form2";
	}
	
	@PostMapping(path = "/insert")
	public String reigster(@LoginUser User user, @ModelAttribute("courseRegisterForm") CourseRegisterForm form) {
		// 강의 등록 폼을 이용해서 실제 db에 강의 등록하기
		courseService.addNewCourse(user.getId(), form);
		return "redirect:/complete";
	}
	
	@GetMapping(path = "/detail")
	public String detail(@RequestParam("no") int courseNo, Model model) {
		model.addAttribute("course", courseService.getCourseDetail(courseNo));
		return "/course/detail";
	}
	
	@GetMapping(path = "/search")
	@ResponseBody
	public List<Course> searchCourse(CourseCriteria courseCriteria) {
		return courseService.getCoursesByCourseCriteria(courseCriteria);
	}
}
