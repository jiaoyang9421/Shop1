package cn.jiaoyang.core.controller;

import cn.jiaoyang.core.pojo.item.ItemCat;
import cn.jiaoyang.service.ItemCatService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther Jiao yang
 * @Date 2019/10/3 21:30
 */
@RestController
@RequestMapping("/itemCat")
public class ItemCatController {
    @Reference
    private ItemCatService itemCatService;
    @RequestMapping("/findByParentId")
        public List<ItemCat> findByParentId(Long parentId){
        return itemCatService.findByParentId(parentId);
    }
}
