package com.outlook.dev.controller;

import com.outlook.dev.service.UserRESTService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class MailController {

	@RequestMapping("/mail")
	public String mail(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {


		String accessToken = (String) request.getSession().getAttribute("accessToken");
		System.out.println("\n\n\naccessToken = "+accessToken+"\n\n\n");
		UserRESTService rest = new UserRESTService();
		String s = rest.get(accessToken);
		System.out.println("\n\n"+"MY CONTENT = "+s+"\n\n");




//		PagedResult<Event> events = (PagedResult<Event>) request.getSession().getAttribute("events");
//		String accessToken2 = (String) request.getSession().getAttribute("accessToken2");
//		System.out.println("\n\naccessToken2 = "+accessToken2+"\n\n");
//
//		OutlookService outlookService = OutlookServiceBuilder.getOutlookService(accessToken2, null);
//		System.out.println("events.getValue()[0]2 = "+events.getValue()[0]);
//		outlookService.createEvent(events.getValue()[0]).execute().body();








//		HttpSession session = request.getSession();
//		TokenResponse tokens = (TokenResponse)session.getAttribute("tokens");
//		if (tokens == null) {
//			// No tokens in session, user needs to sign in
//		ё 	redirectAttributes.addFlashAttribute("error", "Please sign in to continue.");
//			return "redirect:/index.html";
//		}
//
//		String tenantId = (String)session.getAttribute("userTenantId");
//
//		tokens = AuthHelper.ensureTokens(tokens, tenantId);
//
//		String email = (String)session.getAttribute("userEmail");
//
//		OutlookService outlookService = OutlookServiceBuilder.getOutlookService(tokens.getAccessToken(), email);
//
//		// Retrieve messages from the inbox
//		String folder = "inbox";
//		// Sort by time received in descending order
//		String sort = "receivedDateTime DESC";
//		// Only return the properties we care about
//		String properties = "receivedDateTime,from,isRead,subject,bodyPreview";
//		// Return at most 10 messages
//		Integer maxResults = 10;
//
//		try {
//			PagedResult<Message> messages = outlookService.getMessages(
//					folder, sort, properties, maxResults)
//					.execute().body();
//			model.addAttribute("messages", messages.getValue());
//
//
//			System.out.println("\n\nSubject:");
//			Arrays.stream(messages.getValue()).forEach(message -> System.out.println(message.getSubject()));
//
//
//		} catch (IOException e) {
//			redirectAttributes.addFlashAttribute("error", e.getMessage());
//			return "redirect:/index.html";
//		}

		return "mail";
	}
}
