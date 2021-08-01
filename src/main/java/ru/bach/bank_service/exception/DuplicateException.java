package ru.bach.bank_service.exception;

public class DuplicateException extends RuntimeException{
   public DuplicateException(String msg){
       super(msg);
   }
}
