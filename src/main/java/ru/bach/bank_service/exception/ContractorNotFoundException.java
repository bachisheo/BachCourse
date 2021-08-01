package ru.bach.bank_service.exception;

public class ContractorNotFoundException extends RuntimeException {
    public ContractorNotFoundException(String msg){
        super(msg);
    }
}
