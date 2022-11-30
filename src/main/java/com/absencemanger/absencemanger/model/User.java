package com.absencemanger.absencemanger.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class User extends BaseEntity implements Serializable {
    Long crewId;
    String image;
    String name;
    Long userId;
}
