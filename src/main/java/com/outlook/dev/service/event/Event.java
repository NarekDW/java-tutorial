package com.outlook.dev.service.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {

    private String id;
    private String subject;
    private Recipient organizer;
    private DateTimeTimeZone start;
    private DateTimeTimeZone end;
    private Body body;
    private List<Attendee> attendees;

    @Override
    public String toString(){
        return "\nid = "+id+"\n" +
                "subject = "+subject+"\n" +
                "organizer = "+organizer+"\n" +
                "start = "+start.getDateTime()+"\n" +
                "end = "+end.getDateTime()+"\n" +
                "body = "+body.getContentType()+"\n" +
                "attendees = "+attendees.get(0)+"\n";
    }



//    private String id;
//    private String subject;
//    private String bodyPreview;
//    private Body body;
//    private DateTimeTimeZone start;
//    private DateTimeTimeZone end;
//    private Location location;
//    List<Attendee> attendees;
//    private Recipient organizer;


//    public Event(String id, String subject, String bodyPreview,
//                 Body body,
//                 DateTimeTimeZone start, DateTimeTimeZone end,
//                 Location location, List<Attendee> attendees, Recipient organizer) {
//        this.id = id;
//        this.subject = subject;
//        this.bodyPreview = bodyPreview;
//        this.body = body;
//        this.start = start;
//        this.end = end;
//        this.location = location;
//        this.attendees = attendees;
//        this.organizer = organizer;
//    }

//    public Event(String subject, Body body,
//                 DateTimeTimeZone start, DateTimeTimeZone end,
//                 Location location, List<Attendee> attendees) {
//        this.subject = subject;
//        this.body = body;
//        this.start = start;
//        this.end = end;
//        this.location = location;
//        this.attendees = attendees;
//    }
}