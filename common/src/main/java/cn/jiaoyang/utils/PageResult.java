package cn.jiaoyang.utils;

import java.io.Serializable;
import java.util.List;


public class PageResult implements Serializable {
    private long total;//总记录数
    private List result;//当前页结果集,每页具体的泛行
//    必须实现构造方法方便往里面传参数

    public PageResult() {
    }

    public PageResult(long total, List result) {
        this.total = total;
        this.result = result;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getResult() {
        return result;
    }

    public void setResult(List result) {
        this.result = result;
    }
}
