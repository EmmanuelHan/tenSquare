package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import entity.Result;

 /**
 *  代码生成器
 *  service 接口
 * @Author ${author}
 * @Date   ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    /**
     * 分页查询
     * @param ${entity ? uncap_first}
     * @param page
     * @param limit
     * @return
     */
    Result findByParam(${entity} ${entity ? uncap_first},Integer page , Integer limit);
}
</#if>
