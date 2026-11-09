package com.example.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.test.model.Ticket;
import com.example.test.model.TicketStatus;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // Requête dérivée : Spring génère automatiquement le SQL "SELECT * FROM tickets WHERE status = ?"
    List<Ticket> findByStatus(TicketStatus status);

    List<Ticket> findByAssignedTo(String assignedTo);
}