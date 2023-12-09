package io.bootify.l10_minor_project.service;

import io.bootify.l10_minor_project.domain.Flat;
import io.bootify.l10_minor_project.domain.User;
import io.bootify.l10_minor_project.domain.Visit;
import io.bootify.l10_minor_project.domain.Visitor;
import io.bootify.l10_minor_project.model.VisitDTO;
import io.bootify.l10_minor_project.repos.FlatRepository;
import io.bootify.l10_minor_project.repos.UserRepository;
import io.bootify.l10_minor_project.repos.VisitRepository;
import io.bootify.l10_minor_project.repos.VisitorRepository;
import io.bootify.l10_minor_project.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class VisitService {

    private final VisitRepository visitRepository;
    private final VisitorRepository visitorRepository;
    private final FlatRepository flatRepository;
    private final UserRepository userRepository;

    public VisitService(final VisitRepository visitRepository,
            final VisitorRepository visitorRepository, final FlatRepository flatRepository,
            final UserRepository userRepository) {
        this.visitRepository = visitRepository;
        this.visitorRepository = visitorRepository;
        this.flatRepository = flatRepository;
        this.userRepository = userRepository;
    }

    public List<VisitDTO> findAll() {
        final List<Visit> visits = visitRepository.findAll(Sort.by("id"));
        return visits.stream()
                .map(visit -> mapToDTO(visit, new VisitDTO()))
                .toList();
    }

    public VisitDTO get(final Long id) {
        return visitRepository.findById(id)
                .map(visit -> mapToDTO(visit, new VisitDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final VisitDTO visitDTO) {
        final Visit visit = new Visit();
        mapToEntity(visitDTO, visit);
        return visitRepository.save(visit).getId();
    }

    public void update(final Long id, final VisitDTO visitDTO) {
        final Visit visit = visitRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(visitDTO, visit);
        visitRepository.save(visit);
    }

    public void delete(final Long id) {
        visitRepository.deleteById(id);
    }

    private VisitDTO mapToDTO(final Visit visit, final VisitDTO visitDTO) {
        visitDTO.setId(visit.getId());
        visitDTO.setStatus(visit.getStatus());
        visitDTO.setInTime(visit.getInTime());
        visitDTO.setOutTime(visit.getOutTime());
        visitDTO.setPurpose(visit.getPurpose());
        visitDTO.setUrlOfImage(visit.getUrlOfImage());
        visitDTO.setNoOfPeople(visit.getNoOfPeople());
        visitDTO.setVisitor(visit.getVisitor() == null ? null : visit.getVisitor().getId());
        visitDTO.setFlat(visit.getFlat() == null ? null : visit.getFlat().getId());
        visitDTO.setUser(visit.getUser() == null ? null : visit.getUser().getId());
        return visitDTO;
    }

    private Visit mapToEntity(final VisitDTO visitDTO, final Visit visit) {
        visit.setStatus(visitDTO.getStatus());
        visit.setInTime(visitDTO.getInTime());
        visit.setOutTime(visitDTO.getOutTime());
        visit.setPurpose(visitDTO.getPurpose());
        visit.setUrlOfImage(visitDTO.getUrlOfImage());
        visit.setNoOfPeople(visitDTO.getNoOfPeople());
        final Visitor visitor = visitDTO.getVisitor() == null ? null : visitorRepository.findById(visitDTO.getVisitor())
                .orElseThrow(() -> new NotFoundException("visitor not found"));
        visit.setVisitor(visitor);
        final Flat flat = visitDTO.getFlat() == null ? null : flatRepository.findById(visitDTO.getFlat())
                .orElseThrow(() -> new NotFoundException("flat not found"));
        visit.setFlat(flat);
        final User user = visitDTO.getUser() == null ? null : userRepository.findById(visitDTO.getUser())
                .orElseThrow(() -> new NotFoundException("user not found"));
        visit.setUser(user);
        return visit;
    }

}
