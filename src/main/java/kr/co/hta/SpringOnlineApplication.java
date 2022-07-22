package kr.co.hta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * @SpringBootApplication
 * 		스프링 부트의 기본적인 설정을 선언하는 어노테이션
 * 		아래의 어노테이션을 포함하고 있다.
 * 
 * 		@SpringBootConfiguration
 * 			@Configuration 어노테이션과 동일한 역할을 수행하는 어노테이션
 * 			해당 어노테이션을 적용한 클래스가 하나 이상의 @Bean 메소드를 정의해서 객체를 반환하면 그 객체는 스프링 컨테이너에 등록된다.
 * 			사용자가 추가적으로 정의한 빈을 스프링 컨테이너에 등록할 수 있도록 한다.
 * 		@EnableAutoConfiguration
 * 			Spring Boot에서 지원하는 AutoConfiguration을 가능하게 하는 어노테이션
 * 			AutoConfiguration은 pom.xml에 정의한 의존성을 기반으로 관련 있는 객체를 자동으로 스프링 컨테이너에 등록한다.
 * 		@ComponentScan
 * 			@Component, @Controller, @RestController, @ControllerAdvice, @RestControllerAdvice, @Service, @Repository 어노테이션이 적용된다.
 * 			프로젝트 생성 시 지정한 패키지의 클래스를 스캔해서 자동으로 스프링 컨테이너의 빈으로 등록시킨다.
 */
@SpringBootApplication
public class SpringOnlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringOnlineApplication.class, args);
	}
	
}
