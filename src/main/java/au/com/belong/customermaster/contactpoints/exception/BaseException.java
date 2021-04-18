package au.com.belong.customermaster.contactpoints.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

public class BaseException extends Exception{

    private int exceptionCode;
    private int statusCode;

    public BaseException(String msg) {
        super(msg);
    }

    public BaseException(String msg, Throwable t) {
        super(msg, t);
    }

}
