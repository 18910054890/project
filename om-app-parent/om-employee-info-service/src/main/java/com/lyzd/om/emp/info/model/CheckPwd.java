package com.lyzd.om.emp.info.model;

import static com.lyzd.om.shared.utils.UuidGenerator.newUuid;

import java.time.Instant;

import com.lyzd.om.shared.model.BaseAggregate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author GM
 *
 */

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CheckPwd extends BaseAggregate {
	private String newPwd;
	private String phoneNo;

	public static CheckPwd create(String newPwd, String phoneNo) {

		CheckPwd checkPwd = CheckPwd.builder().newPwd(newPwd).phoneNo(phoneNo).build();
		return checkPwd;
	}

	public static boolean checkPassWord(String newPwd) {
		String reg1 = "(?=.*[a-z])(?=.*\\d)(?=.*[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t).{8,20}$";
		String reg2 = "(?=.*[a-z])(?=.*[A-Z])(?=.*[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t).{8,20}$";
		String reg3 = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,20}$";
		String reg4 = "(?=.*[A-Z])(?=.*\\d)(?=.*[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t).{8,20}$";
		String reg5 = "(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t).{8,20}$";
		String reg6 = "(?=.*[a-z])(?=.*\\d).{8,20}$";
		String reg7 = "(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
		String reg8 = "(?=.*[a-z])(?=.*[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t).{8,20}$";
		String reg9 = "(?=.*[A-Z])(?=.*\\d).{8,20}$";
		String reg10 = "(?=.*[A-Z])(?=.*[ _`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\\n|\\r|\\t).{8,20}$";
		String reg11 = "(?=.*\\d)(?=.*[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t).{8,20}$";
		if (java.util.regex.Pattern.matches(reg1, newPwd) || java.util.regex.Pattern.matches(reg2, newPwd)
				|| java.util.regex.Pattern.matches(reg3, newPwd) || java.util.regex.Pattern.matches(reg4, newPwd)
				|| java.util.regex.Pattern.matches(reg5, newPwd) || java.util.regex.Pattern.matches(reg6, newPwd)
				|| java.util.regex.Pattern.matches(reg7, newPwd) || java.util.regex.Pattern.matches(reg8, newPwd)
				|| java.util.regex.Pattern.matches(reg9, newPwd) || java.util.regex.Pattern.matches(reg10, newPwd)
				|| java.util.regex.Pattern.matches(reg11, newPwd)) {
			return true;
		} else {
			return false;
		}

	}

}
