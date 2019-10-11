package cn.jiaoyang.service;

import cn.jiaoyang.core.pojo.good.Brand;
import cn.jiaoyang.utils.PageResult;
import com.alibaba.dubbo.config.annotation.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther Jiao yang
 * @Date 2019/9/27 19:05
 */

public interface BrandService {
    public List<Brand> findAll();


    //分页
//    private long total;//总记录数
//    private List rows;//当前页容量
    public PageResult findPage(Integer page, Integer rows);



    //  增加
    public void add(Brand brand);
    //    根据id查询
    public Brand findOne(Long id);
    //    修改
    public void  update(Brand brand);
    //删除
    public void delete(Long[] ids);
    public List<Map> selectOptionList();
}
