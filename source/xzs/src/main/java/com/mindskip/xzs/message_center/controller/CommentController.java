package com.mindskip.xzs.message_center.controller;


import com.mindskip.xzs.base.BaseApiController;
import com.mindskip.xzs.domain.User;
import com.mindskip.xzs.message_center.service.CommentService;
import com.mindskip.xzs.utility.Response;
import com.mindskip.xzs.utility.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/student/comment")
public class CommentController extends BaseApiController {

    @Autowired
    CommentService commentService;

    @ResponseBody
    @RequestMapping("/getVideoComments/{videoId}")
    public Response getVideoComments(@PathVariable  String videoId ){
        User currentUser = getCurrentUser();
        return ResponseUtil.responseSuccess(commentService.getCommentsByTopicId(videoId,currentUser.getId()));

    }

    /**
     *
     * @param topicId
     * @param topicType
     * @param content
     * @param userId
     * @param parent
     * @param atName
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public Response add(@RequestParam String topicId,
                        @RequestParam String topicType,
                        @RequestParam String content,
                        @RequestParam String userId,
                        @RequestParam(required = false)  String parent,
                        @RequestParam(required = false) String atName){



        System.out.println(atName);
        return ResponseUtil.responseSuccess(commentService.add(topicId,topicType,content,userId,atName,parent));

    }

    /**
     *
     * @param rootCommentId
     * @param index
     * @return
     */
    @ResponseBody
    @RequestMapping("/more")
    public Response more(String rootCommentId, int index){
        return ResponseUtil.responseSuccess(commentService.moreComments(rootCommentId,index));
    }


    

}
