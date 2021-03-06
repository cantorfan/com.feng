package com.feng.security.config;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.feng.http.controller.BaseController;
import com.feng.security.common.SecurityCommon;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLoginHandler extends BaseController
		implements AuthenticationSuccessHandler, AuthenticationFailureHandler {


	

	// Login Success
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException {
		logger.info("User login successfully, name={}, requested seesionid={}, current seesionid={}",
				authentication.getName(), request.getRequestedSessionId(), request.getSession(false).getId());
		responseText(response, success(ACTION_LOGIN, HttpServletResponse.SC_OK, SecurityCommon.getJSON(authentication)));
	}

	// Login Failure
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException {
		logger.info("Login failed: {}, requested seesionid={}, current seesionid={}", exception.getMessage(),
				request.getRequestedSessionId(), request.getSession(false).getId());
		responseText(response, error(ACTION_LOGIN, HttpServletResponse.SC_OK, exception.getMessage()));
	}

}