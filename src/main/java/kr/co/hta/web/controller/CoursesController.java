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
import org.springframework.web.bind.support.SessionStatus;
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
public class CoursesController {

	@Value("${online.course.image.save-directory}")
	String courseImageSaveDirectory;
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping
	public String home(@RequestParam(name = "cat", required = false) String categoryId, Model model) {
		model.addAttribute("categories", courseService.getAllCategories());
		model.addAttribute("tags", courseService.getAllTags());
		model.addAttribute("courses", courseService.getCoursesByCategoryId(categoryId));
		return "course/home";
	}
	
	@GetMapping(path = "/detail")
	public String detail(@RequestParam("no") int no, Model model) {
		Course course = courseService.getCourseDetailByNo(no);
		model.addAttribute("course", course);
		return "course/detail";
	}

	@GetMapping(path = "/search")
	@ResponseBody
	public List<Course> search(CourseCriteria criteria) {
		return courseService.searchCourses(criteria);
	}
	
	@GetMapping(path = "/form1")
	public String form(Model model) {
		// 카테고리 정보를 조회해서 model에 담는다.
		model.addAttribute("categories", courseService.getAllCategories());
		// 단계별 폼 입력값을 저장할 입력폼 객체를 form1로 내부이동하기 전에 미리 모델에 담는다 -> @SessionAttributes로 인해 세션에 담기게 된다.
		model.addAttribute("courseRegisterForm", new CourseRegisterForm());
		return "course/form1";
	}
	
	@PostMapping(path = "/form2")
	public String formdetail(@ModelAttribute("courseRegisterForm") CourseRegisterForm courseRegisterForm) throws IOException {
		// 세션객체에 담겨있는 courseRegisterForm 객체를 매개변수로 전달한다. (입력폼 1의 값이 저장되어 전달된다)
		
		// 입력폼 1에서 받은 첨부파일 처리하기
		if (!courseRegisterForm.getImageFile().isEmpty()) {
			MultipartFile imageFile = courseRegisterForm.getImageFile();
			String fileName = imageFile.getOriginalFilename();
			courseRegisterForm.setImagename(fileName);
			
			InputStream in = imageFile.getInputStream();
			FileOutputStream out = new FileOutputStream(new File(courseImageSaveDirectory, fileName));
			
			FileCopyUtils.copy(in, out);
		}
		
		return "course/form2";
	}

	@PostMapping(path = "/insert")
	public String insert(@LoginUser User loginUser, @ModelAttribute("courseRegisterForm") CourseRegisterForm courseRegisterForm, SessionStatus sessionStatus) throws IOException {
		// 세션객체에 담겨있는 courseRegisterForm 객체를 매개변수로 전달한다. (입력폼 1, 입력폼 2의 값이 저장되어 전달된다)
		// 전달받은 폼으로 정보를 등록한다.
		courseService.addNewCourse(loginUser, courseRegisterForm);
		//세션에 "courseRegisterForm" 이름으로 저장된 객체를 clear시킨다. (현재 컨트롤러에서 담은 객체만 없어진다.)
		sessionStatus.setComplete();
		return "redirect:/courses/complete";
	}
	
	@GetMapping(path = "/complete")
	public String complete() {
		return "course/complete";
	}
	
}
