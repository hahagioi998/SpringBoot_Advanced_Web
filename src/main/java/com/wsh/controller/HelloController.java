package com.wsh.controller;

import com.wsh.constrant.GlobalConstrant;
import com.wsh.domain.Girl;
import com.wsh.domain.Result;
import com.wsh.properties.GirlProperties;
import com.wsh.repository.GirlRepository;
import com.wsh.service.GirlService;
import com.wsh.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by wsh on 2017/11/29.
 * Controller层
 */
@RestController
//@Controller
//@ResponseBody
@RequestMapping("/girl")
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

//    @Value("${cupSize}")
//    private String cupSize;
//
//    @Value("${age}")
//    private Integer age;
//
//    @Value("${content}")
//    private String content;

    @Autowired
    private GirlProperties girlProperties;

    //由于业务逻辑相对简单，直接在Controller调用JPA层操作数据库的方法
    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private GirlService girlService;

    //配置多个映射的url
    @RequestMapping(value = {"/hello", "/hi"}, method = RequestMethod.GET)
    public String say() {
//        return "Hello Spring Boot!" + cupSize + age + "=====>" + content;
        return girlProperties.getCupSize();
    }

    //@PathVariable接收url传过来的参数
    @RequestMapping(value = "/say2/{id}", method = RequestMethod.GET)
    public String say2(@PathVariable("id") Integer id) {
        return "id = " + id;
    }

    //@RequestParam接收请求参数的值  如?id=xxx   defaultValue指定默认值
//    @RequestMapping(value = "/say3",method = RequestMethod.GET)
    @GetMapping(value = "/say3")
    public String say3(@RequestParam(value = "id", required = false, defaultValue = "0") Integer myId) {
        return "id = " + myId;
    }

    /**
     * 查询全部女生列表信息
     *
     * @return 女生列表信息
     */
    @GetMapping(value = "/findAllGirlList")
    public List<Girl> findAllGirlList() {
        return girlService.findAllGirlList();
    }

    /**
     * 新增女生信息
     *
     * @return
     */
    @PostMapping(value = "/addGirl")
    public Result<Girl> addGirl(@Valid Girl girl, BindingResult bindingResult) {
        //BindingResult用于接收错误信息
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(GlobalConstrant.errorCode, bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(girlRepository.save(girl));
    }

    /**
     * 根据ID查询女生信息
     *
     * @param id 主键ID
     * @return
     */
    @GetMapping(value = "/findById/{id}")
    public Girl findById(@PathVariable(value = "id") Integer id) {
        return girlService.findById(id);
    }

    /**
     * 根据ID更新女生信息
     *
     * @param id      主键ID
     * @param age     年龄
     * @param cupSize 大小
     * @return
     */
    @PutMapping(value = "/updateGirl/{id}")
    public Girl updateGirl(@PathVariable(value = "id") Integer id, @RequestParam(value = "age") Integer age, @RequestParam(value = "cupSize") String cupSize) {
        return girlService.updateGirl(id, age, cupSize);
    }

    /**
     * 根据ID删除女生信息
     *
     * @param id 主键ID
     */
    @DeleteMapping(value = "/deleteGirl/{id}")
    public void deleteGirl(@PathVariable(value = "id") Integer id) {
        girlService.deleteGirl(id);
    }

    /**
     * 根据年龄查找女生信息
     *
     * @param age 年龄
     * @return
     */
    @GetMapping(value = "/findGirlListByAge/{age}")
    public List<Girl> findGirlListByAge(@PathVariable(value = "age") Integer age) {
        return girlRepository.findByAge(age);
    }

    /**
     * 同时添加两个女生数据
     */
    @PostMapping(value = "/addTwoGirls")
    public void addTwoGirls() {
        girlService.addTwoGirls();
    }

    @GetMapping(value = "/getAge/{id}")
    public void getAge(@PathVariable("id") Integer id) {
        girlService.getAge(id);
    }

}
