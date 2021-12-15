package com.blogposttodo.blogposttodo.user.entity;


import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "geos")
@Entity(name = "geo")
@AllArgsConstructor
public class GeoEntity {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO, generator = "UUID")
    @Column(nullable = false, updatable = false)
    private UUID id;
    private String lat;
    private String lng;
}
