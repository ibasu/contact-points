package au.com.belong.customermaster.contactpoints.exception;

public class ResourceNotFoundException extends BaseException {

    public ResourceNotFoundException(String message){
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable t){
        super(message, t);
    }
}
