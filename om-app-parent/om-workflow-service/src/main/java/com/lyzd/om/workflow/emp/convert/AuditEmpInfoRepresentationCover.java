package com.lyzd.om.workflow.emp.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.lyzd.om.emp.info.representation.EmployeeRepresentation;
import com.lyzd.om.workflow.emp.representation.AuditEmpInfoRepresentation;

/**
 * 审核信息展现对象的转换
 * @author Thinker
 *
 */
//@Mapper
public interface AuditEmpInfoRepresentationCover {

	AuditEmpInfoRepresentationCover INSTANCE = Mappers.getMapper(AuditEmpInfoRepresentationCover.class);

	/**
	 * properties copy
	 * @param source Source objects, usually, may be domain objects. 
	 * @return target objects, for instance, view objects.
	 */
	AuditEmpInfoRepresentation toVO(EmployeeRepresentation source);

}
