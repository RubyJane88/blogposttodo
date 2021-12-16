package com.blogposttodo.blogposttodo.user.entity;


import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "companies")
@Entity(name = "company")
@AllArgsConstructor
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO, generator = "UUID")
    @Column(nullable = false, updatable = false)
    private UUID id;
    private String name;
    private String catchPhrase;
    private String bs;




}
