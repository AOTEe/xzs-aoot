package com.mindskip.xzs.controller.admin;

import com.mindskip.xzs.base.BaseApiController;
import com.mindskip.xzs.domain.Tag;
import com.mindskip.xzs.service.impl.TagServiceImpl;
import com.mindskip.xzs.utility.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController("AdminTagController")
@RequestMapping(value = "/api/admin/education")
public class TagController extends BaseApiController {

    @Autowired
    TagServiceImpl tagService;

    @RequestMapping("/tag/edit")
    public void editOrg(){

    }

    @RequestMapping(value = "/tag/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> list(@RequestBody(required = false) Tag tag){
        Map<String, Object> data = tagService.list(tag);
        return ResponseUtil.success(data);
    }


    @RequestMapping(value = "/tag/select/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> select(@PathVariable String id) {
        Tag tag = tagService.selectById(id);
        return ResponseUtil.success(tag);
    }

    @RequestMapping(value = "/tag/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> delete(@PathVariable String id) {
        tagService.deleteById(id);
        return ResponseUtil.success();
    }

    @RequestMapping(value = "/tag/update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> edit(@RequestBody Tag tag) {
        tagService.updateById(tag);
        return ResponseUtil.success();
    }

}
