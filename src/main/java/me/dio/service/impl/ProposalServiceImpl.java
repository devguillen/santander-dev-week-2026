package me.dio.service.impl;

import me.dio.controller.exception.NotFoundException;
import me.dio.domain.model.Developer;
import me.dio.domain.model.Proposal;
import me.dio.domain.model.ProposalStatus;
import me.dio.domain.repository.ProposalRepository;
import me.dio.service.DeveloperService;
import me.dio.service.ProposalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProposalServiceImpl implements ProposalService {

    private final ProposalRepository proposalRepository;
    private final DeveloperService developerService;

    public ProposalServiceImpl(ProposalRepository proposalRepository, DeveloperService developerService) {
        this.proposalRepository = proposalRepository;
        this.developerService = developerService;
    }

    @Override
    @Transactional
    public Proposal createProposal(Long developerId, Proposal proposal) {
        Developer dev = developerService.findById(developerId);
        proposal.setStatus(ProposalStatus.PENDING);
        dev.getProposals().add(proposal);
        // Ao salvar o Developer com Cascade.ALL, a proposal também é salva, mas podemos salvar direto também.
        Proposal savedProposal = proposalRepository.save(proposal);
        return savedProposal;
    }

    @Override
    @Transactional
    public Proposal updateProposalStatus(Long proposalId, String status) {
        Proposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> new NotFoundException("Proposal not found"));
        
        try {
            proposal.setStatus(ProposalStatus.valueOf(status.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid status. Supported values: PENDING, ACCEPTED, DECLINED");
        }
        
        return proposalRepository.save(proposal);
    }
}
