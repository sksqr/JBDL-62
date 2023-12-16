package io.bootify.l10_minor_project.rest;

import io.bootify.l10_minor_project.model.VisitDTO;
import io.bootify.l10_minor_project.service.VisitService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserPanelController {

    @Autowired
    private VisitService visitService;

    @PutMapping("/approveVisit/{visitId}")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<Void> approveVisit(@PathVariable Long visitId, @RequestHeader Long userId) throws BadRequestException {
        visitService.approve(visitId, userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/rejectVisit/{visitId}")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<Void> rejectVisit(@PathVariable Long visitId){
        visitService.reject(visitId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/allPendingVisits")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<List<VisitDTO>> allPendingVisits(){
        visitService.findAllWaitingVisits();
        return ResponseEntity.ok().build();
    }
}
