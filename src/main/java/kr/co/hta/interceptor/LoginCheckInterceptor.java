package kr.co.hta.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.hta.annotation.LoginUser;

/**
 * 요청핸들러 메소드가 실행되기 전에 수행할 작업이 구현된 인터셉터
 * <p>preHandler() 메소드는 요청핸들러 메소드의 매개변수에 @LoginUser 어노테이션이 정의되어 있는 경우, 로그인 여부를 체크
 * <p>preHandler() 메소드는 요청 핸들러 메소드의 매개변수에 @LoginUser 어노테이션이 없으면, true를 반환한다.
 * <p>preHandler() 메소드는 요청 핸들러 메소드의 매개변수에 @LoginUser 어노테이션이 있으면,
 * <p>세션에 로그인된 사용자정보가 존재할 경우 true를, 존재하지 않을 경우 false를 반환한다.
 * @author doyoung
 *
 */
public class LoginCheckInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// HandlerMethod 객체는 요청핸들러 메소드를 표현하는 객체이다. 이 객체로 handler를 형변환한다.
		// 정적컨텐츠의 요청에 대해서는 Interceptor가 실행되지 않도록 설정했으므로 형변환 오류 발생하지 않는다.
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		// MethodParameter은 메소드의 매개변수 정보를 표현하는 객체이다.
		MethodParameter[] parameters = handlerMethod.getMethodParameters();
		
		// 요청핸들러 메소드의 매개변수 중 LoginUser 어노테이션이 정의되어있는지 확인한다.
		// LoginUser 어노테이션이 정의된 파라미터가 존재하면 true를 반환, 아니면 false를 반환한다.
		boolean isLoginRequired = false;
		for (MethodParameter parameter : parameters) {
			// 매개변수에서 @LoginUser 어노테이션 정보를 조회한다. 이 어노테이션이 없는 경우 loginUser는 null이다.
			// @LoginUser 어노테이션이 조회된다면 지금 이 메소드는 로그인이 필요한 경우로 판단할 수 있다.
			LoginUser loginUser = parameter.getParameterAnnotation(LoginUser.class);
			if (loginUser != null) {
				isLoginRequired = true;
				break;
			}
		}
		
		// 로그인을 요구하지 않는 요청핸들러 메소드인 경우다.
		if (!isLoginRequired) {
			return true;
		}
		
		// 로그인을 요구하는 요청핸들러 메소드
		// 세션에 로그인 정보가 존재한다.
		if (request.getSession().getAttribute("LOGIN_USER") != null) {
			return true;
		}
		
		// 로그인을 요구하는 요청핸들러 메소드
		// 세션에 로그인 정보가 존재하지 않는다.
		// 재요청 응답을 보내게 하고, false를 반환해서 요청핸들러 메소드가 실행되지 않게 한다.
		response.sendRedirect("/login?fail=deny");
		return false;
	}
}
