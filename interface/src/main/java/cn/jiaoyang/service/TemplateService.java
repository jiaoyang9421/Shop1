package cn.jiaoyang.service;

import cn.jiaoyang.core.pojo.template.TypeTemplate;
import cn.jiaoyang.utils.PageResult;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * @Auther Jiao yang
 * @Date 2019/9/29 20:34
 */
public interface TemplateService {
    public PageResult findPage(@RequestBody TypeTemplate typeTemplate, Integer page, Integer rows);
    //    增加传入的参数为TypeTemplate
    public void add(TypeTemplate template);
    //    根据id查询
    public TypeTemplate findOne(Long id);
    public void update(TypeTemplate template);
    public void delete(Long[] ids);
    public List<Map> findBySpecList(Long id);
}
