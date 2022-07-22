package kr.co.hta.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>사용자정의 어노테이션
 * <p>메소드의 매개변수에 정의하는 어노테이션이다.
 * <p>이 어노테이션은 로그인된 사용자정보를 전달받아야 할 때 사용한다.
 * <pre>
 * 	@GetMapping("/user/dashboard")
 * 	public String home(@LoginUser User user, Model model) {
 * 	}
 * @author doyoung
 *
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {

}
