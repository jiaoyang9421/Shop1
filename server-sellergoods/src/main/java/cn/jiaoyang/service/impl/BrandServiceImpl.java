package cn.jiaoyang.service.impl;

import cn.jiaoyang.core.dao.good.BrandDao;
import cn.jiaoyang.core.pojo.good.Brand;
import cn.jiaoyang.service.BrandService;
import cn.jiaoyang.utils.PageResult;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Auther Jiao yang
 * @Date 2019/9/27 19:07
 */
@Service
@Transactional
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandDao brandDao;
    @Override
    public List<Brand> findAll() {
//        查询所有
        return brandDao.selectByExample(null);
    }

    @Override
    public PageResult findPage(Integer page, Integer rows) {
//        开始分页
        PageHelper.startPage(page,rows);
        //        查询所有
        Page<Brand> page1 = (Page<Brand>) brandDao.selectByExample(null);
//        long total = page1.getTotal();
//        List<Brand> result = page1.getResult();
//        PageResult pageResult = new PageResult(total, result);
//        在pojo的实体类中已经对其进行了方法的构造
        //返回总条数和当前页结果会自动分页
        PageResult pageResult = new PageResult(page1.getTotal(), page1.getResult());
        return pageResult;
    }

    @Override
    public void add(Brand brand) {
        brandDao.insert(brand);
    }

    @Override
    public Brand findOne(Long id) {
//        根据主键查询
        return brandDao.selectByPrimaryKey(id);
    }

    @Override
    public void update(Brand brand) {
        Brand one = findOne(brand.getId());
        brandDao.updateByPrimaryKey(brand);
    }

    @Override
    public void delete(Long[] ids) {
           for(Long id:ids){
               brandDao.deleteByPrimaryKey(id);
           }
    }

    @Override
    public List<Map> selectOptionList() {
        return null;
    }
}
