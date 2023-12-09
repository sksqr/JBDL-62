package io.bootify.l10_minor_project.service;

import io.bootify.l10_minor_project.domain.Address;
import io.bootify.l10_minor_project.domain.Visitor;
import io.bootify.l10_minor_project.model.VisitorDTO;
import io.bootify.l10_minor_project.repos.AddressRepository;
import io.bootify.l10_minor_project.repos.VisitorRepository;
import io.bootify.l10_minor_project.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class VisitorService {

    private final VisitorRepository visitorRepository;
    private final AddressRepository addressRepository;

    public VisitorService(final VisitorRepository visitorRepository,
            final AddressRepository addressRepository) {
        this.visitorRepository = visitorRepository;
        this.addressRepository = addressRepository;
    }

    public List<VisitorDTO> findAll() {
        final List<Visitor> visitors = visitorRepository.findAll(Sort.by("id"));
        return visitors.stream()
                .map(visitor -> mapToDTO(visitor, new VisitorDTO()))
                .toList();
    }

    public VisitorDTO get(final Long id) {
        return visitorRepository.findById(id)
                .map(visitor -> mapToDTO(visitor, new VisitorDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final VisitorDTO visitorDTO) {
        final Visitor visitor = new Visitor();
        mapToEntity(visitorDTO, visitor);
        return visitorRepository.save(visitor).getId();
    }

    public void update(final Long id, final VisitorDTO visitorDTO) {
        final Visitor visitor = visitorRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(visitorDTO, visitor);
        visitorRepository.save(visitor);
    }

    public void delete(final Long id) {
        visitorRepository.deleteById(id);
    }

    private VisitorDTO mapToDTO(final Visitor visitor, final VisitorDTO visitorDTO) {
        visitorDTO.setId(visitor.getId());
        visitorDTO.setEmail(visitor.getEmail());
        visitorDTO.setName(visitor.getName());
        visitorDTO.setPhone(visitor.getPhone());
        visitorDTO.setIdNumber(visitor.getIdNumber());
        visitorDTO.setAddress(visitor.getAddress() == null ? null : visitor.getAddress().getId());
        return visitorDTO;
    }

    private Visitor mapToEntity(final VisitorDTO visitorDTO, final Visitor visitor) {
        visitor.setEmail(visitorDTO.getEmail());
        visitor.setName(visitorDTO.getName());
        visitor.setPhone(visitorDTO.getPhone());
        visitor.setIdNumber(visitorDTO.getIdNumber());
        final Address address = visitorDTO.getAddress() == null ? null : addressRepository.findById(visitorDTO.getAddress())
                .orElseThrow(() -> new NotFoundException("address not found"));
        visitor.setAddress(address);
        return visitor;
    }

    public boolean emailExists(final String email) {
        return visitorRepository.existsByEmailIgnoreCase(email);
    }

    public boolean phoneExists(final String phone) {
        return visitorRepository.existsByPhoneIgnoreCase(phone);
    }

    public boolean idNumberExists(final String idNumber) {
        return visitorRepository.existsByIdNumberIgnoreCase(idNumber);
    }

}
