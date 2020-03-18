package entity;

import org.springframework.util.ObjectUtils;
import util.StringUtil;

public class Page {

    private Integer pageSize;

    private Integer pageNo;

    private Integer total;

    public Integer getPageSize() {
        if(ObjectUtils.isEmpty(pageSize)){
            pageSize = StringUtil.PAGE_SIZE;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        if(ObjectUtils.isEmpty(pageNo)){
            pageNo = StringUtil.START_PAGE;
        }
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
