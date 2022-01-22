package com.renga.money.manager.common.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "ROLE", schema = "MMDB")
@AllArgsConstructor
public class RoleEntity implements GrantedAuthority {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToMany(mappedBy = "roleEntities", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<UserDetailsEntity> userDetails;

    @Override
    public String getAuthority() {
        return this.getName();
    }

    public RoleEntity(){
        this.name = "ROLE_USER";
    }
}
