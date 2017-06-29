package com.outlook.dev.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

public class OutlookServiceBuilder {

	public static OutlookUser getOutlookService(String accessToken, String userEmail) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.set("User-Agent", "EPM-LSTR-CLND");
		headers.set("client-request-id", UUID.randomUUID().toString());
		headers.set("return-client-request-id", "true");
		headers.set("Authorization", String.format("Bearer %s", accessToken));
//		Optional.of(userEmail).ifPresent(mail -> headers.set("X-AnchorMailbox", mail));

		HttpEntity<OutlookUser> entity = new HttpEntity<OutlookUser>(null, headers);

		URI uri = UriComponentsBuilder.newInstance()
				.scheme("https").host("graph.microsoft.com")
				.path("/v1.0/me").build()
				.toUri();

// TODO: 29.06.2017  //        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(null, headers);

		ResponseEntity<OutlookUser> exchange = restTemplate.exchange(uri, HttpMethod.GET, entity, OutlookUser.class);

		return exchange.getBody();





























		// Create a request interceptor to add headers that belong on
		// every request
//		Interceptor requestInterceptor = new Interceptor() {
//			@Override
//			public Response intercept(Interceptor.Chain chain) throws IOException {
//				Request original = chain.request();
//				Builder builder = original.newBuilder()
//						.header("User-Agent", "java-tutorial")
//						.header("client-request-id", UUID.randomUUID().toString())
//						.header("return-client-request-id", "true")
//						.header("Authorization", String.format("Bearer %s", accessToken))
//						.method(original.method(), original.body());
//
//				if (userEmail != null && !userEmail.isEmpty()) {
//					builder = builder.header("X-AnchorMailbox", userEmail);
//				}
//
//				Request request = builder.build();
//				return chain.proceed(request);
//			}
//		};
//
//		// Create a logging interceptor to log request and responses
//		HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//		loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//		OkHttpClient client = new OkHttpClient.Builder()
//				.addInterceptor(requestInterceptor)
//				.addInterceptor(loggingInterceptor)
//				.build();
//
//		// Create and configure the Retrofit object
//		Retrofit retrofit = new Retrofit.Builder()
//				.baseUrl("https://graph.microsoft.com")
//				.client(client)
//				.addConverterFactory(JacksonConverterFactory.create())
//				.build();
//
//		// Generate the token service
//		return retrofit.create(OutlookService.class);
	}
}
