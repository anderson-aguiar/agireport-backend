package analytic_service.exceptions;

public class ServiceNotFoundException extends RuntimeException{

    public ServiceNotFoundException(String msg){
        super(msg);
    }
}
