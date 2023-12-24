package io.bootify.l10_minor_project.repos;

import io.bootify.l10_minor_project.domain.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VisitorRepository extends JpaRepository<Visitor, Long> {

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByPhoneIgnoreCase(String phone);

    boolean existsByIdNumberIgnoreCase(String idNumber);

}
