package userss.service.impl;

import constant.enums.CustomResponseCode;
import constant.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;
import userss.entity.Users;
import userss.mapper.UsersMapper;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserDetailsService {
    private final UsersMapper usersMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersMapper.getOneUsers(username).orElseThrow(()-> {
                    throw new GeneralException(CustomResponseCode.USER_NOT_FOUND);
                }
        );
    }
}
