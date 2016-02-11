package com.deswaef.wowscrappie.usermanagement.util;

import com.deswaef.wowscrappie.usermanagement.domain.RoleEnum;
import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;
import com.deswaef.wowscrappie.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AdministratorsCache {

    private Set<ScrappieUser> admins = new HashSet<>();
    @Autowired
    private UserService userService;


    @PostConstruct
    public void init() {
        admins = userService.findAll()
                .stream()
                .filter(x ->
                                x.getAuthorities()
                                        .stream()
                                        .anyMatch(auth -> auth.getAuthority().equals(RoleEnum.ADMIN_ROLE.getUserRole()))
                ).collect(Collectors.toSet());
    }

    public Set<ScrappieUser> getAdmins() {
        return admins;
    }
}
