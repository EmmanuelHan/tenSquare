package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.entity.Spit;
import com.tensquare.spit.util.TYPE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.SnowFlakeIdGenerator;

import java.util.List;

@Service
@Transactional
public class SpitService {

    @Autowired
    private SpitDao spitDao;

    @Autowired
    private SnowFlakeIdGenerator idGenerator;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Spit> selectAll(){
        return spitDao.findAll();
    }

    public Spit selectById(String id){
        return spitDao.findById(id).get();
    }

    public void save(Spit spit){
        spit.set_id(idGenerator.nextId());
        spitDao.save(spit);
    }

    public void update(Spit spit){
        spitDao.save(spit);
    }

    public void deleteById(String id){
        spitDao.deleteById(id);
    }

    public Page<Spit> selectByParentId(String parentId,int pageNo,int pageSize){
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return spitDao.findByParentId(parentId, pageable);
    }

    public void thumbIp(String spitId) {
        //方式一，效率偏低
//        Spit spit = spitDao.findById(spitId).get();
//        Integer thumbUp = spit.getThumbUp();
//        spit.setThumbUp(ObjectUtils.isEmpty(thumbUp)?1:thumbUp+1);
//        spitDao.save(spit);
        //方式二 使用原生mongo命令来实现自增db.spit.update({"_id":”1"},{$inc: (thumbup :NumberInt(1)}})
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(spitId));
        Update update = new Update();
        update.inc("thumbUp",1);
        mongoTemplate.updateFirst(query,update, TYPE.SPIT);

    }
}
