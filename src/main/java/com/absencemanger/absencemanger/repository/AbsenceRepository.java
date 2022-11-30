package com.absencemanger.absencemanger.repository;

import com.absencemanger.absencemanger.model.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface AbsenceRepository extends JpaRepository<Absence,Long>, QuerydslPredicateExecutor<Absence> {
}
