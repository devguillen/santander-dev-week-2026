package me.dio.service;

import me.dio.domain.model.Proposal;

public interface ProposalService {
    Proposal createProposal(Long developerId, Proposal proposal);
    Proposal updateProposalStatus(Long proposalId, String status);
}
