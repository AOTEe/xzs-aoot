package com.mindskip.xzs.video_room.controller;


import com.mindskip.xzs.utility.ResponseUtil;
import com.mindskip.xzs.video_room.service.VideoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Base64;
import java.util.Map;

@Controller
public class VideoController {


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
}

