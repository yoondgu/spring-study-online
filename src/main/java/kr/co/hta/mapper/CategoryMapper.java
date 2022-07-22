package kr.co.hta.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.type.Alias;

import kr.co.hta.vo.Category;

@Mapper
@Alias("Cateogry")
public interface CategoryMapper {
	
	List<Category> getAllCategories();
	Category getCategoryById(String categoryId);
}
