package com.eventbooking.services;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.eventbooking.exceptions.InvalidBookingException;
import com.eventbooking.models.*;

public class EventBookingSystem {
    private List<Attendee> attendees = new ArrayList<>();
    private List<Organizer> organizers = new ArrayList<>();
    private List<Event> events = new ArrayList<>();
    private List<Ticket> tickets = new ArrayList<>();

    public void registerUser(User user){
        if(user instanceof Attendee){
            attendees.add((Attendee) user);
        }
        else if(user instanceof Organizer){
            organizers.add((Organizer) user);
        }
    }

    public void addEvent(Event event){
        events.add(event);
    }

    public void bookTicket(Attendee attendee, String eventTitle) throws InvalidBookingException {
        for(Event e : events){
            if(e.getTitle().equalsIgnoreCase(eventTitle)){
                if(e.isAvailable()){
                    e.setAvailableTickets(e.getAvailableTickets() - 1);
                    Ticket ticket = new Ticket(attendee, e);
                    tickets.add(ticket);
                    System.out.println("Ticket booked successfully !!");
                    return;
                }else{
                    throw new InvalidBookingException("No tickets available !!");
                }
            }
        }
        throw new InvalidBookingException("Event not found !!");
    }

    public void saveEvents(){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("events.ser"))) {
            out.writeObject(events);
            System.out.println("Events saved.");
        } catch (IOException e) {
            System.out.println("Failed to save events.");
        }
    }

    public void loadEvents(){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("events.ser"))) {
            events = (List<Event>) in.readObject();
            System.out.println("Events loaded.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No previous events found or failed to load.");
        }
    }

    public void showEvents(){
        for(Event e : events){
            System.out.println(e);
        }
    }
}
