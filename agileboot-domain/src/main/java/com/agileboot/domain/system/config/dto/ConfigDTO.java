package com.agileboot.domain.system.config.dto;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONUtil;
import com.agileboot.orm.system.entity.SysConfigEntity;
import com.agileboot.orm.common.enums.YesOrNoEnum;
import com.agileboot.orm.common.util.BasicEnumUtil;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class ConfigDTO {

    public ConfigDTO(SysConfigEntity entity) {
        if (entity != null) {
            configId = entity.getConfigId() + "";
            configName = entity.getConfigName();
            configKey = entity.getConfigKey();
            configValue = entity.getConfigValue();
            configOptions =
                JSONUtil.isTypeJSONArray(entity.getConfigOptions()) ? JSONUtil.toList(entity.getConfigOptions(),
                    String.class) : ListUtil.empty();
            isAllowChange = Convert.toInt(entity.getIsAllowChange()) + "";
            isAllowChangeStr = BasicEnumUtil.getDescriptionByBool(YesOrNoEnum.class, entity.getIsAllowChange());
            remark = entity.getRemark();
            createTime = entity.getCreateTime();
        }
    }

    private String configId;
    private String configName;
    private String configKey;
    private String configValue;
    private List<String> configOptions;
    private String isAllowChange;
    private String isAllowChangeStr;
    private String remark;
    private Date createTime;

}
