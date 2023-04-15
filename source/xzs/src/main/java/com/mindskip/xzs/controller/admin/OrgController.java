package com.mindskip.xzs.controller.admin;

import com.mindskip.xzs.base.RestResponse;
import com.mindskip.xzs.bean.Org;
import com.mindskip.xzs.domain.User;
import com.mindskip.xzs.service.impl.OrgServiceImpl;
import com.mindskip.xzs.utility.ResponseUtil;
import com.mindskip.xzs.viewmodel.admin.user.UserResponseVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RequestMapping("api/admin/org")
@Controller
public class OrgController {


    @Autowired
    OrgServiceImpl orgService;

    @RequestMapping("/edit")
    public void editOrg(){

    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> list(@RequestBody(required = false) Org org){

        return orgService.list(org);
    }


    @RequestMapping(value = "/select/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> select(@PathVariable String id) {
        Org org = orgService.selectById(id);
        return ResponseUtil.success(org);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> delete(@PathVariable String id) {
        orgService.deleteById(id);
        return ResponseUtil.success();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> edit(@RequestBody Org org) {
        orgService.updateById(org);
        return ResponseUtil.success();
    }

    @RequestMapping(value = "/getAllParents", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getAllParents() {
        List orgParents = orgService.getAllParents();
        return ResponseUtil.success(orgParents);
    }


    @RequestMapping(value = "/put", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> put(@RequestBody Org org) {
        orgService.insert(org);
        return ResponseUtil.success();
    }

    @RequestMapping(value = "/orgTree", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> put() {

        List<Map> orgTree = orgService.getOrgTree();
        return ResponseUtil.success(orgTree);
    }

    @RequestMapping(value = "/childrenList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> childrenList(@RequestBody Org org) {

        return ResponseUtil.success(orgService.getOrgChildren(org));
    }



}
