package com.burcu.entity;

import com.burcu.utility.EStatus;
import com.burcu.utility.EUserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String name;
    @Column(length = 50)
    private String surname;
    @Email // adsfadfasd@mail.com
    @Column(length = 50)
    private String email;
    @Column(length = 15)
    private String phone;
    @Size(max = 32)
    private String password;
    @Size(max = 32)
    private String rePassword;

    //@ManyToMany
    @ElementCollection
    private List<Long> favMovies;

    //@ManyToMany
    @ElementCollection //bu kullanım bir alternatif, sadece id göreceğiz.
    private List<Long> favGenres;

   // @OneToMany //One user can make many comments
    @ElementCollection
    private List<Long> comments;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EUserType userType=EUserType.USER;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EStatus Status=EStatus.PENDING;
}
