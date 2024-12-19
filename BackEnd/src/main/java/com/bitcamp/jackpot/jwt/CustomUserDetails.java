package com.bitcamp.jackpot.jwt;

import com.bitcamp.jackpot.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final Member member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add((GrantedAuthority) () -> {
            if(member.getGrade()==0){
                return "ROLE_ADMIN";
            }else if(member.getGrade()==2){
                return "ROLE_PREMIUM";
            }else return "ROLE_USER";

        });

        return collection;
    }

    @Override
    public String getPassword() {
        return member.getPwd();
    }

    @Override
    public String getUsername() {
        return member.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; //
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; //
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; //
    }

    @Override
    public boolean isEnabled() {
        return true; //
    }

}
