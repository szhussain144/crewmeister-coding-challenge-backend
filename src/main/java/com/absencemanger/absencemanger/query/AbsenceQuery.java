package com.absencemanger.absencemanger.query;

import com.absencemanger.absencemanger.dto.SearchDTO;
import com.absencemanger.absencemanger.model.QAbsence;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AbsenceQuery {
    public Predicate customPredicate(SearchDTO request) {
        List<BooleanExpression> predicateList = new ArrayList<>();
        QAbsence absence = QAbsence.absence;
        LocalDateTime startDateTime = null;
        LocalDateTime endDateTime = null;
        System.out.println(request.getSearchBy());
        if (request.getSearchBy() != null && request.getSearchBy().equals("type")) {
            predicateList.add(absence.type
                    .like("%" + request.getSearchValue() + "%"));
        }
        if (request.getSearchBy() != null && request.getSearchBy().equals("crewId")) {
            predicateList.add(absence.crewId.eq(Long.parseLong(request.getSearchValue())));
        }
        if (request.getSearchBy() != null && request.getSearchBy().equals("admitterId")) {
            predicateList.add(absence.admitterId.eq(Long.parseLong(request.getSearchValue())));
        }
        if (request.getStartDate() != null && request.getEndDate() == null) {
            startDateTime = request.getStartDate().atStartOfDay();
            predicateList.add((absence.createdAt.goe(startDateTime)));
        }
        if (request.getEndDate() != null && request.getStartDate() == null) {
            endDateTime = request.getEndDate().atStartOfDay().plusHours(23);
            predicateList.add((absence.createdAt.loe(endDateTime)));
        }
        if(request.getStartDate() != null && request.getEndDate() != null){
            startDateTime = request.getStartDate().atStartOfDay();
            endDateTime = request.getEndDate().atStartOfDay().plusHours(23);
            predicateList.add((absence.createdAt.between(startDateTime,endDateTime)));
        }


        return Expressions.allOf(predicateList.toArray(new BooleanExpression[0]));

    }
}
