package com.outlook.dev.controller;

import com.outlook.dev.auth.AuthHelper;
import com.outlook.dev.auth.IdToken;
import com.outlook.dev.auth.TokenResponse;
import com.outlook.dev.service.OutlookServiceBuilder;
import com.outlook.dev.service.OutlookUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class AuthorizeController {

    private int i=0;


    @RequestMapping(value = "/authorize", method = RequestMethod.POST)
    public String authorize(
            @RequestParam("code") String code, // authCode
            @RequestParam("id_token") String idToken,
            @RequestParam("state") UUID state,
            HttpServletRequest request) {
        // Get the expected state value from the session
        HttpSession session = request.getSession();
        UUID expectedState = (UUID) session.getAttribute("expected_state");
        UUID expectedNonce = (UUID) session.getAttribute("expected_nonce");

        // Make sure that the state query parameter returned matches
        // the expected state
        if (state.equals(expectedState)) {


            IdToken idTokenObj = IdToken.parseEncodedToken(idToken, expectedNonce.toString());
            if (idTokenObj != null) {
                TokenResponse tokenResponse;
                if(i==0){



                    tokenResponse = AuthHelper.getTokenFromAuthCode(code, idTokenObj.getTenantId());
                    System.out.println("\n\n\ntokenResponse access = "
                            +tokenResponse.getAccessToken()
                            +"\n\n\n"
                    );

                    AuthHelper.ensureTokens(tokenResponse, idTokenObj.getTenantId());


                    session.setAttribute("tokens", tokenResponse);
                    session.setAttribute("accessToken", tokenResponse.getAccessToken());
                    session.setAttribute("userConnected", true);
                    session.setAttribute("userName", idTokenObj.getName());
                    session.setAttribute("userTenantId", idTokenObj.getTenantId());
                    i++;

                } else {
                    tokenResponse = AuthHelper.getTokenFromAuthCode(code, idTokenObj.getTenantId());
                    session.setAttribute("tokens2", tokenResponse);
                    session.setAttribute("accessToken2", tokenResponse.getAccessToken());
                    session.setAttribute("userConnected2", true);
                    session.setAttribute("userName2", idTokenObj.getName());
                    session.setAttribute("userTenantId2", idTokenObj.getTenantId());

                }
                // Get user info
//                OutlookService outlookService = OutlookServiceBuilder.getOutlookService(tokenResponse.getAccessToken(), null);
                OutlookUser user = OutlookServiceBuilder.getOutlookService(tokenResponse.getAccessToken(), null);

                System.out.println("\n\n\nuser = "+user+"\n\n\n");
                session.setAttribute("userEmail", user.getMail());
//                try {
////                    user = outlookService.getCurrentUser().execute().body();
//
//                } catch (IOException e) {
//                    session.setAttribute("error", e.getMessage());
//                }

            } else {
                session.setAttribute("error", "ID token failed validation.");
            }


        } else {
            session.setAttribute("error", "Unexpected state returned from authority.");
        }
        return "mail";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/index.html";
    }


//	@RequestMapping(value="/authorize", method=RequestMethod.POST)
//	public String authorize(
//			@RequestParam("code") String code,
//			@RequestParam("id_token") String idToken,
//			@RequestParam("state") UUID state,
//			HttpServletRequest request) {
//		// Get the expected state value from the session
//		HttpSession session = request.getSession();
//		UUID expectedState = (UUID) session.getAttribute("expected_state");
//		UUID expectedNonce = (UUID) session.getAttribute("expected_nonce");
//		session.removeAttribute("expected_state");
//		session.removeAttribute("expected_nonce");
//
//		// Make sure that the state query parameter returned matches
//		// the expected state
//		if (state.equals(expectedState)) {
//			IdToken idTokenObj = IdToken.parseEncodedToken(idToken, expectedNonce.toString());
//			if (idTokenObj != null) {
//				TokenResponse tokenResponse = AuthHelper.getTokenFromAuthCode(code, idTokenObj.getTenantId());
//				session.setAttribute("tokens", tokenResponse);
//				session.setAttribute("userConnected", true);
//				session.setAttribute("userName", idTokenObj.getName());
//				session.setAttribute("userTenantId", idTokenObj.getTenantId());
//				// Get user info
//				OutlookService outlookService = OutlookServiceBuilder.getOutlookService(tokenResponse.getAccessToken(), null);
//				OutlookUser user;
//				try {
//					user = outlookService.getCurrentUser().execute().body();
//					session.setAttribute("userEmail", user.getMail());
//				} catch (IOException e) {
//					session.setAttribute("error", e.getMessage());
//				}
//			} else {
//				session.setAttribute("error", "ID token failed validation.");
//			}
//		} else {
//			session.setAttribute("error", "Unexpected state returned from authority.");
//		}
//		return "redirect:/mail.html";
//	}


}
