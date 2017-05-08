package br.com.developer.exception;

import javax.ejb.ApplicationException;

@ApplicationException()
public class DBException extends Exception{

    public DBException(Exception e) {
        super(e);
    }

    private static final long serialVersionUID = -4610574559439037462L;

    
}
