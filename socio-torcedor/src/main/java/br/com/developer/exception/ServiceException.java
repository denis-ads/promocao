package br.com.developer.exception;

import javax.ejb.ApplicationException;

@ApplicationException()
public class ServiceException extends Exception{

    public ServiceException(Exception e) {
        super(e);
    }

    private static final long serialVersionUID = -4610574559439037462L;

    
}
