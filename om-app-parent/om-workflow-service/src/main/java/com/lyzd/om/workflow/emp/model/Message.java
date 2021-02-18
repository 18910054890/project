package com.lyzd.om.workflow.emp.model;

import static com.lyzd.om.shared.utils.UuidGenerator.newUuid;

import com.lyzd.om.shared.model.BaseAggregate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Employee's message
 * 
 * @author Thinker
 *
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message extends BaseAggregate {

	/**唯一主键**/
	private String id;
	/**发起人用户ID**/
	private String initUserId;
	/**普通用户ID，HR，或部门经理等**/
	private String reader;
    /**消息内容**/
    private String content;
    /**与该消息关联的业务ID**/
    private String bussinesId;
    /**是否已读，1:已读**/
    private String isRead;
    
    /**创建日期**/
    private String createdAt;
    

	public static Message create(String initUserId, String reader,
			String content, String bussinesId, String isRead, String createdAt) {
		Message e = Message.builder().id(newUuid()).initUserId(initUserId).reader(reader).content(content).bussinesId(bussinesId).
				isRead(isRead).createdAt(createdAt).build();

		return e;
	}
}
