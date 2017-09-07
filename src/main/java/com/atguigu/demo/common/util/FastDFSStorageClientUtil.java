package com.atguigu.demo.common.util;

import java.io.IOException;


import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

import com.atguigu.demo.common.exception.TrackerConfNotFoundException;
import com.atguigu.demo.common.exception.UploadFileFailedException;

/**
 * @author panpala
 * @date 2017年9月6日
 * 封装向fastdfs上传文件操作
 */
public class FastDFSStorageClientUtil {

	private TrackerClient trackerClient = null;
	private TrackerServer trackerServer = null;
	private StorageClient storageClient = null;
	private StorageServer storageServer = null;
	
	//tanckerconfPath必须以classpath开始
	public FastDFSStorageClientUtil(String trackerconfPath) {
		//判断trackerconfPath是否为空
		if (trackerconfPath == null || !trackerconfPath.startsWith("classpath:")) {
			throw new TrackerConfNotFoundException("找不到tranck.conf文件,请文件检查路径是否正确");
		}
		
		/*
		 * 获取当前工程classes运行的物理路径，也就是  当前类路径   的在部署路径下的(带盘符)物理路径
		 *“/”表示当前路径的虚拟路径
		 * */
		String path = this.getClass().getResource("/").getPath();
		//字符串具有不变性，此时的trackerconfPath已经指向一个新的字符串对象了
		trackerconfPath = trackerconfPath.replaceAll("classpath:", path);
		System.out.println(trackerconfPath);
		//初始化storageclient对象，用于上传文件
		try {
			ClientGlobal.init(trackerconfPath);
			trackerClient = new TrackerClient();
			trackerServer = trackerClient.getConnection();
			storageClient = new StorageClient(trackerServer, storageServer);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MyException e) {
			e.printStackTrace();
		}
		
	}
	
	//获取文件扩展名 
	public String getExtendName(String fileName) {
		if (fileName != null && fileName.contains(".")) {
			//圆点在是运算符，用圆点时需要转义，\在字符传中也需要转义
			String[] strings = fileName.split("\\.");
			return strings[strings.length - 1];
		}
		return fileName;
	}
	
	//上传文件到fastdfs服务器
	public String[] upload(byte[] filebytes,String fileName) {
		String[] result = null;
		try {
			result = storageClient.upload_file(filebytes, getExtendName(fileName), null);
		} catch (IOException | MyException e) {
			e.printStackTrace();
			throw new UploadFileFailedException("文件上传失败");
		}
		return result;
	}
}
