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

//    public Throwable getNestedException() {
//        return this.getCause();
//    }
//
//    public String getStackTraceString() {
//        StringWriter s = new StringWriter();
//        printStackTrace(new PrintWriter(s));
//        return s.toString();
//    }
//
//    public void setExceptionCode(String code) {
//        try {
//            this.exceptionCode = Integer.parseInt(code);
//        } catch (NumberFormatException e) {
//            this.exceptionCode = 0;
//        }
//    }
//
//    public void setExceptionCode(int code) {
//        this.exceptionCode = code;
//    }
//
//    public int getExceptionCode() {
//        return this.exceptionCode;
//    }
//
//    public String getExceptionCodeAsString() {
//        return String.valueOf(this.exceptionCode);
//    }
//
//    private static final Throwable getRootCause(Throwable throwable) {
//        if (throwable instanceof BaseException) {
//            return throwable.getCause() == null ? throwable : throwable.getCause();
//        }
//        return throwable;
//    }
//
//    public int getStatusCode() {
//        return this.statusCode;
//    }
//
//    public void setStatusCode(int statusCode) {
//        this.statusCode = statusCode;
//    }
//
//    public void setStatusCode(String statusCode) {
//        this.statusCode = Integer.parseInt(statusCode);
//    }
//
//    public String getStatusCodeAsString() {
//        return String.valueOf(this.statusCode);
//    }
}
