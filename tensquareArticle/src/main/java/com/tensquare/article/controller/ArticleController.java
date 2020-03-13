package com.tensquare.article.controller;

import com.tensquare.article.entity.Article;
import com.tensquare.article.service.IArticleService;
import entity.Result;
import entity.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author HanLei
 * @Date   2020-03-12
 */
@Slf4j
@Controller
@RequestMapping("/article/article")
public class ArticleController {

    @Resource
    private IArticleService  articleServiceImpl;


    /**
    * list跳转
    * @return
    */
    @RequestMapping("/mainIndex")
    public String mainIndex(){
        return "article/article_list";
    }

    /**
    * addOrUpdate 页面跳转
    * @param mv
    * @param article
    * @return
    */
    @RequestMapping("/addOrUpdateIndex")
    public ModelAndView addOrUpdateIndex(ModelAndView mv ,Article article){
        mv.setViewName("article/article_addOrUpdate");
        if(article != null){
            mv.addObject("obj",article);
        }
        return mv;
    }

    /**
    * 根据条件 分页查询
    * @param article
    * @param page
    * @param limit
    * @return
    */
    @ResponseBody
    @RequestMapping("/findByParams")
    public Result findByParams(Article article,Integer page , Integer limit){
        return articleServiceImpl.findByParam(article, page, limit);
    }

    /**
    * 新增or修改
    * @param article
    * @return
    */
    @ResponseBody
    @RequestMapping("/addOrUpdate")
    public Result addOrUpdate(Article article){
        try {
            articleServiceImpl.saveOrUpdate(article);
            return new Result(ResultEnum.SUCCESS);
        }catch (Exception e){
            log.info("新增或修改失败",e);
            return new Result(ResultEnum.ERROR);
        }
    }

    /**
    * 删除
    * @param ids
    * @return
    */
    @ResponseBody
    @RequestMapping("/delByIds")
    public Result delByIds(@RequestParam("ids[]") List<Integer> ids){
        try {
            articleServiceImpl.removeByIds(ids);
            return new Result(ResultEnum.SUCCESS);
        }catch (Exception e){
            log.info("删除失败",e);
            return new Result(ResultEnum.ERROR);
        }
    }

 }
