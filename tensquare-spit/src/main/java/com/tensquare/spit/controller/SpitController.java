package com.tensquare.spit.controller;

import com.tensquare.common.entity.PageResult;
import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.ResultEnum;
import com.tensquare.spit.entity.Spit;
import com.tensquare.spit.service.SpitService;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@CrossOrigin
public class SpitController {

    @Resource
    private SpitService spitService;

    @Resource
    private RedisTemplate redisTemplate;

    @GetMapping("/spit")
    public Result selectAll() {
        return new Result(spitService.selectAll());
    }

    @GetMapping("/spit/{spitId}")
    public Result selectById(@PathVariable String spitId) {
        return new Result(spitService.selectById(spitId));
    }

    @PostMapping("/spit")
    public Result save(@RequestBody Spit spit) {
        spitService.save(spit);
        return new Result(spit);
    }

    @PutMapping("/spit/{spitId}")
    public Result update(@PathVariable String spitId, @RequestBody Spit spit) {
        spit.set_id(spitId);
        spitService.save(spit);
        return new Result(spit);
    }

    @DeleteMapping("/spit/{spitId}")
    public Result delete(@PathVariable String spitId) {
        spitService.deleteById(spitId);
        return new Result(ResultEnum.SUCCESS);
    }

    @GetMapping("/spit/comment/{parentId}/{pageNo}/{pageSize}")
    public Result selectByParentId(@PathVariable String parentId, @PathVariable int pageNo, @PathVariable int pageSize) {
        Page<Spit> spits = spitService.selectByParentId(parentId, pageNo, pageSize);
        return new Result(new PageResult<Spit>(spits.getTotalElements(), spits.getContent()));
    }

    @PutMapping("/spit/thumbUp/{spitId}")
    public Result thumbUp(@PathVariable String spitId) {
        //默认用户，在用户启动后，采用真数据
        String userId = "0123456";
        //判断该用户是否已经点赞
        if (ObjectUtils.isEmpty(redisTemplate.opsForValue().get("thumbUp" + userId))) {
            spitService.thumbUp(spitId);
            redisTemplate.opsForValue().set("thumbUp" + userId, 1);
            return new Result(ResultEnum.SUCCESS);
        } else {
            return new Result(ResultEnum.REP_ERROR);
        }
    }

}
