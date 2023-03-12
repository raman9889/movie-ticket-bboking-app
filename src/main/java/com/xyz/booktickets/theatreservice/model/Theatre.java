package com.xyz.booktickets.theatreservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "theatre", schema = "theatre")
@Getter @Setter
public class Theatre {
    @Id
    @Column(name = "theatre_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long theatreId;

    @Column(name = "theatre_name", nullable = false, unique = true)
    private String theatreName;

    @Column(name = "theatre_address")
    private String theatreAddress;

    @Column(name = "city_id")
    private Long cityId;

}
