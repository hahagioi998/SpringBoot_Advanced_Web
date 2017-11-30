package com.wsh.service;

import com.wsh.domain.Girl;
import com.wsh.enums.ResultEnum;
import com.wsh.exception.GirlException;
import com.wsh.repository.GirlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wsh on 2017/11/30.
 * Girl Service层
 */
@Service
public class GirlService {

    @Autowired
    private GirlRepository girlRepository;

    //加上事务管理，要么一起成功，要么一起失败
    @Transactional
    public void addTwoGirls() {
        Girl girlA = new Girl();
        girlA.setAge(22);
        girlA.setCupSize("F");
        girlRepository.save(girlA);

        Girl girlB = new Girl();
        girlB.setAge(23);
        girlB.setCupSize("E");
        girlRepository.save(girlB);
    }

    public void getAge(Integer id) {
        Girl girl = girlRepository.findOne(id);
        Integer age = girl.getAge();
        if (age < 10) {
            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
        } else if (age >= 10 && age > 16) {
            throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
        } else if (age >= 16 && age < 100) {
            throw new GirlException(ResultEnum.SUCCESS);
        } else {
            throw new GirlException(ResultEnum.UNKNOWN_ERROR);
        }
    }

    /**
     * 根据ID查询女生的信息
     *
     * @param id 主键ID
     * @return
     */
    public Girl findOne(Integer id) {
        return girlRepository.findOne(id);
    }

    /**
     * 查询全部女生列表信息
     *
     * @return 女生列表信息
     */
    public List<Girl> findAllGirlList() {
        return girlRepository.findAll();
    }

    /**
     * 根据ID查询女生信息
     *
     * @param id 主键ID
     * @return
     */
    public Girl findById(Integer id) {
        return girlRepository.findOne(id);
    }

    /**
     * 根据ID更新女生信息
     *
     * @param id      主键ID
     * @param age     年龄
     * @param cupSize 大小
     * @return
     */
    public Girl updateGirl(Integer id, Integer age, String cupSize) {
        Girl girl = new Girl();
        girl.setId(id);
        girl.setCupSize(cupSize);
        girl.setAge(age);
        return girlRepository.save(girl);
    }

    /**
     * 根据ID删除女生信息
     *
     * @param id 主键ID
     */
    public void deleteGirl(Integer id) {
        girlRepository.delete(id);
    }
}

