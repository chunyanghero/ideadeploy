package com.haier.openplatform.hopdeploy.security.service;

import com.haier.openplatform.hopdeploy.security.dto.UserDTO;

/**
 * 供客户端调用的远程接口
 * 
 * @author WangXuzheng
 * 
 */
public interface UserServiceClient {
	public UserDTO getUserById(long userId);
}
