package com.atguigu.demo.common.exception;

/**
 * @author panpala
 * @date 2017年9月6日
 */
public class UserLoginFailedException extends RuntimeException {

	private static final long serialVersionUID = 3363079438061299961L;

	public UserLoginFailedException(String message) {
		super(message);
		
	}

}
