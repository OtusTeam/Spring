package ru.otus.spring.security;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.core.Authentication;

public class AclMethodSecurityExpressionRoot extends SecurityExpressionRoot implements AclMethodSecurityExpressionOperations {

    private Object filterObject;
    private Object returnObject;
    private Object target;

    public AclMethodSecurityExpressionRoot(Authentication authentication) {
        super(authentication);
    }

    void setThis(Object target) {
        this.target = target;
    }

    @Override
    public Object getFilterObject() {
        return filterObject;
    }

    @Override
    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    @Override
    public Object getReturnObject() {
        return returnObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    @Override
    public Object getThis() {
        return this.target;
    }

    @Override
    public boolean isAdministrator(Object targetId, Class<?> targetClass) {

        return isGranted(targetId, targetClass, admin);
    }

    @Override
    public boolean isAdministrator(Object target) {

        return hasPermission(target, admin);
    }

    @Override
    public boolean canRead(Object targetId, Class<?> targetClass) {

        if(isAdministrator(targetId, targetClass)) return true;

        return isGranted(targetId, targetClass, read);
    }

    boolean isGranted(Object targetId, Class<?> targetClass, Object permission) {

        return hasPermission(targetId, targetClass.getCanonicalName(), permission);
    }
}
