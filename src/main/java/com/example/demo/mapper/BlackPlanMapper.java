package com.example.demo.mapper;

import com.example.demo.entity.blackplan;

import java.util.List;
import java.util.Map;

public interface BlackPlanMapper {

    /**
     * 获取打印模板
     * @return
     */
    public List<Map<String,Object>> selectAll();

    /**
     * 新增模板
     * @param blackplan
     * @return
     */
    public int insertPlan(blackplan blackplan);

    /**
     * 更新模板
     * @param blackplan
     * @return
     */
    public int updatePlan(blackplan blackplan);

    /**
     * 删除模板
     * @param id
     * @return
     */
    public int deletePlanById(String id);

    /**
     *根据id查询某个方案
     * @param id
     * @return
     */
    public blackplan selectOneById(String id);
}
