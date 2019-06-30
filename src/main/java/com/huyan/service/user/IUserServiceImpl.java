package com.huyan.service.user;

import com.huyan.base.LoginUserUtil;
import com.huyan.entity.Role;
import com.huyan.entity.User;
import com.huyan.responsitory.RoleRepository;
import com.huyan.responsitory.UserRepository;
import com.huyan.service.IUserService;
import com.huyan.service.ServiceResult;
import com.huyan.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 胡琰
 * @date 2019/4/10 16:34
 */
@Service
public class IUserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private final Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();

    @Override
    public User findUserByName(String username) {
        User user = userRepository.findByName(username);

        if (user == null){
            return null;
        }
        List<Role> roles = roleRepository.findRolesByUserId(user.getId());
        if (roles == null || roles.isEmpty()){
            throw new DisabledException("权限非法");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));
        user.setAuthorityList(authorities);
        return user;
    }

    @Override
    public ServiceResult<UserDTO> findById(Long userId) {
        return null;
    }

    @Override
    public User findUserByTelephone(String telephone) {
        return null;
    }

    @Override
    public User addUserByPhone(String telehone) {
        return null;
    }

    @Override
    @Transactional
    public ServiceResult modifyUserProfile(String profile, String value) {
        Long userId = LoginUserUtil.getLoginUserId();
        if (profile == null || profile.isEmpty()){
            return new ServiceResult(false,"属性不可以为空");
        }
        switch (profile){
            case "name":
                userRepository.updateUsername(userId,value);
                break;
            case "email":
                userRepository.updateEmail(userId,value);
                break;
            case "password":
                userRepository.updatePassword(userId,this.passwordEncoder.encodePassword(value,userId));
                break;
                default:
                    return new ServiceResult(false , "不支持的属性");
        }
        return ServiceResult.success();
    }
}
