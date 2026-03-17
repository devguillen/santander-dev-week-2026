package me.dio.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.dio.domain.model.Proposal;
import me.dio.service.ProposalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/proposals")
@Tag(name = "Job Proposals", description = "Recruiters use this endpoint to send a proposal to the developer")
public class ProposalController {

    private final ProposalService proposalService;

    public ProposalController(ProposalService proposalService) {
        this.proposalService = proposalService;
    }

    @PostMapping("/developer/{developerId}")
    @Operation(summary = "Send a new job proposal to the developer")
    public ResponseEntity<Proposal> createProposal(@PathVariable Long developerId, @RequestBody Proposal proposal) {
        Proposal savedProposal = proposalService.createProposal(developerId, proposal);
        return ResponseEntity.ok(savedProposal);
    }

    @PatchMapping("/{proposalId}/status")
    @Operation(summary = "Update proposal status (ACCEPTED, DECLINED)")
    public ResponseEntity<Proposal> updateProposalStatus(@PathVariable Long proposalId, @RequestParam String status) {
        Proposal updatedProposal = proposalService.updateProposalStatus(proposalId, status);
        return ResponseEntity.ok(updatedProposal);
    }
}
