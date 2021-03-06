package com.college.controller;

import com.college.contants.AppCode;
import com.college.contants.Path;
import com.college.entity.RolePermission;
import com.college.model.Resp;
import com.college.service.RolePermissionService;
import com.college.utils.DateTimeUtil;
import com.github.pagehelper.Page;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * @author milo
 * @Title:
 * @Description
 */
@RestController
public class RolePermissionController extends BaseController {

    private static Logger logger = LoggerFactory
            .getLogger(RolePermissionController.class);

    @Autowired
    private RolePermissionService rolePermissionService;

    /**
     * get list
     *
     * @param pageNum
     * @param pageSize
     * @param id
     * @param roleId
     * @param permissionId
     * @param status       0: 删除 1:正常
     * @param createTime   创建时间
     * @param updateTime   更新时间
     * @return
     */
    @RequestMapping(value = Path.ROLE_PERMISSION_LIST)
    @ResponseBody
    public Map<String, Object> getList(@RequestParam(value = "pagenum", defaultValue = "1") int pageNum,
                                       @RequestParam(value = "pagesize", defaultValue = "10") int pageSize,
                                       @RequestParam(value = "id", required = false) Integer id,
                                       @RequestParam(value = "roleId", required = false) Integer roleId,
                                       @RequestParam(value = "permissionId", required = false) Integer permissionId,
                                       @RequestParam(value = "status", required = false) boolean status,
                                       @RequestParam(value = "createTime", required = false) Integer createTime,
                                       @RequestParam(value = "updateTime", required = false) Integer updateTime
    ) {
        Map<String, Object> params = Maps.newHashMap();
        Page<RolePermission> page = rolePermissionService.searchPageList(pageNum, pageSize, params);
        Map<String, Object> resultMap = Maps.newHashMap();
        logger.info(" RolePermissionController -->  pageResult :{}", page.getResult());
        resultMap.put("result", page.getResult());
        resultMap.put("pages", page.getPages());
        resultMap.put("startrow", page.getStartRow());
        resultMap.put("endrow", page.getEndRow());
        resultMap.put("pagesize", page.getPageSize());
        resultMap.put("pagenum", page.getPageNum());
        resultMap.put("total", page.getTotal());
        return resultMap;
    }

    /**
     * insert
     *
     * @param roleId
     * @param permissionId
     * @param status       0: 删除 1:正常
     * @param createTime   创建时间
     * @return
     */
    @RequestMapping(value = Path.ROLE_PERMISSION_ADD)
    @ResponseBody
    public Resp add(
            @RequestParam(value = "roleId", required = false) Integer roleId,
            @RequestParam(value = "permissionId", required = false) Integer permissionId,
            @RequestParam(value = "status", required = false) boolean status,
            @RequestParam(value = "createTime", required = false) String createTime) {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(roleId);
        rolePermission.setPermissionId(permissionId);
        rolePermission.setStatus(status);
        rolePermission.setCreateTime(DateTimeUtil.parseDateTime(createTime,"yyyy-MM-dd HH:mm:ss"));
        Integer id = rolePermissionService.insert(rolePermission);
        return null != id ? Resp.success(id) : Resp.error(AppCode._10003);
    }

    /**
     * update
     *
     * @param id
     * @param roleId
     * @param permissionId
     * @param status       0: 删除 1:正常
     * @param createTime   创建时间
     * @return
     */
    @RequestMapping(value = Path.ROLE_PERMISSION_UPDATE)
    @ResponseBody
    public Resp update(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "roleId", required = false) Integer roleId,
            @RequestParam(value = "permissionId", required = false) Integer permissionId,
            @RequestParam(value = "status", required = false) boolean status,
            @RequestParam(value = "createTime", required = false) String createTime
    ) {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setId(id);
        rolePermission.setRoleId(roleId);
        rolePermission.setPermissionId(permissionId);
        rolePermission.setStatus(status);
        rolePermission.setCreateTime(DateTimeUtil.parseDateTime(createTime,"yyyy-MM-dd HH:mm:ss"));
        rolePermissionService.update(rolePermission);
        return Resp.success();
    }

}
