package org.icepear.echarts.exceptions;

public class MissingDependencyException extends RuntimeException{
    public MissingDependencyException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }   
}
