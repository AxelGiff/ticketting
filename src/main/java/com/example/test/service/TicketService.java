package com.example.test.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.test.dto.CreateTicketRequest;
import com.example.test.model.Ticket;
import com.example.test.model.TicketStatus;
import com.example.test.repository.TicketRepository;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Transactional(readOnly = true)
    public List<Ticket> getAll(TicketStatus status) {
        if (status != null) {
            return ticketRepository.findByStatus(status);
        }
        return ticketRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Ticket getById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ticket introuvable avec l'ID: " + id));
    }

    @Transactional
    public Ticket create(CreateTicketRequest req) {
        Ticket ticket = new Ticket(req.title(), req.description());
        return ticketRepository.save(ticket);
    }

    @Transactional
    public Ticket assign(Long id, String agentUsername) {
        Ticket ticket = getById(id);
        ticket.setAssignedTo(agentUsername);
        ticket.setStatus(TicketStatus.IN_PROGRESS);
        return ticketRepository.save(ticket);
    }
}