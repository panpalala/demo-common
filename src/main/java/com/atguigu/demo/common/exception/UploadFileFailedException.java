package com.atguigu.demo.common.exception;

/**
 * @author panpala
 * @date 2017年9月7日
 */
public class UploadFileFailedException extends RuntimeException {

	private static final long serialVersionUID = 7633938818114033799L;

	public UploadFileFailedException(String message) {
		super(message);
		
	}

}
