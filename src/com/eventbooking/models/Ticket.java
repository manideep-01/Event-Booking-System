package com.eventbooking.models;

public class Ticket {
    private Attendee attendee;
    private Event event;
    private String status;

    public Ticket(Attendee attendee, Event event) {
        this.attendee = attendee;
        this.event = event;
        this.status = "Booked";
    }

    public void cancel() {
        this.status = "Cancelled";
        event.setAvailableTickets(event.getAvailableTickets() + 1);
    }

    public String getStatus() {
        return status;
    }

    public String ticketDetails() {
        return "Ticket for: " + event.getTitle() + " | Attendee: " + attendee.name + " | Status: " + status;
    }
}