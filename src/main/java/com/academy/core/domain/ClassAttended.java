package com.academy.core.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class ClassAttended {
	
	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(strategy =
			"org.hibernate.id.enhanced.SequenceStyleGenerator",
			name = "generator",
			parameters = {
					@org.hibernate.annotations.Parameter(name = "sequence_name", value = "hibernate_sequence"),
					@org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
					@org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
			}
	)
	private Long id;
	private Date date;
	@OneToOne
	private Academy academy;
	@ManyToMany
	private List<Member> members = Collections.emptyList();
	
}
