package com.kuz.springrest.util;

public class PersonNotCreatedException  extends RuntimeException{
    public PersonNotCreatedException(String msg)
    {
        super(msg);
    }
}
