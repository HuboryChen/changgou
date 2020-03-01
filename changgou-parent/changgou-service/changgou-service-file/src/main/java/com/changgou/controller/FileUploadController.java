package com.changgou.controller;

import com.changgou.file.FastDFSFile;
import com.changgou.util.FastDFSUtil;
import entity.Result;
import entity.StatusCode;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author DL_Wu
 * @Date 2020/3/1 15:20
 * @Version 1.0
 */
@RestController
@RequestMapping("/upload")
@CrossOrigin   //跨域
public class FileUploadController {

    /**
     * 文件上传
     * @return
     */
    @PostMapping
    public Result upload(@RequestParam(value = "file")MultipartFile file) throws Exception{
        //封装文件信息
        FastDFSFile fastDFSFile = new FastDFSFile(
                file.getOriginalFilename(),  //文件名字， 1.jpg
                file.getBytes(),              //文件字节数组
                StringUtils.getFilenameExtension(file.getOriginalFilename())    //获取文件扩展名
        );
        //调用FastDFS将文件上传
        String[] uploads = FastDFSUtil.upload(fastDFSFile);

        //拼接访问地址 url:http://47.107.160.219:8080/
       String url = "http://47.107.160.219:8080/"+uploads[0]+"/"+uploads[1];

//        String url = "http://192.168.0.104:8080/"+uploads[0]+"/"+uploads[1];
        return new Result(true, StatusCode.OK,"文件上传成功",url);
    }
}
