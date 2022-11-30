package com.absencemanger.absencemanger.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApproveRejectDTO {
    Long admitterId = 1L;
    String admitterNote;
    boolean accepted;
}
