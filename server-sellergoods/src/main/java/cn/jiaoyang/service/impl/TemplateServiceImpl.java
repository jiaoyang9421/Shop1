package cn.jiaoyang.service.impl;

import cn.jiaoyang.core.dao.specification.SpecificationOptionDao;
import cn.jiaoyang.core.dao.template.TypeTemplateDao;
import cn.jiaoyang.core.pojo.template.TypeTemplate;
import cn.jiaoyang.core.pojo.template.TypeTemplateQuery;
import cn.jiaoyang.service.TemplateService;
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
 * @Date 2019/9/29 20:34
 * 模板管理
 */
@Service
@Transactional
public class TemplateServiceImpl implements TemplateService{
    @Autowired
    private TypeTemplateDao templateDao;
    @Autowired
    private SpecificationOptionDao specificationOptionDao;
    @Override
    public PageResult findPage(TypeTemplate typeTemplate, Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        TypeTemplateQuery query = new TypeTemplateQuery();
        TypeTemplateQuery.Criteria criteria = query.createCriteria();
        if(typeTemplate!=null){
            if(typeTemplate.getName()!=null&&!"".equals(typeTemplate.getName())){
                criteria.andNameLike("%"+typeTemplate.getName()+"%");
            }
        }
        Page<TypeTemplate> typeTemplates = (Page<TypeTemplate>) templateDao.selectByExample(query);
            return new PageResult(typeTemplates.getTotal(),typeTemplates.getResult());
          }

    @Override
    public void add(TypeTemplate template) {
        templateDao.insertSelective(template);
    }

    @Override
    public TypeTemplate findOne(Long id) {

        return templateDao.selectByPrimaryKey(id);
    }

    @Override
    public void update(TypeTemplate template) {
        templateDao.updateByPrimaryKeySelective(template);
    }

    @Override
    public void delete(Long[] ids) {
        if(ids!=null){
            for(Long id:ids){
                templateDao.deleteByPrimaryKey(id);
            }
        }
    }

    @Override
    public List<Map> findBySpecList(Long id) {
        return null;
    }


}
