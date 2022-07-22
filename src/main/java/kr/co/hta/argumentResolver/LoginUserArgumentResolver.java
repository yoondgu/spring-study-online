package kr.co.hta.argumentResolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import kr.co.hta.annotation.LoginUser;
import kr.co.hta.vo.User;

/**
 * <p> 사용자정의 ArgumentResolver
 * <p> ArgumentResolver는 요청핸들러메소드의 매개변수를 분석해서 적절한 값 혹은 객체를 매개변수에 전달한다.
 * <p> 사용자정의 ArgumentResolver는 HandlerMethodArgumentResolver를 구현한다.
 * @author doyoung
 *
 */
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

	/*
	 * MethodParameter는 요청핸들러 메소드의 매개변수에 대한 정보를 포함하고 있는 객체
	 * 요청핸들러의 매개변수가, 지정된 어노테이션을 포함하고 있으면 true를 반환한다.
	 */
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(LoginUser.class);
	}

	/*
	 * boolean supportsParameter(MethodParameter parameter) 메소드가 true를 반환할 때만 실행되는 메소드
	 * 요청핸들러 메소드의 매개변수에 제공할 객체를 반환한다.
	 * 
	 * HttpSession 객체에 "LOGIN_USER"라는 이름으로 저장된, 인증된 사용자정보를 조회해서 반환한다.
	 */
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		
		User user = (User) webRequest.getAttribute("LOGIN_USER", WebRequest.SCOPE_SESSION);
		return user;
	}
}
