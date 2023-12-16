package io.bootify.l10_minor_project.service;

import io.bootify.l10_minor_project.domain.Flat;
import io.bootify.l10_minor_project.domain.User;
import io.bootify.l10_minor_project.domain.Visit;
import io.bootify.l10_minor_project.domain.Visitor;
import io.bootify.l10_minor_project.model.VisitDTO;
import io.bootify.l10_minor_project.model.VisitStatus;
import io.bootify.l10_minor_project.repos.FlatRepository;
import io.bootify.l10_minor_project.repos.UserRepository;
import io.bootify.l10_minor_project.repos.VisitRepository;
import io.bootify.l10_minor_project.repos.VisitorRepository;
import io.bootify.l10_minor_project.util.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
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

    public List<VisitDTO> findAllWaitingVisits() {
        final List<Visit> visits = visitRepository.findByStatus(VisitStatus.WAITING);
        return visits.stream()
                .map(visit -> mapToDTO(visit, new VisitDTO()))
                .toList();
    }

    public VisitDTO get(final Long id) {
        return visitRepository.findById(id)
                .map(visit -> mapToDTO(visit, new VisitDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final VisitDTO visitDTO) throws BadRequestException {
        final Visit visit = new Visit();
        visitDTO.setStatus(VisitStatus.WAITING);
        mapToEntity(visitDTO, visit);
        return visitRepository.save(visit).getId();
    }

    public void update(final Long id, final VisitDTO visitDTO) throws BadRequestException {
        final Visit visit = visitRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(visitDTO, visit);
        visitRepository.save(visit);
    }

    public void updateInTime(Long visitId){
        Visit visit = visitRepository.findById(visitId).orElse(null);
        if(visit==null){
            throw new NotFoundException();
        }
        if(visit.getStatus().equals(VisitStatus.APPROVED)){
            visit.setInTime(LocalDateTime.now());
            visit.setStatus(VisitStatus.INPROGRESS);
            visitRepository.save(visit);
        }
    }

    public void updateOutTime(Long visitId){
        Visit visit = visitRepository.findById(visitId).orElse(null);
        if(visit==null){
            throw new NotFoundException();
        }
        if(visit.getStatus().equals(VisitStatus.INPROGRESS)){
            visit.setOutTime(LocalDateTime.now());
            visit.setStatus(VisitStatus.COMPLETED);
            visitRepository.save(visit);
        }
    }


    @Transactional
    public void approve(Long visitId, Long userId) throws BadRequestException {
        Visit visit = visitRepository.findById(visitId).orElse(null);
        if(visit==null){
            throw new NotFoundException();
        }
        if(visit.getStatus().equals(VisitStatus.WAITING)){
            User user = userRepository.findById(userId).get();
            if(user.getFlat() == visit.getFlat()){
                visit.setStatus(VisitStatus.APPROVED);
                visitRepository.save(visit);
            }
            else{
                throw new BadRequestException("Not Authorized");
            }
        }
    }

    public void reject(Long visitId){
        Visit visit = visitRepository.findById(visitId).orElse(null);
        if(visit==null){
            throw new NotFoundException();
        }
        if(visit.getStatus().equals(VisitStatus.WAITING)){
            visit.setStatus(VisitStatus.REJECTED);
            visitRepository.save(visit);
        }
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
        visitDTO.setFlatNumber(visit.getFlat() == null ? null : visit.getFlat().getNumber());
        return visitDTO;
    }

    private Visit mapToEntity(final VisitDTO visitDTO, final Visit visit) throws BadRequestException {
        visit.setStatus(visitDTO.getStatus());
        visit.setInTime(visitDTO.getInTime());
        visit.setOutTime(visitDTO.getOutTime());
        visit.setPurpose(visitDTO.getPurpose());
        visit.setUrlOfImage(visitDTO.getUrlOfImage());
        visit.setNoOfPeople(visitDTO.getNoOfPeople());
        final Visitor visitor = visitDTO.getVisitor() == null ? null : visitorRepository.findById(visitDTO.getVisitor())
                .orElseThrow(() -> new NotFoundException("visitor not found"));
        visit.setVisitor(visitor);
        final Flat flat = flatRepository.findByNumber(visitDTO.getFlatNumber());
        if(flat ==null){
            throw new BadRequestException("Invalid Flat number");
        }
        visit.setFlat(flat);
        return visit;
    }

}
