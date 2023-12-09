package io.bootify.l10_minor_project.repos;

import io.bootify.l10_minor_project.domain.Flat;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FlatRepository extends JpaRepository<Flat, Long> {
}
