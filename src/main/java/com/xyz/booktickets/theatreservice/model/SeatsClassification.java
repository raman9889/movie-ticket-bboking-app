package com.xyz.booktickets.theatreservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "seat_classification", schema = "theatre")
@Getter @Setter
public class SeatsClassification {
    @Id
    @Column(name = "seat_type_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long seatTypeId;

    @Column(name = "price")
    private Double price;

    @Column(name = "seat_classification_name")
    private String seatClassificationName;

}
