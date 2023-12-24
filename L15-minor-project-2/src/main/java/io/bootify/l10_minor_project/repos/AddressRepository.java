package io.bootify.l10_minor_project.repos;

import io.bootify.l10_minor_project.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address, Long> {
}
