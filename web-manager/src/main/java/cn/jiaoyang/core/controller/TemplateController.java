package cn.jiaoyang.core.controller;

import cn.jiaoyang.core.pojo.template.TypeTemplate;
import cn.jiaoyang.service.TemplateService;
import cn.jiaoyang.utils.PageResult;
import cn.jiaoyang.utils.Result;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther Jiao yang
 * @Date 2019/9/29 20:31
 *
 */
@RestController
@RequestMapping("/typeTemplate")
public class TemplateController {
    @Reference
    private TemplateService templateService;
    public PageResult search(@RequestBody TypeTemplate typeTemplate, Integer page, Integer rows){
        return templateService.findPage(typeTemplate,page,rows);
    }
    //添加
    @RequestMapping("/add")
    public Result add(@RequestBody TypeTemplate template){
        try {
            templateService.add(template);
            return new Result(true,"添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"添加失败");
        }
    }
    @RequestMapping("/findOne")
    public TypeTemplate findOne(Long id){
        return templateService.findOne(id);
    }
    @RequestMapping("/update")
    public Result update(@RequestBody TypeTemplate template){
        try {
            templateService.update(template);
            return new Result(true,"修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"修改失败");
        }
    }
    @RequestMapping("/delete")
    public Result delete(Long[] ids) {
        try {
            templateService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }
}
