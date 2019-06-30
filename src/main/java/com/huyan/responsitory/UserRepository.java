package com.huyan.responsitory;

import com.huyan.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author 胡琰
 * @date 2019/4/1 21:30
 */
public interface UserRepository extends CrudRepository<User,Long> {

    User findByName(String username);

    User findUserByPhoneNumber(String telephone);

    User updateUsername(Long userId, String value);
    User updateEmail(Long userId, String value);
    User updatePassword(Long userId, String value);
}
