package com.agileboot.domain.system.menu.query;

import cn.hutool.core.util.StrUtil;
import com.agileboot.orm.system.entity.SysMenuEntity;
import com.agileboot.orm.common.query.AbstractQuery;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.Arrays;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class MenuQuery extends AbstractQuery {

    private String menuName;
    private Boolean isVisible;
    private Integer status;


    @Override
    public QueryWrapper<SysMenuEntity> toQueryWrapper() {

        QueryWrapper<SysMenuEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StrUtil.isNotEmpty(menuName), columnName("menu_name"), menuName)
            .eq(isVisible != null, "is_visible", isVisible)
            .eq(status != null, "status", status);

        queryWrapper.orderBy(true, true, Arrays.asList("parent_id", "order_num"));
        return queryWrapper;
    }
}
