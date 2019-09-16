<#--controller 生成模板。生成对应的增删改的方法-->
package ${package.Controller};

import ${package.Entity}.${table.entityName};
import ${package.Service}.${table.serviceName};
import org.springframework.web.bind.annotation.RequestMapping;
import cn.com.bluemoon.middleware.mybatis.instance.CommonInstance;
import cn.com.bluemoon.middleware.mybatis.log.CommonFunction;
import cn.com.bluemoon.middleware.mybatis.dto.BaseResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


import java.util.List;
import java.util.ArrayList;
<#if entityLombokModel>
import lombok.extern.slf4j.Slf4j;

</#if>
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
* <p>
* ${table.comment!} 前端控制器
* </p>
*
* @author ${author}
* @since ${date}
*/
<#if entityLombokModel>
@Slf4j
</#if>
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
<#--@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityName}</#if>")-->
@RequestMapping("/${table.entityName?uncap_first}")
<#if kotlin>
    class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    <#--声明日志-->
    <#if !entityLombokModel>
    private static final Logger log = LoggerFactory.getLogger(${table.controllerName}.class);
    </#if>
    @Autowired
    private ${table.serviceName} ${(table.serviceName?substring(1))?uncap_first};

    /**
    * @param jsonObject {page:{pageNum: 1, pageSize:10}, data: {fieldName: fieldValue}}
    *
    * 分页获取${table.entityName}列表信息
    */
    @PostMapping(value="/list")
    public BaseResponseDto<IPage<${table.entityName}>> list(@RequestBody JSONObject jsonObject) {
        BaseResponseDto<IPage<${table.entityName}>> baseResponseDto = new BaseResponseDto<>();
        CommonFunction.beforeProcess(log, jsonObject);
        Boolean isSuccess = false;
        boolean isContinue = true;
        String responseMsg = "请求失败";
        Integer responseCode = -1;
        IPage<${table.entityName}> data = null;

        try{

            // 分页查询
            JSONObject page = jsonObject.getJSONObject("page");
            // 查询条件
            JSONObject queryData = jsonObject.getJSONObject("data");

            int pageNum = page.optInt("pageNum", 1);
            int pageSize = page.optInt("pageSize", 10);
            Page queryPage = new Page(pageNum, pageSize);
            // 构造查询条件
            QueryWrapper<${table.entityName}> queryWrapper = new QueryWrapper<>();
            <#--获取所有的字段信息-->
            // 未组装date类型查询条件，请按照实际业务情况自行填写
            <#list table.fields as field>
                <#--操作非日期类型-->
                <#if field.columnType != 'DATE'>
<#--                    ${field.columnType?lower_case?cap_first} ${field.propertyName} = queryData.optString("${field.propertyName}", null);-->
                <#-- 统一按照String处理-->
            String  ${field.propertyName} = queryData.optString("${field.propertyName}", null);
            if (null != ${field.propertyName}) {
                queryWrapper.eq("${field.name}", ${field.propertyName});
            }

                    <#--
                    <#if field.columnType == 'STRING'>
                        ${field.columnType?lower_case?cap_first} ${field.propertyName} = queryData.opt${field.columnType?lower_case?cap_first}("${field.propertyName}", null);
                        <#else >

                    </#if>
                    -->
                </#if>
            </#list>
            data = this.${(table.serviceName?substring(1))?uncap_first}.page(queryPage, queryWrapper);


            responseMsg = CommonInstance.SUCCESS_MSG;
            responseCode = CommonInstance.SUCCESS_CODE;
            isSuccess = CommonInstance.SUCCESS;
        }catch(Exception e){
            CommonFunction.genErrorMessage(log, e);
            e.printStackTrace();
        }

        CommonFunction.afterProcess(log, responseMsg, isSuccess);

        return baseResponseDto.responseCode(responseCode)
        .responseMsg(responseMsg)
        .success(isSuccess)
        .data(data);
    }

    /**
    *
    * 添加${table.entityName}列表信息
    */
    @PostMapping(value="/add")
    public BaseResponseDto<${table.entityName}> add(@RequestBody JSONObject jsonObject) {
        BaseResponseDto<${table.entityName}> baseResponseDto = new BaseResponseDto<>();
        CommonFunction.beforeProcess(log, jsonObject);
        Boolean isSuccess = false;
        boolean isContinue = true;
        String responseMsg = "请求失败";
        Integer responseCode = -1;
        ${table.entityName} entity = new ${table.entityName}();

        try{
            //TODO 新增校验及逻辑




            responseMsg = CommonInstance.SUCCESS_MSG;
            responseCode = CommonInstance.SUCCESS_CODE;
            isSuccess = CommonInstance.SUCCESS;
        }catch(Exception e){
            CommonFunction.genErrorMessage(log, e);
            e.printStackTrace();
        }

        CommonFunction.afterProcess(log, responseMsg, isSuccess);

        return baseResponseDto.responseCode(responseCode)
        .responseMsg(responseMsg)
        .success(isSuccess)
        .data(entity);
    }

    /**
    *
    * 更新${table.entityName}列表信息
    */
    @PostMapping(value="/update")
    public BaseResponseDto<${table.entityName}> update(@RequestBody JSONObject jsonObject) {
        BaseResponseDto<${table.entityName}> baseResponseDto = new BaseResponseDto<>();
        CommonFunction.beforeProcess(log, jsonObject);
        Boolean isSuccess = false;
        boolean isContinue = true;
        String responseMsg = "请求失败";
        Integer responseCode = -1;
        ${table.entityName} entity = new ${table.entityName}();

        try{
            //TODO 更新校验及逻辑

            responseMsg = CommonInstance.SUCCESS_MSG;
            responseCode = CommonInstance.SUCCESS_CODE;
            isSuccess = CommonInstance.SUCCESS;
        }catch(Exception e){
            CommonFunction.genErrorMessage(log, e);
            e.printStackTrace();
        }

        CommonFunction.afterProcess(log, responseMsg, isSuccess);

        return baseResponseDto.responseCode(responseCode)
        .responseMsg(responseMsg)
        .success(isSuccess)
        .data(entity);
    }


    /**
    *
    * demo
    */
    @PostMapping(value="/demo")
    public BaseResponseDto<String> update(@RequestBody JSONObject jsonObject) {
        BaseResponseDto<String> baseResponseDto = new BaseResponseDto<>();
        CommonFunction.beforeProcess(log, jsonObject);
        Boolean isSuccess = false;
        boolean isContinue = true;
        String responseMsg = "请求失败";
        Integer responseCode = -1;

        try{
        //TODO 具体逻辑

        responseMsg = CommonInstance.SUCCESS_MSG;
        responseCode = CommonInstance.SUCCESS_CODE;
        isSuccess = CommonInstance.SUCCESS;
        }catch(Exception e){
        CommonFunction.genErrorMessage(log, e);
        e.printStackTrace();
        }

        CommonFunction.afterProcess(log, responseMsg, isSuccess);

        return baseResponseDto.responseCode(responseCode)
        .responseMsg(responseMsg)
        .success(isSuccess)
        .data(responseMsg);
    }

}
</#if>
