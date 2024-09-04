package com.example.bmsbookticket.controllers;

import com.example.bmsbookticket.dtos.BookTicketRequestDTO;
import com.example.bmsbookticket.dtos.BookTicketResponseDTO;
import com.example.bmsbookticket.dtos.ResponseStatus;
import com.example.bmsbookticket.exceptions.InvalidShowSeatException;
import com.example.bmsbookticket.exceptions.SeatUnavailableException;
import com.example.bmsbookticket.exceptions.UserNotFoundException;
import com.example.bmsbookticket.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TicketController {

    @Autowired
    private TicketService ticketService;
    public BookTicketResponseDTO bookTicket(BookTicketRequestDTO requestDTO){
        BookTicketResponseDTO responseDto = new BookTicketResponseDTO();
        try {
            responseDto.setTicket(ticketService.bookTicket(requestDTO.getShowSeatIds(), requestDTO.getUserId()));
            responseDto.setStatus(ResponseStatus.SUCCESS);
        } catch (UserNotFoundException | InvalidShowSeatException | SeatUnavailableException e) {
            responseDto.setStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
