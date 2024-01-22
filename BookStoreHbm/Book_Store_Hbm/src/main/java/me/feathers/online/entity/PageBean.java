package me.feathers.online.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 封装的分页数据对象
 *
 * @author Feathers
 * @create 2017-06-22-13:04
 */
public class PageBean<T> implements Serializable {

    /**
     * 封装当前页的所有数据
     */
    private List<T> datas;

    /**
     * 分页目标数据的条数
     */
    private Long count;

    /**
     * 总共有多少页
     */
    private Integer pageCount;

    /**
     * 当前的页数
     */
    private Integer pageNow;

    /**
     * 每页的条数
     */
    private Integer pageSize;

    public PageBean() {
    }

    public PageBean(Long count, Integer pageCount, Integer pageNow, Integer pageSize) {
        this.count = count;
        this.pageCount = pageCount;
        this.pageNow = pageNow;
        this.pageSize = pageSize;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPageNow() {
        return pageNow;
    }

    public void setPageNow(Integer pageNow) {
        this.pageNow = pageNow;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PageBean{");
        sb.append("count=").append(count);
        sb.append(", pageCount=").append(pageCount);
        sb.append(", pageNow=").append(pageNow);
        sb.append(", pageSize=").append(pageSize);
        sb.append('}');
        return sb.toString();
    }
}
