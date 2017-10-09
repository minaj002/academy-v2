package com.academy.core.query.result;

import com.academy.core.dto.AcademyBean;
import com.academy.core.dto.SectionBean;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class GetSectionsResult implements QueryResult {

	private List<SectionBean> sections;

}
