package io.bootify.l10_minor_project.repos;

import io.bootify.l10_minor_project.domain.Flat;
import io.bootify.l10_minor_project.domain.Visit;
import io.bootify.l10_minor_project.model.VisitStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface VisitRepository extends JpaRepository<Visit, Long> {
    List<Visit> findByStatus(VisitStatus status);
    List<Visit> findByStatusAndFlat(VisitStatus status, Flat flat);
}
