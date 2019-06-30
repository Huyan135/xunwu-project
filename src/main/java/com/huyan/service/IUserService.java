package com.huyan.service;

import com.huyan.entity.User;
import com.huyan.web.dto.UserDTO;

/**
 * 用户服务
 * @author 胡琰
 * @date 2019/4/10 16:30
 */
public interface IUserService{
    User findUserByName(String userName);

    ServiceResult<UserDTO> findById(Long userId);

    /**
     * 根据电话号码寻找用户
     * @param telephone
     * @return
     */
    User findUserByTelephone(String telephone);

    /**
     * 通过手机号注册用户
     * @param telehone
     * @return
     */
    User addUserByPhone(String telehone);

    /**
     * 修改指定属性值
     * @param profile
     * @param value
     * @return
     */
    ServiceResult modifyUserProfile(String profile, String value);
}
