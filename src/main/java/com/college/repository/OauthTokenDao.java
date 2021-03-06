package com.college.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

import com.college.entity.OauthToken;

/**
* @Title: 
* @Description
* @author milo    
*/
@Repository("oauthTokenDao")
public interface OauthTokenDao {

	OauthToken get(Long id);
	
	void insert(OauthToken oauthToken);

	void delete(Long id);

	void update(OauthToken oauthToken);

	/**
	 * 分页查询 返回Page 对象
	 * @param params psge
	 * @return  Page
	 */
    Page<OauthToken> searchPageList(@Param("searchFields") Map<String, Object> params);
	
	Long getTotalCount(Map<String, Object> params);

	List<OauthToken> findListByParams(@Param("searchFields") Map<String, Object> params);

	/**
	 * 根据查询条件 返回单个对象
	 * @param params
	 * @return  
	 */
    OauthToken searchOne(@Param("searchFields") Map<String, Object> params);

}
