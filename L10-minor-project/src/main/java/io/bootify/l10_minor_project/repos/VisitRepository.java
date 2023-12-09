package io.bootify.l10_minor_project.repos;

import io.bootify.l10_minor_project.domain.Visit;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VisitRepository extends JpaRepository<Visit, Long> {
}
