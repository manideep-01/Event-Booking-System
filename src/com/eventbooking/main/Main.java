package com.eventbooking.main;

import com.eventbooking.exceptions.InvalidBookingException;
import com.eventbooking.models.Attendee;
import com.eventbooking.models.Organizer;
import com.eventbooking.models.Event;
import com.eventbooking.services.EventBookingSystem;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        EventBookingSystem service = new EventBookingSystem();
        Scanner sc = new Scanner(System.in);

        service.loadEvents();

        while (true){
            System.out.println("-----Event Booking System Menu-----");
            System.out.println("1. Register Attendee");
            System.out.println("2. Register Organizer");
            System.out.println("3. Add Events");
            System.out.println("4. Book Tickets");
            System.out.println("5. Show Events");
            System.out.println("6. Save and Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            try{
                switch (choice){
                    case 1:
                        System.out.print("Enter ID: ");
                        String id1 = sc.nextLine();
                        System.out.print("Enter Name: ");
                        String name1 = sc.nextLine();
                        service.registerUser(new Attendee(id1, name1));
                        break;

                    case 2:
                        System.out.print("Enter ID: ");
                        String id2 = sc.nextLine();
                        System.out.print("Enter Name: ");
                        String name2 = sc.nextLine();
                        service.registerUser(new Organizer(id2, name2)); 
                        break;

                    case 3:
                        System.out.print("Enter Event Title: ");
                        String title = sc.nextLine();
                        System.out.print("Enter Tickets: ");
                        int count = sc.nextInt();
                        sc.nextLine();
                        service.addEvent(new Event(title, count));
                        break;

                    case 4:
                        System.out.print("Enter Attendee ID: ");
                        String attendeeId = sc.nextLine();
                        System.out.print("Enter Name: ");
                        String attendeeName = sc.nextLine();
                        System.out.print("Enter Event Title: ");
                        String eventTitle = sc.nextLine();
                        service.bookTicket(new Attendee(attendeeId, attendeeName), eventTitle);
                        break;

                    case 5:
                        service.showEvents();
                        break;

                    case 6:
                        service.saveEvents();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (InvalidBookingException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
