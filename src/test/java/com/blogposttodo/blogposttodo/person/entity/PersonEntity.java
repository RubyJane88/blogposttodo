package com.blogposttodo.blogposttodo.person.entity;


import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
/*the above decorators are equivalent of this @Data,
 and they don't slow down JPA*/
//@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String email;

    private String mobileNumber;
    private byte[] storeHash;
    private byte[] storeSalt;



}
