package cn.jiaoyang.core.controller;

import cn.jiaoyang.core.pojo.good.Brand;
import cn.jiaoyang.service.BrandService;
import cn.jiaoyang.utils.PageResult;
import cn.jiaoyang.utils.Result;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther Jiao yang
 * @Date 2019/9/27 15:58
 * 品牌管理
 */
@RestController//返回的是json格式的数据
@RequestMapping("/brand")
public class BrandController {
//    依赖注入
  @Reference
  private BrandService brandService;
  @RequestMapping("/findAll")
//  查询所有返回值是一个brand类型的集合
    public List<Brand> finaAll(){
      List<Brand> list= brandService.findAll();
      return list;
  }


//根据主键查询
    @RequestMapping("/findOne")
    public Brand findOne(Long id){
        Brand one =brandService.findOne(id);
        return one;
    }

//  分页  page当前页 rows 每页的条数
    @RequestMapping("/search")
    public PageResult findPage(Integer page,Integer rows){
        PageResult result=brandService.findPage(page,rows);
        return result;
    }

//    添加
    @RequestMapping("/add")
//    RequestBody当前端以对象的形式向后端传数据是必须写注解
//   Result是封装的一个类里面有成功或者失败
    public Result add(@RequestBody Brand brand){
        try {
            brandService.add(brand);
            return new Result(true,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"添加失败");
        }
    }


//    删除
    @RequestMapping("/delete")
    public Result delete(Long[] ids){
        try{
            brandService.delete(ids);
            return new Result(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }


//    保存修改
@RequestMapping("/update")
public Result update(@RequestBody Brand brand){
    try{
        brandService.update(brand);
        return new Result(true,"修改成功");
    }catch (Exception e){
        e.printStackTrace();
        return new Result(false,"修改失败");
    }
}



}
