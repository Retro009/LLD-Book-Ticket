package com.example.bmsbookticket.services;

import com.example.bmsbookticket.exceptions.InvalidShowSeatException;
import com.example.bmsbookticket.exceptions.SeatUnavailableException;
import com.example.bmsbookticket.exceptions.UserNotFoundException;
import com.example.bmsbookticket.models.Ticket;

import java.util.List;

public interface TicketService {
    public Ticket bookTicket(List<Integer> showSeatIds, int userId) throws UserNotFoundException, InvalidShowSeatException, SeatUnavailableException;

}
