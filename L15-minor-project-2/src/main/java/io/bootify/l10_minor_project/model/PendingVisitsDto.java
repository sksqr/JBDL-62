package io.bootify.l10_minor_project.model;

import io.bootify.l10_minor_project.domain.Visit;

import java.io.Serializable;
import java.util.List;

public class PendingVisitsDto implements Serializable {

    private static final long serialVersionUID = 1234567L;
    List<Visit> pendingVisits;
    Long flatId;

    public List<Visit> getPendingVisits() {
        return pendingVisits;
    }

    public void setPendingVisits(List<Visit> pendingVisits) {
        this.pendingVisits = pendingVisits;
    }

    public Long getFlatId() {
        return flatId;
    }

    public void setFlatId(Long flatId) {
        this.flatId = flatId;
    }
}
