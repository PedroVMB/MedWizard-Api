package com.MedWizard.infrastructure.security;

import com.MedWizard.domain.user.User;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HierarchyService {
    private final RoleHierarchy roleHierarchy;

    public HierarchyService(RoleHierarchy roleHierarchy) {
        this.roleHierarchy = roleHierarchy;
    }

    public boolean userNotHavePermission(User logged, User actor, String desiredProfile){
        for(GrantedAuthority authority: logged.getAuthorities()){
            var authoritiesReachable =  roleHierarchy.getReachableGrantedAuthorities(List.of(authority));

            for(GrantedAuthority perfil: authoritiesReachable){
                if(perfil.getAuthority().equals(desiredProfile) || logged.getId().equals(actor.getId()))
                    return false;
            }
        }
        return true;
    }
}
