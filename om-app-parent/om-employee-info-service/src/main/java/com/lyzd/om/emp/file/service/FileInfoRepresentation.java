package com.lyzd.om.emp.file.service;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Thinker
 *
 */
@Data
public class FileInfoRepresentation implements Serializable {
	
	private static final long serialVersionUID = 4776525989639806800L;
	
	@ApiModelProperty(value = "返回文件id")
	private String id;
	@ApiModelProperty(value = "返回文件名")
	private String fileName;
	/**1: 身份证；2: 劳动合同；3: 保密协议；4: 学历证书；5: 学位证书。。。**/
	@ApiModelProperty(value = "返回文件类型：1: 身份证；2: 劳动合同；3: 保密协议；4: 学历证书；5: 学位证书。。。")
	private String fileType;
	@ApiModelProperty(value = "返回文件说明")
	private String comment;
	
	public FileInfoRepresentation() {
		super();
	}
}
