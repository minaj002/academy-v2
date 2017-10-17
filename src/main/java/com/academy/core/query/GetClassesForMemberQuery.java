package com.academy.core.query;

import com.academy.core.query.result.GetClassesForMemberResult;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class GetClassesForMemberQuery implements Query<GetClassesForMemberResult> {

	private String user;
	private Long memberId;

}
