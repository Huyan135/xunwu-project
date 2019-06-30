package com.huyan.responsitory;

import com.huyan.entity.SupportAddress;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author 胡琰
 * @date 2019/4/17 14:46
 */
public interface SupportAddressRepository extends CrudRepository<SupportAddress,Long> {
    List<SupportAddress> findAllByLevel(String level);

    SupportAddress findByEnNameAndLevel(String cityEnName, String value);
}
