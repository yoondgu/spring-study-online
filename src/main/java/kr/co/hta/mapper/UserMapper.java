package kr.co.hta.mapper;

import org.apache.ibatis.annotations.Mapper;
import kr.co.hta.vo.User;

@Mapper
public interface UserMapper {

	User getUserByEmail(String email);
	User getUserById(String id);
	void insertUser(User user);
	void updateUser(User user);
}
