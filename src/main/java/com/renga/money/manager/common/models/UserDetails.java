package com.renga.money.manager.common.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.renga.money.manager.common.models.entities.SEX;
import com.renga.money.manager.common.models.entities.UserDetailsEntity;
import com.renga.money.manager.common.util.MMUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private SEX sex;
    private LocalDate dob;
    private String createdBy;
    private LocalDateTime createdTimestamp;
    private String modifiedBy;
    private LocalDateTime modifiedTimestamp;
    private Set<String> roles = new HashSet<>();

    public UserDetails(UserDetailsEntity userDetailsEntity){
        this.id = userDetailsEntity.getId();
        this.firstName = userDetailsEntity.getFirstName();
        this.middleName = userDetailsEntity.getMiddleName();
        this.lastName = userDetailsEntity.getLastName();
        this.email = userDetailsEntity.getEmail();
        this.sex = userDetailsEntity.getSex();
        this.dob = userDetailsEntity.getDob();
        this.roles = userDetailsEntity.getRoles();

        this.createdBy = userDetailsEntity.getCreatedBy();
        this.createdTimestamp = userDetailsEntity.getCreatedTimestamp();
        this.modifiedBy = userDetailsEntity.getModifiedBy();
        this.modifiedTimestamp = userDetailsEntity.getModifiedTimestamp();
    }



    @JsonGetter
    public Integer age(){
        if(Objects.isNull(dob) || dob.isAfter(MMUtil.getLocalDateNYZone())){
            return null;
        }

        return Period.between(dob, MMUtil.getLocalDateNYZone()).getYears();
    }

    @JsonGetter
    public String getFullName(){
        if(StringUtils.isBlank(this.firstName)){
            return null;
        }

        String fullName = StringUtils.trimToEmpty(this.firstName);
        if(StringUtils.isNotBlank(this.middleName)){
            fullName = fullName.concat(" ").concat(this.middleName);
        }

        if(StringUtils.isNotBlank(this.lastName)){
            fullName = fullName.concat(" ").concat(this.lastName);
        }
        return fullName;
    }
}
