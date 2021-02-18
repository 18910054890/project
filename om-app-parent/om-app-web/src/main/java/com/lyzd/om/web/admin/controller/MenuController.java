package com.lyzd.om.web.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyzd.om.shared.dto.admin.MenuDto;
import com.lyzd.om.shared.entity.admin.MyMenu;
import com.lyzd.om.shared.service.admin.MenuService;
import com.lyzd.om.shared.service.log.aop.MyLogA;
import com.lyzd.om.spring.common.dto.Result;
import com.lyzd.om.spring.common.dto.ResultCode;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Thinker
 *
 */
@Controller
@RequestMapping("/api/menu")
@Api(tags = "系统：菜单管理")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("index")
    @PreAuthorize("hasAnyAuthority('menu:list')")
    @ApiOperation(value = "返回菜单页面")
    public String index(){
        return "system/menu/menu";
    }


    @GetMapping
    @ResponseBody
    @ApiOperation(value = "菜单列表")
    @PreAuthorize("hasAnyAuthority('menu:list')")
    @MyLogA("查询菜单")
    public Result getMenuAll(String queryName,Integer queryType){
    	System.out.println(queryName);
        return Result.ok().data(menuService.getMenuAll(queryName,queryType)).code(ResultCode.TABLE_SUCCESS);
    }

    @GetMapping("/build")
    @ResponseBody
    @ApiOperation(value = "绘制菜单树")
    @PreAuthorize("hasAnyAuthority('menu:add','menu:edit')")
    @MyLogA("绘制菜单树")
    public Result buildMenuAll(){
        List<MenuDto> menuAll =menuService.buildMenuAll();
        return Result.ok().data(menuAll);
    }

    @GetMapping("/ebuild/{roleId}")
    @ResponseBody
    @ApiOperation(value = "通过id绘制菜单树")
    @PreAuthorize("hasAnyAuthority('role:add','role:edit')")
    @MyLogA("通过id绘制菜单树")
    public Result buildMenuAllByRoleId(@PathVariable Integer roleId){
        List<MenuDto> menuAll =menuService.buildMenuAllByRoleId(roleId);
        return Result.ok().data(menuAll);
    }

    @GetMapping(value = "/edit")
    @ApiOperation(value = "修改菜单页面")
    @PreAuthorize("hasAnyAuthority('menu:edit')")
    public String editPermission(Model model, MyMenu myMenu) {
        model.addAttribute("myMenu",menuService.getMenuById(myMenu.getMenuId()));
        return "system/menu/menu-edit";
    }

    @PutMapping
    @ResponseBody
    @ApiOperation(value = "修改菜单")
    @PreAuthorize("hasAnyAuthority('menu:edit')")
    @MyLogA("修改菜单")
    public Result updateMenu(@RequestBody MyMenu menu) {
        return menuService.updateMenu(menu);
    }


    @GetMapping(value = "/add")
    @ApiOperation(value = "添加菜单页面")
    @PreAuthorize("hasAnyAuthority('menu:add')")
    public String addMenu(Model model) {
        model.addAttribute("myMenu",new MyMenu());
        return "system/menu/menu-add";
    }

    @PostMapping
    @ResponseBody
    @ApiOperation(value = "添加菜单")
    @PreAuthorize("hasAnyAuthority('menu:add')")
    @MyLogA("添加菜单")
    public Result<MyMenu> savePermission(@RequestBody MyMenu myMenu) {
        return menuService.save(myMenu);
    }


    /**
     * @param menuId
     * @return
     */
    @DeleteMapping
    @ResponseBody
    @ApiOperation(value = "删除菜单")
    @PreAuthorize("hasAnyAuthority('menu:del')")
    @MyLogA("删除菜单")
    public Result deleteMenu(Integer menuId) {
        return menuService.delete(menuId);
    }

}
