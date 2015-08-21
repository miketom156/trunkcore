package com.job5156.core.common;

/**
 * <p></p>
 * Date:2015/5/16 10:45
 *
 * @author pzm
 * @version 1.0
 */
public class TypeException extends RuntimeException{
    private String paramName;
    private Class needClass;

    public TypeException(String paramName, Class needClass) {
        super(paramName+" must be "+needClass.getSimpleName());
        this.paramName = paramName;
        this.needClass = needClass;
    }
}
