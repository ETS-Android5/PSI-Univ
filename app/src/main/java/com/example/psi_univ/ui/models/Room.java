package com.example.psi_univ.ui.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Spliterator;

public class Room {
    private final String roomName;
    private final List<Event> events;

    public Room(String roomName, List<Event> events) {
        this.roomName = roomName;
        this.events = events;
        events.sort(Comparator.comparing(Event::getStart)); //TODO: try to implement binary search
    }

    public boolean isAvailableAt(Calendar d) {
        for (Event e : events) {
            if (e.isOverlapping(d)) {
                return false;
            }
            if(e.getStart().after(d)){
                return true;
            }
        }
        return true;
    }

    public String getRoomName() {
        return roomName;
    }

    public Calendar getNextEvent() {
        Calendar d = Calendar.getInstance();
        for(Event e : events){
            if(e.getStart().after(d)){
                return e.getStart();
            }
        }
        return null;
    }

    public static List<Event> dummyEvents() {//TODO remove this
        List<Event> dummy = new ArrayList<>();
        for(int i = 0; i < 24; i+=2) {
            Calendar start = Calendar.getInstance();
            start.set(2022, 5, 24, i, 0);
            Calendar end = Calendar.getInstance();
            end.set(2022, 5, 24, i+2, 0);
            dummy.add(new Event(start, end));
        }
        return dummy;
    }
}
