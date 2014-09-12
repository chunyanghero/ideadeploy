package com.haier.openplatform.hopdeploy.security.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.haier.openplatform.dao.CommonDAO;
import com.haier.openplatform.hopdeploy.security.domain.User;

public interface UserDAO extends CommonDAO<User, Integer> {
	/**
	 * 根据用户名获取密码
	 * 
	 * @param username
	 * @return
	 */
	public String getPasswordByUsername(@Param("username") String username);

	/**
	 * 查看指定用户名数量
	 * 
	 * @param username
	 * @return
	 */
	public int getUserAmount(String username);

	public List<User> searchForSendingEM();

	public String getNumberOfExecutor(String executor);

	/**
	 * 从alm数据库读取要发版项目后，短信通知人s的电话
	 * 
	 * @return
	 */
	public List<String> getPhoneNums();
}
