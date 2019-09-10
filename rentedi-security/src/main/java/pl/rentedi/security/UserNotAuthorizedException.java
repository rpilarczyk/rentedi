package pl.rentedi.security;

public class UserNotAuthorizedException extends RuntimeException {

    public UserNotAuthorizedException(String message){
        super(message);
    }
}
