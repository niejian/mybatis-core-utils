package org.mybatis.plus.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @desc: org.mybatis.plus.vo.GenerateConfigVo
 * @author: niejian9001@gmail.com
 * @date: 2019-09-16 11:10
 */
public class GenerateConfigVo implements Serializable {
    /**
     * 请输入项目相对路径：如 demo-parent/demo-user
     * */
    private String relativeProjectPath;
    /**
     * 请输入模块名：如 order
     * */
    private String modelName;
    /**
     * jdbcUrl
     * jdbc:mysql://localhost:3306/test?useUnicode=true&useSSL=false&characterEncoding=utf8
     * */
    private String jdbcUrl;
    /**
     * 数据库账户名
     * */
    private String dbUserName;
    /**
     * 数据库密码
     * */
    private String dbPwd;
    /**
     *基本包路径，如：cn.com.org
     */
    private String basePackeName;

    /**
     * 数据表名
     */
    private List<String> tableNames;

    /**
     * 是否使用lomok插件
     */
    private boolean useLomok;


    public String getRelativeProjectPath() {
        return relativeProjectPath;
    }

    public void setRelativeProjectPath(String relativeProjectPath) {
        this.relativeProjectPath = relativeProjectPath;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public void setDbUserName(String dbUserName) {
        this.dbUserName = dbUserName;
    }

    public String getDbPwd() {
        return dbPwd;
    }

    public void setDbPwd(String dbPwd) {
        this.dbPwd = dbPwd;
    }

    public String getBasePackeName() {
        return basePackeName;
    }

    public void setBasePackeName(String basePackeName) {
        this.basePackeName = basePackeName;
    }

    public List<String> getTableNames() {
        return tableNames;
    }

    public void setTableNames(List<String> tableNames) {
        this.tableNames = tableNames;
    }

    public boolean getUseLomok() {
        return useLomok;
    }

    public void setUseLomok(boolean useLomok) {
        this.useLomok = useLomok;
    }
}
