package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.model.NoticeMessage;
import ru.otus.spring.repository.NoticeMessageRepository;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    private final AclServiceWrapperService aclServiceWrapperService;

    private final NoticeMessageRepository repository;

    public NoticeServiceImpl(AclServiceWrapperService aclServiceWrapperService, NoticeMessageRepository repository) {
        this.aclServiceWrapperService = aclServiceWrapperService;
        this.repository = repository;
    }

    @Override
    //@Transactional
    public NoticeMessage create(NoticeMessage message) {
        NoticeMessage savedMessage = repository.save(message);
        aclServiceWrapperService.createPermission(savedMessage, BasePermission.READ);
        return savedMessage;
    }

    @Override
    @PostFilter("hasPermission(filterObject, 'READ')")
    public List<NoticeMessage> getAll() {
        return repository.findAll();
    }

    @Override
    @PreAuthorize("hasPermission(#message, 'WRITE')")
    public NoticeMessage update(NoticeMessage message) {
        return repository.save(message);
    }

    @Override
    @PreAuthorize("canRead(#id, T(ru.otus.spring.model.NoticeMessage))")
    public NoticeMessage get(Integer id) {
        return repository.findById(id).get();
    }
}
