//package com.outlook.dev.controller;
//
//import com.outlook.dev.auth.AuthHelper;
//import com.outlook.dev.auth.TokenResponse;
//import com.outlook.dev.service.OutlookService;
//import com.outlook.dev.service.OutlookServiceBuilder;
//import com.outlook.dev.service.PagedResult;
//import com.outlook.dev.service.event.Event;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@Controller
//public class EventsController {
//
//    @RequestMapping("/events")
//    public String events(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
//        HttpSession session = request.getSession();
//        TokenResponse tokens = (TokenResponse)session.getAttribute("tokens");
//        if (tokens == null) {
//            // No tokens in session, user needs to sign in
//            redirectAttributes.addFlashAttribute("error", "Please sign in to continue.");
//            return "redirect:/index.html";
//        }
//
//        String tenantId = (String)session.getAttribute("userTenantId");
//
//        tokens = AuthHelper.ensureTokens(tokens, tenantId);
//
//        String email = (String)session.getAttribute("userEmail");
//
////        OutlookService outlookService = OutlookServiceBuilder.getOutlookService(tokens.getAccessToken(), email);
//
//        // Sort by start time in descending order
////        "start/dateTime DESC"
////        "from/emailAddress/address"
//        String sort = "start/dateTime DESC";
//        // Only return the properties we care about
//        String properties = "id,organizer,subject,start,end,body,attendees";
////                "id,subject,bodyPreview,body,start,end,location,attendees, organizer";
////        String id, String subject, String bodyPreview,
////                Body body,
////                DateTimeTimeZone start, DateTimeTimeZone end,
////                Location location, List<Attendee> attendees, Recipient organizer
//        // Return at most 10 events
//        Integer maxResults = 30;
//
//
////        createAppointment(outlookService);
//
//        try {
//            PagedResult<Event> events = outlookService.getEvents(
//                    sort, properties, maxResults)
//                    .execute().body();
//
//            session.setAttribute("events", events);
//
//
//            System.out.println("\n\nREADED:");
////            Arrays.stream(events.getValue()).forEach(System.out::println);
//            System.out.println(events.getValue()[0]);
//            System.out.println("R/\n\n");
//
////            System.out.println("events.getValue()[0].getAttendees().get(0) = "
////                    +events.getValue()[0].getAttendees().get(0));
//
////            EmailAddress emailAddress1 = new EmailAddress("calendar_sync_test@outlook.com",
////                    "Funny Pers");
//
////            events.getValue()[0].getAttendees().get(0).setEmailAddress(emailAddress1);
////            PagedResult<Event> body = outlookService.createEvent(events.getValue()[0])
////                    .execute().body();
//
//
////            System.out.println("\n\nEVENT CREATRE:");
////            Arrays.stream(body.getValue()).forEach(e -> System.out.println(e.getSubject()));
//
//            model.addAttribute("events", events.getValue());
//        } catch (IOException e) {
//            redirectAttributes.addFlashAttribute("error", e.getMessage());
//            return "redirect:/index.html";
//        }
//
//        return "events";
//    }
//
//
////    private void createAppointment(OutlookService outlookService) {
////        try {
////
////            String id = "1";
////            String subject = "Test Event From REST API";
////            Body bodyClnd = new Body("HTML", "Does late morning work for you?");
////
////
////
////            EmailAddress emailAddress = new EmailAddress("Narek", "narek_karapetian@epam.com");
////            Recipient recipient = new Recipient(emailAddress);
//////            LocalDateTime of1 = LocalDateTime.of(2017, 6, 24, 12, 0, 0);
//////            LocalDateTime of2 = LocalDateTime.of(2017, 6, 24, 13, 0, 0);
////
////            Location location = new Location("Harry's Bar");
////
////            EmailAddress emailAddress1 = new EmailAddress("narek_karapetian@epam.com", "Funny Pers");
////            Attendee attendee = new Attendee(emailAddress1,"required");
////
////            List<Attendee> attendees = new ArrayList<>();
////            attendees.add(attendee);
////
////            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'hh:MM:ss");
////            String format1 = format.format(new Date());
////            String format2 = format.format(new Date());
////
////            DateTimeTimeZone dtz1 = new DateTimeTimeZone("2017-06-24T12:00:00",
////                    "Pacific Standard Time");
////            DateTimeTimeZone dtz2 = new DateTimeTimeZone("2017-06-24T14:00:00",
////                    "Pacific Standard Time");
////
////
////            // Это РАБОТАЕТ МАТЬ ЕГО!!!!!!!!
////            Event event = new Event(
//////                    id,
////                    subject,
////                    bodyClnd,
////                    dtz1,
////                    dtz2,
////                    location,
////                    attendees
//////                    recipient
////            );
////
////            PagedResult<Event> body = outlookService.createEvent(event)
////                    .execute().body();
////
////            System.out.println("\n\nEVENT CREATRE:");
////            Arrays.stream(body.getValue()).forEach(e -> System.out.println(e.getSubject()));
////
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////    }
//
////
////    @RequestMapping("/create")
////    public String create(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
////        System.out.println("\n\n\nCREATE:\n\n\n");
////        HttpSession session = request.getSession();
////        TokenResponse tokens = (TokenResponse)session.getAttribute("tokens");
////        if (tokens == null) {
////            // No tokens in session, user needs to sign in
////            redirectAttributes.addFlashAttribute("error", "Please sign in to continue.");
////            return "redirect:/index.html";
////        }
////
////        String tenantId = (String)session.getAttribute("userTenantId");
////
////        tokens = AuthHelper.ensureTokens(tokens, tenantId);
////
////        String email = (String)session.getAttribute("userEmail");
////
////        OutlookService outlookService = OutlookServiceBuilder.getOutlookService(tokens.getAccessToken(), email);
////
////        try {
////
////
////
//////            PagedResult<Event> events = outlookService.getEvents(
//////                    sort, properties, maxResults)
//////                    .execute().body();
//////            model.addAttribute("events", events.getValue());
////        } catch (IOException e) {
////            redirectAttributes.addFlashAttribute("error", e.getMessage());
////            return "redirect:/index.html";
////        }
////
////        return "events";
////    }
//}