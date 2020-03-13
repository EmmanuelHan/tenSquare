package entity;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {

    private Long pageNo;

    private Long pageSize;

    private Long total;

    private List<T> rows;

    public PageResult() {
    }

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public PageResult(Long pageNo, Long pageSize, Long total, List<T> rows) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.total = total;
        this.rows = rows;
    }

}
