package com.lyzd.om.web.file.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lyzd.om.emp.file.service.FileInfo;
import com.lyzd.om.emp.file.service.FileInfoRepresentation;
import com.lyzd.om.emp.file.service.FileInfoService;
import com.lyzd.om.spring.common.annotation.LogAnnotation;
import com.lyzd.om.spring.common.dto.JwtUserDto;
import com.lyzd.om.spring.common.dto.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Thinker
 *
 */
@Api(tags = "文件")
@RestController
@RequestMapping("/api/files")
public class FileController {

	@Autowired
	private FileInfoService fileInfoService;
	@LogAnnotation
	@PostMapping("/upload/{fileType}")
	@ApiOperation(value = "文件上传")
	public Result upload(MultipartFile file, @PathVariable String fileType) throws IOException {
		
		JwtUserDto user = this.getJwtUserDto();
		return Result.ok().resData(fileInfoService.save(file, user.getMyUser().getUserId()+"", fileType)).message("文件上传成功");
	}
	
	@GetMapping(value = "/queryFileInfo")
    @ResponseBody
    @ApiOperation(value = "文件信息查询, fileType可选")
    public Result queryFileInfo(String fileType) throws UnsupportedEncodingException {
		
		String userId = this.getJwtUserDto().getMyUser().getUserId().toString();
		
		List<FileInfoRepresentation> res = fileInfoService.queryFileInfo(userId, fileType);
		
		return Result.ok().count(Long.valueOf("" + res.size())).data(res);
    }
	
	@GetMapping(value = "/download/{id}")
    @ResponseBody
    @ApiOperation(value = "下载")
    public void download(HttpServletResponse response, @PathVariable String id) throws UnsupportedEncodingException {
		
		//String fileName = this.getJwtUserDto().getMyUser().getUserName() + "-" + id +".pdf";
		
		 FileInfo fi = fileInfoService.byId(id);
		 byte[] fileContent = fi.getContent();
				 
		 String fileName = fi.getFileName();
		
		// 配置文件下载
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Type", "application/octet-stream;charset=UTF-8");
        // 下载文件能正常显示中文
        //response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"),"iso-8859-1"));
        
		 OutputStream os;
		try {
			os = response.getOutputStream();
			 os.write(fileContent);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    @ApiOperation(value = "文件删除")
    public Result delete(@PathVariable String id) throws UnsupportedEncodingException {
		fileInfoService.deleteById(id);
		return Result.ok().message("删除成功");
	}
	
	 private JwtUserDto getJwtUserDto() {
	    	return (JwtUserDto)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 }


}
