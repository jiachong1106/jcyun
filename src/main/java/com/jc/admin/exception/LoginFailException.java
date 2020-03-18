package com.jc.admin.exception;

public class LoginFailException extends RuntimeException{

	private static final long serialVersionUID = 1L;

    //构造方法的方法名与类名相同，首字母不小写
    public LoginFailException(String message) {
            super();
    }
}
