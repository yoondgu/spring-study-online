package kr.co.hta.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.hta.vo.User;

@Mapper
public interface UserMapper {

	User getUserById(String id);
	User getUserByEmail(String email);
	
	void insertUser(User user);
	
	void updateUser(User user);
}
