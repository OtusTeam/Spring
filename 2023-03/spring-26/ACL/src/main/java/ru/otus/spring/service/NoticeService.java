package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
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
import ru.otus.spring.model.NoticeMessage;
import ru.otus.spring.repository.NoticeMessageRepository;

@Service
public class NoticeService {
    @Autowired
    protected MutableAclService mutableAclService;

    @Autowired
    private NoticeMessageRepository repository;

    public void add( NoticeMessage noticeMessage ) {
        repository.save( noticeMessage );
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final Sid owner = new PrincipalSid( authentication );
        ObjectIdentity oid = new ObjectIdentityImpl( noticeMessage.getClass(), noticeMessage.getId() );

        final Sid admin = new GrantedAuthoritySid("ROLE_EDITOR");

        MutableAcl acl = mutableAclService.createAcl( oid );
        acl.setOwner( owner );
        acl.insertAce( acl.getEntries().size(), BasePermission.ADMINISTRATION, admin, true );

        mutableAclService.updateAcl( acl );


    }
}
