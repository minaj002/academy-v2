package com.academy.core.query.result;

import com.academy.core.dto.ClassAttendedBean;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class GetClassesForMemberResult implements QueryResult {

	private List<ClassAttendedBean> classesAttended= Collections.emptyList();

	public GetClassesForMemberResult(List<ClassAttendedBean> classesAttended) {
		this.classesAttended = classesAttended;
	}

}
