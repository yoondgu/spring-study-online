package kr.co.hta.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.co.hta.argumentResolver.LoginUserArgumentResolver;
import kr.co.hta.interceptor.LoginCheckInterceptor;

/**
 * <p>spring-mvc 관련 설정을 정의하는 클래스
 * <p>사용자정의 WebMvcConfig 클래스는 WebMvcConfigurer 인터페이스를 구현한다.
 * <p> WebMvcConfigurer 인터페이스의 추상 메소드 (이 외에도 많이 있다)
 * <p>		void addInterceptors(InterceptorRegistry registry)
 * <p>			사용자 정의 Interceptor를 등록시킨다.
 * <p>		void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers)
 * <p>			사용자 정의 argumentResolver를 등록시킨다.
 * @author doyoung
 *
 */

/*
 * @Configuration
 * 		스프링 컨테이너의 빈으로 등록시키는 어노테이션
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginCheckInterceptor())
				.addPathPatterns("/**") // 모든 경로를 조회하는데,
				// resources 하위의 요청(=정적컨텐츠)은 제외한다. 아이콘 파일도 제외한다.
				.excludePathPatterns("/resources/**", "/*.ico");
	}
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new LoginUserArgumentResolver());
	}
}
