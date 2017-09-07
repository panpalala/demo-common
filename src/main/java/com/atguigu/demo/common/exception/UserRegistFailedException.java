package com.atguigu.demo.common.exception;

/**
 * @author panpala
 * @date 2017年9月6日
 */
public class UserRegistFailedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserRegistFailedException(String message) {
		super(message);
		
	}

}
