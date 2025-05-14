package com.eventbooking.models;

import com.eventbooking.annotations.RoleRequired;
import java.util.Iterator;
import java.util.List;

@RoleRequired(role = "Admin")
public class Admin extends User {
    public Admin(String id, String name) {
        super(id, name);
    }

    public void removeEvent(List<Event> events, String title) {
        Iterator<Event> iterator = events.iterator();
        while (iterator.hasNext()) {
            Event e = iterator.next();
            if (e.getTitle().equalsIgnoreCase(title)) {
                iterator.remove();
                System.out.println("Event \"" + title + "\" removed.");
                return;
            }
        }
        System.out.println("Event not found.");
    }

    @Override
    public void showProfile() {
        System.out.println("Admin ID: " + id + ", Name: " + name);
    }
}