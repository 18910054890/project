package com.lyzd.om.emp.info.model;

import static com.lyzd.om.shared.utils.UuidGenerator.newUuid;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lyzd.om.shared.model.BaseAggregate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;
/**
 * @author GM
 *
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Education {
	private String phone;
	private String schoolName;
	private String major;
	private String education;
	private String startTime;
	private String endTime;
	private String id;

	 public static Education create(String phone,String schoolName,String major,String education,String startTime,String endTime) {
		 Education myeducation = Education.builder().id(newUuid()).phone(phone).schoolName(schoolName)
				 .major(major).education(education).startTime(startTime)
				 .endTime(endTime).build();
		 return myeducation;
	 }
}
