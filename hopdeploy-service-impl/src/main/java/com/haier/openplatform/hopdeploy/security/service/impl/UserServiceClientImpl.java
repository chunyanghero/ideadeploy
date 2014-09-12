package com.haier.openplatform.hopdeploy.security.service.impl;

import com.haier.openplatform.hopdeploy.security.dto.UserDTO;
import com.haier.openplatform.hopdeploy.security.service.UserServiceClient;

public class UserServiceClientImpl implements UserServiceClient {

	@Override
	public UserDTO getUserById(long userId) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(123456L);
		userDTO.setName("test");
		return userDTO;
	}

}
