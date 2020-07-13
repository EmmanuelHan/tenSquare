package entity;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {

    private Long pageNo;

    private Long pageSize;

    private Long total;

    private List<T> list;

    public PageResult() {
    }

    public PageResult(Long total, List<T> list) {
        this.total = total;
        this.list = list;
    }

    public PageResult(Long pageNo, Long pageSize, Long total, List<T> list) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.total = total;
        this.list = list;
    }

}
