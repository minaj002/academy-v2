package com.weststein.repository.business;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class BusinessProfile {

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
    private Long businessId;
    private LocalDateTime created;
    private String url;
    private String ipAddress;
    private String industry;
    private String descriptionOfBusiness;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> countriesOfOperation;
    private String detailsOfTargetMarket;
    private boolean affiliatesSellProducts;
    private String purposeOfRelationshipsWithAffiliates;


}

