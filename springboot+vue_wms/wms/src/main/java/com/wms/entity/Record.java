package com.wms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @since 2023-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Record对象", description="")
public class Record implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "货品id")
    private Integer goods;

    @ApiModelProperty(value = "取货人/补货人")
    @TableField("userId")
    private Integer userid;

    @ApiModelProperty(value = "操作人id")
    @TableField("admin_id")
    private Integer adminId;

    @ApiModelProperty(value = "数量")
    private Integer count;

    @ApiModelProperty(value = "操作时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    private LocalDateTime createtime;
    private Date createtime;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "物品名")
    @TableField(exist = false)
    private String goodsname;

    @ApiModelProperty(value = "仓库名")
    @TableField(exist = false)
    private String storagename;

    @ApiModelProperty(value = "分类名")
    @TableField(exist = false)
    private String goodstypename;

    @ApiModelProperty(value = "用户名")
    @TableField(exist = false)
    private String username;

    @ApiModelProperty(value = "操作人名")
    @TableField(exist = false)
    private String adminname;

    @ApiModelProperty(value = "判断是入库还是出库")
    @TableField(exist = false)
    private String action;

}
