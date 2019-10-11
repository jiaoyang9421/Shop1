package cn.jiaoyang.service;

import cn.jiaoyang.core.pojo.specification.Specification;
import cn.jiaoyang.utils.PageResult;
import cn.jiaoyang.core.pojo.entity.SpecEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * @Auther Jiao yang
 * @Date 2019/9/29 14:36
 */
public interface SpecificationService {
    public void add(@RequestBody SpecEntity specEntity);
    public SpecEntity findOne(Long id);
    public void update(@RequestBody SpecEntity specEntity);
    public void delete(Long[] ids);
    public List<Map> selectOptionList();
    public PageResult search(@RequestBody Specification spec, Integer page, Integer rows);
}
