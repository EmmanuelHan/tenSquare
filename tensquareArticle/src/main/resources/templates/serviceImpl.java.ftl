package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import util.StringUtil;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author ${author}
 * @Date   ${date}
 */
@Slf4j
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    @Resource
    private ${table.mapperName} ${table.mapperName ? uncap_first};

    @Override
    public List<${entity}> list() {

       return ${table.mapperName ? uncap_first}.selectList(null);
    }

    /**
    * 分页查询
    *
    * @param ${entity ? uncap_first}  page  limit
    * @return jsonResponse
    */
    @Override
    public Result findByParam(${entity} ${entity ? uncap_first},Integer page , Integer limit) {

        if(page == null){
        page = StringUtil.START_PAGE;
        }
        if(limit == null){
        limit = StringUtil.PAGE_SIZE;
        }
        //开启分页
        Page ${entity ? uncap_first}Page = new Page(page,limit);
        //查询构造器
        Wrapper wrapper = new QueryWrapper();

    <#list table.fields as field>
        if(${entity ? uncap_first}.get${field.propertyName ? cap_first}()!=null && !"".equals(${entity ? uncap_first}.get${field.propertyName ? cap_first}())){
            ((QueryWrapper) wrapper).eq("${field.name}",${entity ? uncap_first}.get${field.propertyName ? cap_first}());
        }
    </#list>
        IPage<${entity}> ${entity ? uncap_first}IPage = ${table.mapperName ? uncap_first}.selectPage(${entity ? uncap_first}Page, wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("pageSize", page);
        data.put("total", ${entity ? uncap_first}Page.getTotal());
        data.put("pageNo", ${entity ? uncap_first}Page.getCurrent());
        data.put("list", ${entity ? uncap_first}IPage.getRecords());
        return new Result(data);
    }

}
</#if>
