package com.ego.manager.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ego.commons.utils.FtpUtil;
import com.ego.manager.service.PicService;

@Service
public class PicServiceImpl implements PicService {

	@Value("${ftpclient.host}")
	private String host;

	@Value("${ftpclient.port}")
	private int port;

	@Value("${ftpclient.username}")
	private String username;

	@Value("${ftpclient.password}")
	private String password;

	@Value("${ftpclient.basePath}")
	private String basePath;

	@Value("${ftpclient.filePath}")
	private String filePath;

	@Override
	public Map<String, Object> upload(MultipartFile file) {
		Map<String, Object> map = new HashMap<>();
		String oldName = file.getOriginalFilename();
		String fileName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
		InputStream input = null;
		boolean result = false;
		// 此处没有应用声明式事务，可以进行try-catch
		try {
			input = file.getInputStream();
			result = FtpUtil.uploadFile(host, port, username, password, basePath, filePath, fileName, input);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (result) {
				map.put("error", 0);
				map.put("url", "http://" + host + ":80" + filePath + fileName);
			} else {
				map.put("error", 1);
				map.put("msg", "图片上传失败！");
			}
		}
		return map;
	}

}
