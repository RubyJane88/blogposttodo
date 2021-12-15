package com.blogposttodo.blogposttodo.user.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.UUID;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "users")
@Entity(name = "user")
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO, generator = "UUID")
    @Column(nullable = false, updatable = false)
    private UUID id;

    @NotBlank(message = "Name cannot be blank")
    private String Name;
    @NotBlank(message = "Username cannot be blank")
    private String userName;

    @NotBlank(message = "Email cannot be blank")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
   private CompanyEntity company;

   @NotBlank(message = "Phone cannot be blank")
   private String phone;
   private String website;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserEntity that = (UserEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}



