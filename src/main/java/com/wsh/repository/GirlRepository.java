package com.wsh.repository;

import com.wsh.domain.Girl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by wsh on 2017/11/30.
 * Girl Repository JPA层
 */
public interface GirlRepository extends JpaRepository<Girl, Integer> {

    /**
     * 根据年龄查找女生列表信息
     *
     * @param age 年龄
     * @return 女生列表信息
     */
    List<Girl> findByAge(Integer age);

}
