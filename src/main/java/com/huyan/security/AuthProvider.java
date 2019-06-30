package com.huyan.security;

import com.huyan.entity.User;
import com.huyan.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @author 胡琰
 * @date 2019/4/10 16:25
 */
public class AuthProvider implements AuthenticationProvider {
    @Autowired
    private IUserService userService;
    private final Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String inputpassword = (String) authentication.getCredentials();

        User user = userService.findUserByName(username);
        if (user == null) {
            throw new AuthenticationCredentialsNotFoundException("authError");
        }

        if (this.passwordEncoder.isPasswordValid(user.getPassword(), inputpassword, user.getId())) {
            return new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());
        }
        throw new BadCredentialsException("authError");//登录失败后跳出来的（authError网页链接上会跳出来到这个页面）
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
