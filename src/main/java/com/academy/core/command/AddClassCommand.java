package com.academy.core.command;



import com.academy.core.command.result.AddClassResult;
import com.academy.core.dto.MemberBean;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class AddClassCommand implements Command<AddClassResult>{

	private String userName;
	private Date date;
	private String title;
	private  List<MemberBean> members;


}
