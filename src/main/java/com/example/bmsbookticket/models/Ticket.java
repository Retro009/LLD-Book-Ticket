package com.example.bmsbookticket.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Ticket extends BaseModel{
    @ManyToOne
    private Show show;
    @OneToMany
    List<Seat> seats;
    private Date timeOfBooking;
    @ManyToOne
    private User user;
    @Enumerated(EnumType.ORDINAL)
    private TicketStatus status;
}
