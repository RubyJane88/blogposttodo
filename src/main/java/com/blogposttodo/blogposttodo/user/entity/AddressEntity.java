package com.blogposttodo.blogposttodo.user.entity;


import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "addresses")
@Entity(name = "address")
@AllArgsConstructor
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO, generator = "UUID")
    @Column(nullable = false, updatable = false)
    private UUID id;
    private String street;
    private String suite;
    private String city;
    private String state;
    private String zipcode;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "geo_id", referencedColumnName = "id")
   private  GeoEntity geo;



}
