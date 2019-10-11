package cn.jiaoyang.core.controller;

import cn.jiaoyang.core.pojo.specification.Specification;
import cn.jiaoyang.service.SpecificationService;
import cn.jiaoyang.utils.PageResult;
import cn.jiaoyang.utils.Result;
import cn.jiaoyang.core.pojo.entity.SpecEntity;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Auther Jiao yang
 * @Date 2019/9/29 14:31
 * 规格管理
 */
@RestController
@RequestMapping("/specification")
public class SpecController {
    @Reference
    private SpecificationService specificationService;
    @RequestMapping("/search")
    public PageResult search(@RequestBody Specification spec,Integer page, Integer rows){
        PageResult result = specificationService.search(spec,page,rows);
        return result;
    }
    @RequestMapping("/add")
    public Result add(@RequestBody SpecEntity specEntity){
        try {
            specificationService.add(specEntity);
            return new Result(true,"添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"添加失败");
        }
    }


    @RequestMapping("/findOne")
    public SpecEntity findOne(Long id){
        SpecEntity one = specificationService.findOne(id);
        return one;

    }



    @RequestMapping("/update")
    public Result update(@RequestBody SpecEntity specEntity){
        try {
            specificationService.update(specEntity);
            return new Result(true,"更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"更新失败");
        }
    }
    // 批量删除
    @RequestMapping("/delete")
    public Result delete(Long[] ids){
        try {
            specificationService.delete(ids);
            return new Result(true,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }
    @RequestMapping("/selectOptionList")
    public List<Map> selectOptionList(){
        return specificationService.selectOptionList();
    }
}
