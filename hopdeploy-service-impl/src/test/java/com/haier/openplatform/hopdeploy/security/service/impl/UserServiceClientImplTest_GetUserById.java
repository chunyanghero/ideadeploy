package com.haier.openplatform.hopdeploy.security.service.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import javax.annotation.Resource;

import org.junit.Test;

import com.haier.openplatform.hopdeploy.security.dto.UserDTO;
import com.haier.openplatform.hopdeploy.security.service.UserServiceClient;

/**
 * @author kevin
 * 
 */
public class UserServiceClientImplTest_GetUserById extends BaseTestCase {
	@Resource
	protected UserServiceClient userServiceClient;

	@Test
	public void testGetUserById() {
		long id = 99999918L;
		UserDTO user = userServiceClient.getUserById(id);
		assertThat(user, notNullValue());
	}
}
