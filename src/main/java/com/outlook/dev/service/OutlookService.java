package com.outlook.dev.service;

import com.outlook.dev.service.event.Event;
import retrofit2.Call;
import retrofit2.http.*;

public interface OutlookService {

	@GET("/v1.0/me")
	Call<OutlookUser> getCurrentUser();

	@GET("/v1.0/me/mailfolders/{folderid}/messages")
	Call<PagedResult<Message>> getMessages(
	  @Path("folderid") String folderId,
	  @Field("$orderby") String orderBy,
	  @Field("$select") String select,
	  @Field("$top") Integer maxResults
	);

	@GET("/v1.0/me/events")
	Call<PagedResult<Event>> getEvents(
	  @Query("$orderby") String orderBy,
	  @Query("$select") String select,
	  @Query("$top") Integer maxResults
	);

	@POST("/v1.0/me/events")
	Call<PagedResult<Event>> createEvent(
			@Body Event event
	);

//	@GET("/v1.0/me/contacts")
//	Call<PagedResult<Contact>> getContacts(
//		@Query("$orderby") String orderBy,
//	  @Query("$select") String select,
//	  @Query("$top") Integer maxResults
//	);
}
