package com.mindskip.xzs.video_room.util;

import com.mindskip.xzs.video_room.controller.VideoController;
import org.bytedeco.javacv.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;

public class VideoUtil {

    public static final Logger log = LoggerFactory.getLogger(VideoUtil.class);

    /**
     * 获取视频第一帧
     * @param videoPath 视频地址
     * @param imgPath 生成图片的名字（包含全路径）
     * @throws Exception
     */
    public static void getVideoPicture(String videoPath,String imgPath) {
        File imgFile = new File(imgPath);
        //判断保存的文件的文件夹是否存在，不存在创建。
        if (!imgFile.getParentFile().exists()) {
            log.info("保存文件的文件夹不存在，创建。");
            imgFile.getParentFile().mkdirs();
        }
        File videoFile = new File(videoPath);
        if (videoFile.exists()) {
            log.info("视频存在：{}",videoFile);
            //实例化“截取视频首帧”对象
            FFmpegFrameGrabber ff = new FFmpegFrameGrabber(videoFile);
            try {
                ff.start();
                int ftp = ff.getLengthInFrames();
                int flag=0;
                Frame frame = null;
                while (flag <= ftp) {
                    //获取帧
                    frame = ff.grabImage();
                    //过滤前1帧，避免出现全黑图片
                    if ((flag>1)&&(frame != null)) {
                        break;
                    }
                    flag++;
                }
                ImageIO.write(frameToBufferedImage(frame), "jpg", imgFile);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    ff.close();
                    ff.stop();
                } catch (FrameGrabber.Exception e) {
                    e.printStackTrace();
                }
            }
        }else {
            log.info("视频不存在：{}",videoFile);
        }
    }

    /**
     * 帧转为流
     * @param frame
     * @return
     */
    private static RenderedImage frameToBufferedImage(Frame frame) {
        //创建BufferedImage对象
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage bufferedImage = converter.getBufferedImage(frame);
        return bufferedImage;
    }

    public static void main(String[] args) {
        try {
            String videoPath = "C:\\Users\\陈圣兹\\Videos\\Red Dead Redemption 2\\Red Dead Redemption.mp4";
            String imgPath = "C:\\Users\\陈圣兹\\Videos\\Red Dead Redemption 2\\图片123.jpg";
            getVideoPicture(videoPath,imgPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
