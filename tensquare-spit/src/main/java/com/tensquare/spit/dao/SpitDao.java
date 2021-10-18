package com.tensquare.spit.dao;

import com.tensquare.spit.entity.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpitDao extends MongoRepository<Spit,String> {

    /**
     * 根据父编号查询数据，并分页
     * @param parentId
     * @param pageable
     * @return
     */
    public Page<Spit> findByParentId(String parentId, Pageable pageable);

}
