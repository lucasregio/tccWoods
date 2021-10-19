package com.woods.tcc.services.exceptions;

public class EntityNotFoundException extends RuntimeException {
  public static final long serialVersionUID = 1L;

  public EntityNotFoundException(Object id){
    super("Entity Not Found - id:" + id);
  }

  public EntityNotFoundException(String msg){
    super(msg);
  }
}
