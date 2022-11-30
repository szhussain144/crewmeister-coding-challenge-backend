package com.absencemanger.absencemanger.service;

import com.absencemanger.absencemanger.dto.ApproveRejectDTO;
import com.absencemanger.absencemanger.dto.SearchDTO;
import com.absencemanger.absencemanger.model.Absence;
import com.absencemanger.absencemanger.query.AbsenceQuery;
import com.absencemanger.absencemanger.repository.AbsenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AbsencesService {
    private final AbsenceRepository absenceRepository;
    final private LocalDateTime currentDatetime = LocalDateTime.now ();


    public Page<Absence> getAbsences(SearchDTO searchDTO, Pageable pageable){
        AbsenceQuery absenceQuery = new AbsenceQuery();
        Page<Absence> absenceList = null;
        if(searchDTO == null){
         absenceList = absenceRepository.findAll(pageable);
        }else {
            absenceList = absenceRepository.findAll(absenceQuery.customPredicate(searchDTO), pageable);
        }
        return absenceList;
    }

    public Absence updateAbsence(Long id, ApproveRejectDTO approveRejectDTO){
        Optional<Absence> absence = absenceRepository.findById(id);
        if(absence.isEmpty()){
            return null;
        }else{
            return absence.map(absence1 -> {
                if(approveRejectDTO.isAccepted()) {
                    absence1.setConfirmationDate(currentDatetime);
                }else{
                    absence1.setRejectedAt(currentDatetime);
                }
                absence1.setAdmitterId(approveRejectDTO.getAdmitterId());
                absence1.setAdmitterNote(absence1.getAdmitterNote());
               return absenceRepository.save(absence1);
            }).orElse(null);
        }
    }
}
