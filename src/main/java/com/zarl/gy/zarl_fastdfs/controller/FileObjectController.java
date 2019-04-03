package com.zarl.gy.zarl_fastdfs.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zarl.gy.zarl_fastdfs.client.FastDFSClient;
import com.zarl.gy.zarl_fastdfs.constant.ErrorCode;
import com.zarl.gy.zarl_fastdfs.entity.response.FileResponseData;
import com.zarl.gy.zarl_fastdfs.exception.FastDFSException;
import com.zarl.gy.zarl_fastdfs.utils.FileCheck;
import com.zarl.gy.zarl_fastdfs.utils.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * 
 * @ClassName: com.zarl.gy.zarl_fastdfs.controller.FileObjectController
 * @Description:TODO 接口控制器
 * @author: 高炎
 * @Email 59498838@qq.com OR yan.gao@zarltech.com
 * @date: 2019年4月1日 上午11:48:44
 * 
 * @Copyright: 2019 www.zarltech.com Inc. All rights reserved.
 */
@Api(value = "FileObjectController 接口控制器", description = "文件上传下载管理")
@Controller
@RequestMapping("/fastdfs")
public class FileObjectController {
	
	/**
	 * 获取日志记录器
     */
    //private static Logger logger = LoggerFactory.getLogger(FileObjectController.class);

	@Autowired
	private FastDFSClient fastDFSClient;

	/**
	 * 文件服务器地址
	 */
	@Value("${file_server_addr}")
	private String fileServerAddr;

	/**
	 * FastDFS秘钥
	 */
	@Value("${fastdfs.http_secret_key}")
	private String fastDFSHttpSecretKey;

	@ApiOperation(value = "跳转到上传页面", notes = "跳转到上传页面")
	@GetMapping("/html/upload")
	public String index() {
		return "upload";
	}

	/**
	 * 上传文件通用，只上传文件到服务器，不会保存记录到数据库
	 *
	 * @param file
	 * @param request
	 * @return 返回文件路径等信息
	 */

	@ApiOperation(value = "上传文件通用，只上传文件到服务器", notes = "上传文件通用，只上传文件到服务器")
	@ResponseBody
	@RequestMapping(value = "/upload/file", method = RequestMethod.POST)
	public FileResponseData uploadFile(
			@ApiParam(value = "文件路径") @RequestParam(value = "file", defaultValue = "", required = true) MultipartFile file,
			HttpServletRequest request) {
		//logger.info("start  ....");
		return uploadSample(file, request);
	}

	/**
	 * 只能上传图片，只上传文件到服务器，不会保存记录到数据库. <br>
	 * 会检查文件格式是否正确，默认只能上传 ['png', 'gif', 'jpeg', 'jpg'] 几种类型.
	 *
	 * @param file
	 * @param request
	 * @return 返回文件路径等信息
	 */
	@ApiOperation(value = "只能上传图片，只上传文件到服务器", notes = "只能上传图片，只上传文件到服务器")
	@RequestMapping(value = "/upload/image", method = RequestMethod.POST)
	@ResponseBody
	public FileResponseData uploadImage(
			@ApiParam(value = "文件路径") @RequestParam(value = "file", defaultValue = "", required = true) MultipartFile file,
			HttpServletRequest request) {
		// 检查文件类型
		if (!FileCheck.checkImage(file.getOriginalFilename())) {
			FileResponseData responseData = new FileResponseData(false);
			responseData.setCode(ErrorCode.FILE_TYPE_ERROR_IMAGE.CODE);
			responseData.setMessage(ErrorCode.FILE_TYPE_ERROR_IMAGE.MESSAGE);
			return responseData;
		}

		return uploadSample(file, request);
	}

	/**
	 * 只能上传文档，只上传文件到服务器，不会保存记录到数据库. <br>
	 * 会检查文件格式是否正确，默认只能上传 ['pdf', 'ppt', 'xls', 'xlsx', 'pptx', 'doc', 'docx'] 几种类型.
	 *
	 * @param file
	 * @param request
	 * @return 返回文件路径等信息
	 */
	@ApiOperation(value = "只能上传文档，只上传文件到服务器", notes = "默认检查 ['pdf', 'ppt', 'xls', 'xlsx', 'pptx', 'doc', 'docx'] 几种类型")
	@RequestMapping(value = "/upload/doc", method = RequestMethod.POST)
	@ResponseBody
	public FileResponseData uploadDoc(
			@ApiParam(value = "文件路径") @RequestParam(value = "file", defaultValue = "", required = true) MultipartFile file,
			HttpServletRequest request) {
		// 检查文件类型
		if (!FileCheck.checkDoc(file.getOriginalFilename())) {
			FileResponseData responseData = new FileResponseData(false);
			responseData.setCode(ErrorCode.FILE_TYPE_ERROR_DOC.CODE);
			responseData.setMessage(ErrorCode.FILE_TYPE_ERROR_DOC.MESSAGE);
			return responseData;
		}

		return uploadSample(file, request);
	}

	/**
	 * 以附件形式下载文件
	 *
	 * @param filePath 文件地址
	 * @param response
	 */
	@ApiOperation(value = "以附件形式下载文件", notes = "以附件形式下载文件")
	@RequestMapping(value = "/download/file", method = RequestMethod.GET)
	public void downloadFile(
			@ApiParam(value = "文件路径") @RequestParam(value = "filePath", defaultValue = "", required = true) String filePath,
			HttpServletResponse response) throws FastDFSException {

		try {
			fastDFSClient.downloadFile(filePath, response);
		} catch (FastDFSException e) {
			e.printStackTrace();
			throw e;
		}

	}

	/**
	 * 获取图片 使用输出流输出字节码，可以使用< img>标签显示图片<br>
	 *
	 * @param filePath 图片地址
	 * @param response
	 */
	@ApiOperation(value = "获取图片 使用输出流输出字节码，可以使用<img>标签显示图片", notes = "获取图片 使用输出流输出字节码，可以使用<img>标签显示图片")
	@RequestMapping(value = "/get/image", method = RequestMethod.GET)
	public void downloadImage(
			@ApiParam(value = "文件路径") @RequestParam(value = "filePath", defaultValue = "", required = true) String filePath,
			HttpServletResponse response)
			throws FastDFSException {
		try {
			fastDFSClient.downloadFile(filePath, response.getOutputStream());
		} catch (FastDFSException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据指定的路径删除服务器文件，适用于没有保存数据库记录的文件
	 *
	 * @param filePath
	 */
	@ApiOperation(httpMethod = "GET", value = "根据指定的路径删除服务器文件", notes = "根据指定的路径删除服务器文件", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/delete/file", method = RequestMethod.GET)
	@ResponseBody
	public FileResponseData deleteFile(
			@ApiParam(value = "文件路径") @RequestParam(value = "filePath", defaultValue = "", required = true) String filePath
			) {
		FileResponseData responseData = new FileResponseData();

		if (StringUtils.isEmpty(filePath)) {
			responseData.setSuccess(false);
			responseData.setMessage("文件路径为空");
		} else {
			try {
				fastDFSClient.deleteFile(filePath);
			} catch (FastDFSException e) {
				e.printStackTrace();
				responseData.setSuccess(false);
				responseData.setCode(e.getCode());
				responseData.setMessage(e.getMessage());
			}
		}
		return responseData;
	}

	/**
	 * 获取访问文件的token
	 *
	 * @param filePath 文件路径
	 * @return
	 */
	@ApiOperation(value = "获取访问文件的token", notes = "获取访问文件的token")
	@RequestMapping(value = "/get/token", method = RequestMethod.GET)
	@ResponseBody
	public FileResponseData getToken(
			@ApiParam(value = "文件路径") @RequestParam(value = "filePath", defaultValue = "", required = true) String filePath
			) {
		FileResponseData responseData = new FileResponseData();
		// 设置访文件的Http地址. 有时效性.
		String token = FastDFSClient.getToken(filePath, fastDFSHttpSecretKey);
		responseData.setToken(token);
		responseData.setHttpUrl(fileServerAddr + "/" + filePath + "?" + token);

		return responseData;
	}

	/**
	 * 上传通用方法，只上传到服务器，不保存记录到数据库
	 *
	 * @param file
	 * @param request
	 * @return
	 */
	public FileResponseData uploadSample(MultipartFile file, HttpServletRequest request) {
		FileResponseData responseData = new FileResponseData();
		try {
			// 上传到服务器
			String filepath = fastDFSClient.uploadFileWithMultipart(file);

			responseData.setFileName(file.getOriginalFilename());
			responseData.setFilePath(filepath);
			responseData.setFileType(FastDFSClient.getFilenameSuffix(file.getOriginalFilename()));
			// 设置访文件的Http地址. 有时效性.
			String token = FastDFSClient.getToken(filepath, fastDFSHttpSecretKey);
			responseData.setToken(token);
			responseData.setHttpUrl(fileServerAddr + "/" + filepath + "?" + token);
		} catch (FastDFSException e) {
			responseData.setSuccess(false);
			responseData.setCode(e.getCode());
			responseData.setMessage(e.getMessage());
		}

		return responseData;
	}

}
