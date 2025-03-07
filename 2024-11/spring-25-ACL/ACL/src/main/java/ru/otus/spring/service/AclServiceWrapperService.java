package ru.otus.spring.service;

import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.model.Permission;

public interface AclServiceWrapperService {

    void createPermission(Object object, Permission permission);
}
