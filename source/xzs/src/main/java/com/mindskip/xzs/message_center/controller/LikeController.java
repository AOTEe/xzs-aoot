package com.mindskip.xzs.message_center.controller;

import com.mindskip.xzs.base.BaseApiController;
import com.mindskip.xzs.domain.User;
import com.mindskip.xzs.message_center.service.LikeService;
import com.mindskip.xzs.utility.Response;
import com.mindskip.xzs.utility.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 *
 */
@RequestMapping("/api/student/like")
@Controller
public class LikeController extends BaseApiController {

    @Autowired
    LikeService likeService;
    @RequestMapping("/like")
    @ResponseBody
    public Map<String, Object> like(@RequestParam String topicId, @RequestParam String userId, @RequestParam String topicType){
        likeService.like(topicId,userId,topicType);
         return ResponseUtil.success();
    }

    @ResponseBody
    @RequestMapping("/cancelLike")
    public Map<String, Object> cancelLike(@RequestParam String topicId, @RequestParam String userId, @RequestParam String topicType){
        likeService.cancelLike(topicId,userId,topicType);
        return ResponseUtil.success();
    }
    @ResponseBody
    @RequestMapping("/cancelDislike")
    public Map<String, Object> cancelDisike(@RequestParam String topicId, @RequestParam String userId, @RequestParam String topicType){
        likeService.cancelDislike(topicId,userId,topicType);
        return ResponseUtil.success();
    }

    @ResponseBody
    @RequestMapping("/dislike")
    public Map<String, Object> dislike(@RequestParam String topicId, @RequestParam String userId, @RequestParam String topicType){
        likeService.dislike(topicId,userId,topicType);
        return ResponseUtil.success();
    }
    @ResponseBody
    @RequestMapping("/getLikeNum")
    public void getLikeNum(){}

    /**
     * 评论comment的点赞等行为
     * @return
     */
    @ResponseBody
    @RequestMapping("/commentAction")
    public Response commentAction(String commentId,int action){
        User currentUser = getCurrentUser();
        likeService.commentAction(commentId,currentUser.getId(),action);
        return  ResponseUtil.responseSuccess();
    }
}
