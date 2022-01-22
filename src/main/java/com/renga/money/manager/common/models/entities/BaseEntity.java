package com.renga.money.manager.common.models.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.renga.money.manager.common.util.MMUtil;
import lombok.Data;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
public class BaseEntity implements Serializable {

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    @JsonDeserialize(using= LocalDateTimeDeserializer.class)
    @Column(name = "CREATED_TIMESTAMP")
    private LocalDateTime createdTimestamp;

    @Column(name = "MODIFIED_BY")
    private String modifiedBy;

    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    @JsonDeserialize(using= LocalDateTimeDeserializer.class)
    @Column(name = "MODIFIED_TIMESTAMP")
    private LocalDateTime modifiedTimestamp;

    @PrePersist
    public void prePersist(){
        this.createdTimestamp = MMUtil.getLocalDateTimeNYZone();
        this.modifiedTimestamp = MMUtil.getLocalDateTimeNYZone();
    }

    @PreUpdate
    public void preUpdate(){
        this.modifiedTimestamp = MMUtil.getLocalDateTimeNYZone();
    }
}
