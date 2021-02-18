package com.lyzd.om.web.admin.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyzd.om.emp.info.sdk.commond.FileUploadCommand;
import com.lyzd.om.emp.info.sdk.event.FileCreatedHandler;
import com.lyzd.om.emp.info.service.UpLoadService;
import com.lyzd.om.spring.common.dto.Result;

import io.swagger.annotations.Api;
import lombok.experimental.PackagePrivate;

/**
 * @author GM
 *
 */
@Controller
@RequestMapping("/api/upload")
@Api(tags = "基本信息：上传excel")
public class UploadExcelController {
	  @Autowired
      private UpLoadService uploadService;
	  
	  
      @GetMapping("/uploadExcel")
      @ResponseBody
      public Result uploadExcel() throws IOException{
    	  

			InputStream inputStream = new FileInputStream("D:/file/shanghui.xlsx");
			
			System.out.print("字节流一：");
			FileUploadCommand fileUploadCommand=new FileUploadCommand(inputStream);
			FileCreatedHandler fileCreatedHandler =new FileCreatedHandler();
			try {
				List list=fileCreatedHandler.getCourseListByExcel1(inputStream,"shanghui.xlsx");
				fileCreatedHandler.saveExcelInfo2(list);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//    	  try {
//			//uploadService.saveFile(fileUploadCommand);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
			finally {
			inputStream.close();
		}
          return  Result.ok();
      }
      

}
