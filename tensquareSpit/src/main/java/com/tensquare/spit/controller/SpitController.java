package com.tensquare.spit.controller;

import com.tensquare.spit.entity.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public Result selectAll(){
        return new Result(spitService.selectAll());
    }

    @RequestMapping(value = "/{spitId}",method = RequestMethod.GET)
    public Result selectById(@PathVariable String spitId){
        return new Result(spitService.selectById(spitId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Spit spit){
        spitService.save(spit);
        return new Result(spit);
    }

    @RequestMapping(value = "/{spitId}",method = RequestMethod.PUT)
    public Result update(@PathVariable String spitId,@RequestBody Spit spit){
        spit.set_id(spitId);
        spitService.save(spit);
        return new Result(spit);
    }

    @RequestMapping(value = "/{spitId}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable String spitId){
        spitService.deleteById(spitId);
        return new Result(ResultEnum.SUCCESS);
    }

    @RequestMapping(value = "/comment/{parentId}/{pageNo}/{pageSize}",method = RequestMethod.GET)
    public Result selectByParentId(@PathVariable String parentId,@PathVariable int pageNo,@PathVariable int pageSize){
        Page<Spit> spits = spitService.selectByParentId(parentId, pageNo, pageSize);
        return new Result(new PageResult<Spit>(spits.getTotalElements(),spits.getContent()));
    }

    @RequestMapping(value = "/thumbUp/{spitId}",method = RequestMethod.PUT)
    public Result thumbUp(@PathVariable String spitId){
        //默认用户，在用户启动后，采用真数据
        String userId = "0123456";
        //判断该用户是否已经点赞
        if(ObjectUtils.isEmpty(redisTemplate.opsForValue().get("thumbUp"+userId))){
            spitService.thumbIp(spitId);
            redisTemplate.opsForValue().set("thumbUp"+userId,1);
            return new Result(ResultEnum.SUCCESS);
        } else {
            return new Result(ResultEnum.REP_ERROR);
        }
    }

}
