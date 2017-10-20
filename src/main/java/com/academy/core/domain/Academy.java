package com.academy.core.domain;

import com.academy.core.annotation.Email;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Academy {

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
	private String name;
	@Email
	private String email;
	@OneToMany
	private List<Section> sections = new ArrayList<>();
}
