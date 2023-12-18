package com.wms.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author llw
 * @since 2023-12-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Menu对象", description="菜单栏(代码生成器生成的变量是全小写)")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "菜单编码")
    @TableField("menuCode")
    private String menucode;

    @ApiModelProperty(value = "菜单名字")
    @TableField("menuName")
    private String menuname;

    @ApiModelProperty(value = "菜单级别")
    @TableField("menuLevel")
    private String menulevel;

    @ApiModelProperty(value = "菜单的父code")
    @TableField("menuParentCode")
    private String menuparentcode;

    @ApiModelProperty(value = "点击触发的函数")
    @TableField("menuClick")
    private String menuclick;

    @ApiModelProperty(value = "权限 0超级管理员，1表示管理员，2表示普通用户，可以用逗号组合使用")
    @TableField("menuRight")
    private String menuright;

    @ApiModelProperty(value = "跳转的路径")
    @TableField("menuComponent")
    private String menucomponent;

    @ApiModelProperty(value = "菜单图标")
    @TableField("menuIcon")
    private String menuicon;


}
