package com.weststein.repository.specifications;

import com.weststein.repository.Person;
import com.weststein.repository.Person_;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by artis on 06/06/2017.
 */
public class PersonSpecifications {

    public static Specification<Person> findByFirstAndLastName(String search) {
        return (root, query, cb) -> cb.like(
                cb.concat(root.get(Person_.firstName), root.get(Person_.lastName)),
                search+"%"
        );
    }

}
