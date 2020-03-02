package com.changgou.util;

import com.changgou.file.FastDFSFile;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件上传
     * @param fastDFSFile  文件上传的信息封装
     */
    public static String[] upload(FastDFSFile fastDFSFile) {

        //附加参数
        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] = new NameValuePair("author",fastDFSFile.getAuthor());

        try{
            //获取TrackerServer
            TrackerServer trackerServer = getTrackerServer();

            //获取StorageClient
            StorageClient storageClient = getStorageClient(trackerServer);

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
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    /**
     * 获取文件信息
     *
     * @param groupName      文件组名 group1
     * @param remoteFileName 文件的储存路径名字  /M00/00/00/rBKntV5cagyANxooAAEYZf_b9Hs269.jpg
     * @return
     */
    public static FileInfo getFile(String groupName, String remoteFileName) {
        try{
            //获取TrackerServer
            TrackerServer trackerServer = getTrackerServer();

            //获取StorageClient
            StorageClient storageClient = getStorageClient(trackerServer);

            return storageClient.get_file_info(groupName,remoteFileName);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 文件下载
     * @param groupName      文件组名 group1
     * @param remoteFileName 文件的储存路径名字  /M00/00/00/rBKntV5cagyANxooAAEYZf_b9Hs269.jpg
     * @return
     */
    public static InputStream downloadFile(String groupName, String remoteFileName) {
        try{
            //获取TrackerServer
            TrackerServer trackerServer = getTrackerServer();

            //获取StorageClient
            StorageClient storageClient = getStorageClient(trackerServer);

            //下载
            byte[] bytes = storageClient.download_file(groupName, remoteFileName);
            return new ByteArrayInputStream(bytes);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 文件删除
     * @param groupName      文件组名 group1
     * @param remoteFileName 文件的储存路径名字  /M00/00/00/rBKntV5cagyANxooAAEYZf_b9Hs269.jpg
     * @return
     */
    public static void deleteFile(String groupName, String remoteFileName){
        try{
            //获取TrackerServer
            TrackerServer trackerServer = getTrackerServer();

            //获取StorageClient
            StorageClient storageClient = getStorageClient(trackerServer);

            //删除
            storageClient.delete_file(groupName,remoteFileName);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取storage信息
     * @return
     */
    public static StorageServer getStorage() {
        try{
            //创建一个访问tracker的TrackerClient客户对象
            TrackerClient trackerClient = new TrackerClient();

            //获取TrackerServer
            TrackerServer trackerServer = getTrackerServer();

            return trackerClient.getStoreStorage(trackerServer);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取storage的IP和端口
     * @throws Exception
     * @return
     */
    public static ServerInfo[] getStorageInfo(String groupName, String remoteFileName){
        try{
            //创建一个访问tracker的TrackerClient客户对象
            TrackerClient trackerClient = new TrackerClient();

            //获取TrackerServer
            TrackerServer trackerServer = getTrackerServer();

            //获取storage的IP和端口
            return trackerClient.getFetchStorages(trackerServer, groupName,  remoteFileName);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取Tracker 信息
     *
     * @return
     * @throws Exception
     */
    public static String getTrackerInfo()  {
        try{
            //获取TrackerServer
            TrackerServer trackerServer = getTrackerServer();

            //Tracker的IP http端口
            String ip = trackerServer.getInetSocketAddress().getHostString();
            int port = ClientGlobal.getG_tracker_http_port();
            String url = "http://" + ip + ":" + port;
            return url;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取TrackerServer
     * @return
     */
    public static TrackerServer getTrackerServer() {
        try{
            //创建一个访问tracker的TrackerClient客户对象
            TrackerClient trackerClient = new TrackerClient();

            //通过TrackerClient访问TrckerService服务 ， 获取连接信息
            return trackerClient.getConnection();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取StorageClient
     * @param trackerServer
     * @return
     * @throws Exception
     */
    public static StorageClient getStorageClient(TrackerServer trackerServer){
        //通过TrackerService连接信息获取Storage连接信息， 创建StorageClent对象储存Storage连接信息
        return  new StorageClient(trackerServer,null);
    }


    /**
     * 测试
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
/*       获取文件信息
        FileInfo fileInfo = getFile("group1", "M00/00/00/rBKntV5cagyANxooAAEYZf_b9Hs269.jpg");
        System.out.println(fileInfo.getSourceIpAddr());
        System.out.println(fileInfo.getFileSize());*/

 /*       文件下载
        InputStream inputStream = downloadFile("group1", "M00/00/00/rBKntV5cagyANxooAAEYZf_b9Hs269.jpg");
        将文件写入本地磁盘
        FileOutputStream os = new FileOutputStream("D:/1.jpg");

        定义个缓冲区
        byte[] buffer = new byte[1024];
        while (inputStream.read(buffer)!= -1){
            os.write(buffer);
        }

        os.flush();
        os.close();
        inputStream.close();*/

        //文件删除
        deleteFile("group1", "M00/00/00/rBKntV5cgOiAIWN8AAEYZf_b9Hs948.jpg");

      //获取storage信息
       /* StorageServer storage = getStorage();
        System.out.println(storage.getStorePathIndex());
        System.out.println(storage.getInetSocketAddress());*/

       //获取storage的IP和端口
        /*ServerInfo[] group1s = getStorageInfo("group1", "/M00/00/00/rBKntV5cgOiAIWN8AAEYZf_b9Hs948.jpg");
        for (ServerInfo group1 : group1s) {
            System.out.println(group1.getIpAddr());
            System.out.println(group1.getPort());
        }*/

        //获取Tracker 信息
      /*  String trackerInfo = getTrackerInfo();
        System.out.println(trackerInfo);*/
    }

}
