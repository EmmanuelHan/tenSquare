package ${package.Controller};

import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
import entity.Result;
import entity.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;


<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
</#if>
import org.springframework.beans.factory.annotation.Autowired;
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * @Author ${author}
 * @Date   ${date}
 */
@Slf4j
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("/${package.ModuleName}/${entity ? lower_case}")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    @Resource
    private ${table.serviceName}  ${table.serviceImplName ? uncap_first};


    /**
    * list跳转
    * @return
    */
    @RequestMapping("/mainIndex")
    public String mainIndex(){
        return "${entity ? uncap_first}/${entity ? uncap_first}_list";
    }

    /**
    * addOrUpdate 页面跳转
    * @param mv
    * @param ${entity ? uncap_first}
    * @return
    */
    @RequestMapping("/addOrUpdateIndex")
    public ModelAndView addOrUpdateIndex(ModelAndView mv ,${entity} ${entity ? uncap_first}){
        mv.setViewName("${entity ? uncap_first}/${entity ? uncap_first}_addOrUpdate");
        if(${entity ? uncap_first} != null){
            mv.addObject("obj",${entity ? uncap_first});
        }
        return mv;
    }

    /**
    * 根据条件 分页查询
    * @param ${entity ? uncap_first}
    * @param page
    * @param limit
    * @return
    */
<#if !restControllerStyle>
    @ResponseBody
</#if>
    @RequestMapping("/findByParams")
    public Result findByParams(${entity} ${entity ? uncap_first},Integer page , Integer limit){
        return ${table.serviceImplName ? uncap_first}.findByParam(${entity ? uncap_first}, page, limit);
    }

    /**
    * 新增or修改
    * @param ${entity ? uncap_first}
    * @return
    */
<#if !restControllerStyle>
    @ResponseBody
</#if>
    @RequestMapping("/addOrUpdate")
    public Result addOrUpdate(${entity} ${entity ? uncap_first}){
        try {
            ${table.serviceImplName ? uncap_first}.saveOrUpdate(${entity ? uncap_first});
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
<#if !restControllerStyle>
    @ResponseBody
</#if>
    @RequestMapping("/delByIds")
    public Result delByIds(@RequestParam("ids[]") List<Integer> ids){
        try {
            ${table.serviceImplName ? uncap_first}.removeByIds(ids);
            return new Result(ResultEnum.SUCCESS);
        }catch (Exception e){
            log.info("删除失败",e);
            return new Result(ResultEnum.ERROR);
        }
    }

 }
</#if>
