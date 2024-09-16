package com.example.vgc_project.repository;

import com.example.vgc_project.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket , Integer> {
}
