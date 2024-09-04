package com.example.bmsbookticket.services;

import com.example.bmsbookticket.exceptions.InvalidShowSeatException;
import com.example.bmsbookticket.exceptions.SeatUnavailableException;
import com.example.bmsbookticket.exceptions.UserNotFoundException;
import com.example.bmsbookticket.models.*;
import com.example.bmsbookticket.repositories.ShowSeatRepository;
import com.example.bmsbookticket.repositories.TicketRepository;
import com.example.bmsbookticket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShowSeatRepository showSeatRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public Ticket bookTicket(List<Integer> showSeatIds, int userId) throws UserNotFoundException, SeatUnavailableException, InvalidShowSeatException {
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User Not Found"));
        List<ShowSeat> showSeats = showSeatRepository.findByIdIn(showSeatIds);
        if(showSeats.isEmpty())
            throw new SeatUnavailableException("No Seat is Selected");
        Show show = showSeats.get(0).getShow();
        for(ShowSeat showSeat:showSeats)
            if(!showSeat.getShow().equals(show))
                throw new InvalidShowSeatException("Invalid ShowSeat id");

        List<ShowSeat> availableShowSeats = new ArrayList<>();
        List<Integer> unavailableSeatIds = new ArrayList<>();
        for(ShowSeat showSeat:showSeats){
            if(showSeat.getStatus().equals(SeatStatus.AVAILABLE)){
                showSeat.setStatus(SeatStatus.BLOCKED);
                availableShowSeats.add(showSeat);
            }
            else
                unavailableSeatIds.add(showSeat.getSeat().getId());
        }
        if(unavailableSeatIds.size()>0)
            throw new SeatUnavailableException("Seats are not available."+unavailableSeatIds);

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setStatus(TicketStatus.UNPAID);
        ticket.setSeats(showSeatRepository.saveAll(availableShowSeats).stream().map(showSeat -> showSeat.getSeat()).collect(Collectors.toList()));
        ticket.setShow(show);
        ticket.setTimeOfBooking(new Date());

        return ticketRepository.save(ticket);
    }
}
