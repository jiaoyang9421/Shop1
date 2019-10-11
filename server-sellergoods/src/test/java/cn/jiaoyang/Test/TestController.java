package cn.jiaoyang.Test;

import cn.jiaoyang.core.dao.good.BrandDao;
import cn.jiaoyang.core.pojo.good.Brand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/applicationContext*.xml"})
public class TestController {
    @Autowired
    private BrandDao brandDao;
    @Test
    public void test(){
        Brand brand = brandDao.selectByPrimaryKey(1L);
        System.out.println(brand);
    }
}
