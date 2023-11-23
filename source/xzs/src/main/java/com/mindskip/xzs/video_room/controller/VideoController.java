package com.mindskip.xzs.video_room.controller;


import com.mindskip.xzs.base.BaseApiController;
import com.mindskip.xzs.domain.User;
import com.mindskip.xzs.utility.Response;
import com.mindskip.xzs.utility.ResponseUtil;
import com.mindskip.xzs.video_room.bean.Video;
import com.mindskip.xzs.video_room.bean.VideoCategory;
import com.mindskip.xzs.video_room.bean.VideoEditVO;
import com.mindskip.xzs.video_room.service.VideoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Base64;
import java.util.Map;

@Controller
public class VideoController  extends BaseApiController {


    @Autowired
    VideoService videoService;

    @RequestMapping("/api/student/videoBase")
    @ResponseBody
    public Map<String, Object> getVideoBase(HttpServletRequest request, HttpServletResponse response) {



        String base64 = null;
        File file = new File("C:\\Users\\陈圣兹\\Videos\\Red Dead Redemption 2\\zi~.mp4");

        response.addHeader("Content-Disposition", "attachment;fileName=" + file.getName());

        FileInputStream fis =  null;
        BufferedInputStream bis = null;

        try {
            fis = new FileInputStream(file);
            byte[] bytes=new byte[(int)file.length()];
            fis.read(bytes);
            base64 = Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }
        return ResponseUtil.success(base64);

    }


//    @RequestMapping("/api/student/video")
//    @ResponseBody
//    public Map<String, Object> getVideo(HttpServletRequest request, HttpServletResponse response) {
//
//
//
//        String base64 = null;
//        File file = new File("C:\\Users\\陈圣兹\\Videos\\Red Dead Redemption 2\\zi~.mp4");
//
//        response.addHeader("Content-Disposition", "attachment;fileName=" + file.getName());
//
//        FileInputStream fis =  null;
//        BufferedInputStream bis = null;
//
//        try {
//            fis = new FileInputStream(file);
//            byte[] bytes=new byte[(int)file.length()];
//            fis.read(bytes);
//            base64 = Base64.getEncoder().encodeToString(bytes);
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }finally {
//            if(fis!=null){
//                try {
//                    fis.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//
//                }
//            }
//        }
//        return ResponseUtil.success(base64);
//
//    }


    @RequestMapping("/api/student/video/{id}")
    @ResponseBody
    public Map<String, Object> videoInfo(@PathVariable String id) {

        return ResponseUtil.success( videoService.getVideoVO(id));

    }

    /**
     * 获取该视频是否点赞、点踩、收藏等信息
     * @return
     */
    @RequestMapping("/api/student/videoRelation/{videoId}/{userId}")
    @ResponseBody
    public Map<String,Object> videoRelation(@PathVariable String videoId,@PathVariable String userId){


        return ResponseUtil.success(videoService.videoRelation(videoId,userId));
    }

    @RequestMapping("/api/admin/video/saveCategory")
    @ResponseBody
    public Response saveCategory(@RequestBody VideoCategory category){

        return ResponseUtil.responseSuccess(videoService.saveCategory(category));
    }

    @RequestMapping("/api/admin/video/categoryList")
    @ResponseBody
    public Response categoryList(){

        return ResponseUtil.responseSuccess(videoService.categoryList());
    }

    @ResponseBody
    @RequestMapping("/api/admin/video/upload")
    public Response upload(MultipartFile file){
        return ResponseUtil.responseSuccess(videoService.upload(file));
    }

    @ResponseBody
    @RequestMapping("/api/admin/video/save")
    public Response save(VideoEditVO video){
        User currentUser = getCurrentUser();
        videoService.save(video,currentUser);
        return ResponseUtil.responseSuccess();
    }

    @ResponseBody
    @RequestMapping("/api/admin/video/coverUpload")
    public Response coverUpload(MultipartFile file){
        return ResponseUtil.responseSuccess(videoService.coverUpload(file));
    }
}

