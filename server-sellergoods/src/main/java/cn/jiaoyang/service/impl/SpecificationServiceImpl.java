package cn.jiaoyang.service.impl;

import cn.jiaoyang.core.dao.specification.SpecificationDao;
import cn.jiaoyang.core.dao.specification.SpecificationOptionDao;
import cn.jiaoyang.core.pojo.specification.Specification;
import cn.jiaoyang.core.pojo.specification.SpecificationOption;
import cn.jiaoyang.core.pojo.specification.SpecificationOptionQuery;
import cn.jiaoyang.core.pojo.specification.SpecificationQuery;
import cn.jiaoyang.service.SpecificationService;
import cn.jiaoyang.utils.PageResult;
import cn.jiaoyang.core.pojo.entity.SpecEntity;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Auther Jiao yang
 * @Date 2019/9/29 14:38
 */
@Service
@Transactional
public class SpecificationServiceImpl implements SpecificationService {
    @Autowired
    private SpecificationDao specificationDao;
    @Autowired
    private SpecificationOptionDao specificationOptionDao;


    @Override
    public PageResult search(Specification spec, Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        SpecificationQuery query = new SpecificationQuery();
        SpecificationQuery.Criteria criteria = query.createCriteria();
//        首先判断传过来的规格是否为空
        if(spec!=null){
//            然后判断规格的名称是否为空或者空字符串
                        if(spec.getSpecName()!=null&&!"".equals(spec.getSpecName())){
//                            封装名字像这样的
                            criteria.andSpecNameLike("%"+spec.getSpecName()+"%");
                        }
//                        查询满足条件的
        }
        Page<Specification> specifications = (Page<Specification>) specificationDao.selectByExample(query);

        return  new PageResult(specifications.getTotal(),specifications.getResult());
    }



    @Override
    public void add(SpecEntity specEntity) {
//        新建功能里面包括2张表的内容所以要把2张表的内容封装起来
        specificationDao.insertSelective(specEntity.getSpecification());
        if(specEntity.getSpecificationOptionList()!=null){
            for (SpecificationOption option:specEntity.getSpecificationOptionList()){
//                设置id和规格保持一致
                option.setSpecId(specEntity.getSpecification().getId());
//                遍历得到的结果是规格选项和orders
                specificationDao.insertSelective(option);
            }
        }
    }

    @Override
    public SpecEntity findOne(Long id) {
//        根据id查询规格表
        Specification specification = specificationDao.selectByPrimaryKey(id);
//        封装满足条件的id
        SpecificationOptionQuery query = new SpecificationOptionQuery();
        SpecificationOptionQuery.Criteria criteria = query.createCriteria();
                criteria.andIdEqualTo(id);
//                格局条件查询

        List<SpecificationOption> specificationOptions = specificationOptionDao.selectByExample(query);
//     把结果封装到specEntity类中
        SpecEntity specEntity = new SpecEntity();
        specEntity.setSpecification(specification);
        specEntity.setSpecificationOptionList(specificationOptions);
        return specEntity;
    }

    @Override
    public void update(SpecEntity specEntity) {
//        根据规格对象进行更新
        specificationDao.updateByPrimaryKeySelective(specEntity.getSpecification());
//        封装满足条件的规格选项
        SpecificationOptionQuery query = new SpecificationOptionQuery();
        SpecificationOptionQuery.Criteria criteria = query.createCriteria();
        criteria.andSpecIdEqualTo(specEntity.getSpecification().getId());
//        首先要删除里面原有的
        specificationOptionDao.deleteByExample(query);
        List<SpecificationOption> optionList = specEntity.getSpecificationOptionList();
        if(optionList!=null){
            for(SpecificationOption option:optionList){
//                插入规格选项的id
                option.setSpecId(specEntity.getSpecification().getId());
//                插入规格选项的值
                specificationOptionDao.insertSelective(option);
            }
        }

    }

    @Override
    public void delete(Long[] ids) {
       if(ids!=null){
           for(Long id:ids){
               specificationDao.deleteByPrimaryKey(id);
               SpecificationOptionQuery query = new SpecificationOptionQuery();
               SpecificationOptionQuery.Criteria criteria = query.createCriteria();
               criteria.andSpecIdEqualTo(id);
               specificationOptionDao.deleteByExample(query);
           }
       }
    }

    @Override
    public List<Map> selectOptionList() {
        return specificationDao.selectOptionList();
    }


}
