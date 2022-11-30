package com.absencemanger.absencemanger.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Absence extends BaseEntity{
    Long admitterId;
    String admitterNote;
    LocalDateTime confirmationDate;
    LocalDateTime rejectedAt;
    LocalDate startDate;
    LocalDate endDate;
    Long crewId;
    String memberNote;
    String type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "userId",name = "userId")
    User user;

}
