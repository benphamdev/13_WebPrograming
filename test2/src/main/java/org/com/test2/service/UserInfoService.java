package org.com.test2.service;

import java.util.List;
import java.util.Optional;

import org.com.test2.configs.UserInfoUserDetails;
import org.com.test2.entity.UserInfo;
import org.com.test2.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService implements UserDetailsService {
    @Autowired
    UserInfoRepository repository;
    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.repository = userInfoRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<UserInfo> userInfo = repository.findByUsername(username);
        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found: " + username));
    }
}