package com.changgou.util;

import com.changgou.file.FastDFSFile;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @Author DL_Wu
 * @Date 2020/3/1 14:50
 * @Version 1.0
 *
 *  实现FastDFS文件管理
 *      文件下载
 *      文件删除
 *      文件上传
 *      文件信息获取
 *      Storage信息获取
 *      Tracker信息获取
 */
public class FastDFSUtil {

    //加载tracker链接信息
    static {
        try {
            //获取文件路径
            String filename = new ClassPathResource("fdfs_client.conf").getPath();
            ClientGlobal.init(filename);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件上传
     * @param fastDFSFile  文件上传的信息封装
     */
    public static String[] upload(FastDFSFile fastDFSFile) throws Exception{

        //附加参数
        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] = new NameValuePair("author",fastDFSFile.getAuthor());


        //创建一个访问tracker的TrackerClient客户对象
        TrackerClient trackerClient = new TrackerClient();

        //通过TrackerClient访问TrckerService服务 ， 获取连接信息
        TrackerServer trackerServer = trackerClient.getConnection();

        //通过TrackerService连接信息获取Storage连接信息， 创建StorageClent对象储存Storage连接信息
        StorageClient storageClient = new StorageClient(trackerServer,null);

        /**
         * //通过StorageClient访问Storage。实现文件上传，并且获取文件上传后的储存信息
         *      1.文件上传字节数组
         *      2。文件扩展名
         *      3.附加参数
         *
         *      uploads[]
         *          uploads[0]: 文件上传所储存的storage 的组名字
         *          uploads[1]:  文件上传储存到的Storage文件文字
         */
        String[] uploads = storageClient.upload_file(fastDFSFile.getContent(), fastDFSFile.getExt(), meta_list);
        return uploads;
    }
}
