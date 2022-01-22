package com.renga.money.manager.common.models.entities;

import com.renga.money.manager.common.models.UserDetailsRequestWrapper;
import com.renga.money.manager.common.util.MMUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "USER_DETAILS", schema = "MMDB")
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsEntity extends BaseEntity{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Column(name = "SEX")
    @Enumerated(EnumType.STRING)
    private SEX sex;

    @NotNull
    @Column(name = "DOB")
    private LocalDate dob;

    @NotNull
    @Column(name = "EMAIL")
    private String email;

    @Column(name = "IS_ACTIVE")
    private boolean isActive = true;

    @Column(name = "PASSWORD")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<RoleEntity> roleEntities;

    public UserDetailsEntity(UserDetailsRequestWrapper userDetailsRequestWrapper){
        this.firstName = userDetailsRequestWrapper.getFirstName();
        this.middleName = userDetailsRequestWrapper.getMiddleName();
        this.lastName = userDetailsRequestWrapper.getLastName();
        this.dob = userDetailsRequestWrapper.getDob();
        this.sex = userDetailsRequestWrapper.getSex();
        this.email = userDetailsRequestWrapper.getEmail();

        Set<RoleEntity> roleEntities = new HashSet<>();
        roleEntities.add(new RoleEntity());
        this.roleEntities = roleEntities;
        this.password = MMUtil.B_CRYPT_ENCODER.encode(userDetailsRequestWrapper.getPassword());
        this.isActive = true;
        super.setCreatedBy("SYSTEM");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserDetailsEntity that = (UserDetailsEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    public Set<String> getRoles(){
        if(CollectionUtils.isEmpty(this.roleEntities)){
            return new HashSet<>();
        }

        return this.roleEntities.stream()
                .filter(Objects::nonNull)
                .map(roleEntity -> roleEntity.getName())
                .collect(Collectors.toSet());
    }
}