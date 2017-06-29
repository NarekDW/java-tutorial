package com.outlook.dev.auth;

import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;
import java.util.UUID;

public class AuthHelper {
	private static final String authority = "https://login.microsoftonline.com";
	private static final String authorizeUrl = authority + "/common/oauth2/v2.0/authorize";

	private static String[] scopes = {
			"openid",
			"offline_access",
			"profile",
			"User.Read",
			"Mail.Read",
			"Calendars.ReadWrite"
	};

	private static String appId = null;
	private static String appPassword = null;
	private static String redirectUrl = null;

	private static String getAppId() {
		if (appId == null) {
			try {
				loadConfig();
			} catch (Exception e) {
				return null;
			}
		}
		return appId;
	}
	private static String getAppPassword() {
		if (appPassword == null) {
			try {
				loadConfig();
			} catch (Exception e) {
				return null;
			}
		}
		return appPassword;
	}

	private static String getRedirectUrl() {
		if (redirectUrl == null) {
			try {
				loadConfig();
			} catch (Exception e) {
				return null;
			}
		}
		return redirectUrl;
	}

	private static String getScopes() {
		StringBuilder sb = new StringBuilder();
		for (String scope: scopes) {
			sb.append(scope + " ");
		}
		return sb.toString().trim();
	}

	private static void loadConfig() throws IOException {
		String authConfigFile = "auth.properties";
		InputStream authConfigStream = AuthHelper.class.getClassLoader().getResourceAsStream(authConfigFile);

		if (authConfigStream != null) {
			Properties authProps = new Properties();
			try {
				authProps.load(authConfigStream);
				appId = authProps.getProperty("appId");
				appPassword = authProps.getProperty("appPassword");
				redirectUrl = authProps.getProperty("redirectUrl");
			} finally {
				authConfigStream.close();
			}
		}
		else {
			throw new FileNotFoundException("Property file '" + authConfigFile + "' not found in the classpath.");
		}
	}

	public static String getLoginUrl(UUID state, UUID nonce) {

		UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(authorizeUrl);
		urlBuilder.queryParam("client_id", getAppId());
		urlBuilder.queryParam("redirect_uri", getRedirectUrl());
		urlBuilder.queryParam("response_type", "code id_token");
		urlBuilder.queryParam("scope", getScopes());
		urlBuilder.queryParam("state", state);
		urlBuilder.queryParam("nonce", nonce);
		urlBuilder.queryParam("response_mode", "form_post");

		return urlBuilder.toUriString();
	}


    public static TokenResponse getTokenFromAuthCode(String authCode, String tenantId) {
//		okenService.getAccessTokenFromAuthCode(tenantId, getAppId(), getAppPassword(),
//				"authorization_code", authCode, getRedirectUrl()).execute().body();
		RestTemplate restTemplate = new RestTemplate();
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.scheme("https").host("login.microsoftonline.com")
				.path("/{tenantId}/oauth2/v2.0/token").build()
				.expand(tenantId)
				.encode();
		URI uri = uriComponents.toUri();

//		String url = "https://login.microsoftonline.com/"+tenantId+"/oauth2/v2.0/token";

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", getAppId());
        params.add("client_secret", getAppPassword());
        params.add("grant_type", "authorization_code");
        params.add("code", authCode);
        params.add("redirect_uri", getRedirectUrl());

//		@Field("client_id") String clientId,
//				@Field("client_secret") String clientSecret,
//				@Field("grant_type") String grantType,
//				@Field("code") String code,
//				@Field("redirect_uri") String redirectUrl

//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.ALL);
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, null);
//		JsonNode tokenResponse = restTemplate.postForObject(uri, entity,
//				JsonNode.class
////				params
//		);

//		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		TokenResponse tokenResponse = restTemplate.postForObject(uri, entity, TokenResponse.class);

//		String access_token = tokenResponse.findValue("access_token").asText();
//
//
//		System.out.println("\n\n\naccess_token = "+access_token+"\n\n\n");
		System.out.println("\n\n\n"+tokenResponse+"\n\n\n");

		return tokenResponse;

//		return tokenResponse;





//		restTemplate.getMessageConverters().add(new )


//		Request.Builder builder = new Request.Builder()
//				.header("User-Agent", "java-tutorial")
//				.header("client-request-id", UUID.randomUUID().toString())
//				.header("return-client-request-id", "true")
//				.header("Authorization", String.format("Bearer %s", accessToken))
//				.method(, original.body());
//
//		if (userEmail != null && !userEmail.isEmpty()) {
//			builder = builder.header("X-AnchorMailbox", userEmail);
//		}
//
//		Request request = builder.build();





//		@POST("/{tenantid}/oauth2/v2.0/token")
//		Call<TokenResponse> getAccessTokenFromAuthCode(
//				@Path("tenantid") String tenantId,
//				@Field("client_id") String clientId,
//				@Field("client_secret") String clientSecret,
//				@Field("grant_type") String grantType,
//				@Field("code") String code,
//				@Field("redirect_uri") String redirectUrl
//	);






		// Create a logging interceptor to log request and responses
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(interceptor).build();
//
//        // Create and configure the Retrofit object
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(authority)
//                .client(client)
//                .addConverterFactory(JacksonConverterFactory.create())
//                .build();
//
//        // Generate the token service
//        TokenService tokenService = retrofit.create(TokenService.class);
//
//        try {
//            return tokenService.getAccessTokenFromAuthCode(tenantId, getAppId(), getAppPassword(),
//                    "authorization_code", authCode, getRedirectUrl()).execute().body();
//        } catch (IOException e) {
//            TokenResponse error = new TokenResponse();
//            error.setError("IOException");
//            error.setErrorDescription(e.getMessage());
//            return error;
//        }
    }


    // Проверяет не устарел ли токен, и если устарел то обновляет, если все нормально, то возвращает его же...
	@SuppressWarnings("Duplicates")
	public static TokenResponse ensureTokens(TokenResponse tokens, String tenantId) {

		RestTemplate restTemplate = new RestTemplate();
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.scheme("https").host("login.microsoftonline.com")
				.path("/{tenantId}/oauth2/v2.0/token").build()
				.expand(tenantId)
				.encode();
		URI uri = uriComponents.toUri();

//		@POST("/{tenantid}/oauth2/v2.0/token")
//		Call<TokenResponse> getAccessTokenFromRefreshToken(
//				@Path("tenantid") String tenantId,
//				@Field("client_id") String clientId,
//				@Field("client_secret") String clientSecret,
//				@Field("grant_type") String grantType,
//				@Field("refresh_token") String code,
//				@Field("redirect_uri") String redirectUrl
//	);

//		tenantId, getAppId(), getAppPassword(),
//						"refresh_token", tokens.getRefreshToken(), getRedirectUrl()).execute().body();

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("client_id", getAppId());
		params.add("client_secret", getAppPassword());
		params.add("grant_type", "refresh_token");
		params.add("refresh_token", tokens.getRefreshToken());
		params.add("redirect_uri", getRedirectUrl());

//		@Field("client_id") String clientId,
//				@Field("client_secret") String clientSecret,
//				@Field("grant_type") String grantType,
//				@Field("code") String code,
//				@Field("redirect_uri") String redirectUrl

//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.ALL);
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, null);
//		JsonNode tokenResponse = restTemplate.postForObject(uri, entity,
//				JsonNode.class
////				params
//		);

//		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		TokenResponse tokenResponse = restTemplate.postForObject(uri, entity, TokenResponse.class);

//		String access_token = tokenResponse.findValue("access_token").asText();
//
//
//		System.out.println("\n\n\naccess_token = "+access_token+"\n\n\n");
		System.out.println("\n\n\n2 = \n"+tokenResponse+"\n\n\n");

		return tokenResponse;








		// Are tokens still valid?
//		Calendar now = Calendar.getInstance();
//		if (now.getTime().before(tokens.getExpirationTime())) {
//			// Still valid, return them as-is
//			return tokens;
//		}
//		else {
//			// Expired, refresh the tokens
//			// Create a logging interceptor to log request and responses
//			HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//			interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//			OkHttpClient client = new OkHttpClient.Builder()
//					.addInterceptor(interceptor).build();
//
//			// Create and configure the Retrofit object
//			Retrofit retrofit = new Retrofit.Builder()
//					.baseUrl(authority)
//					.client(client)
//					.addConverterFactory(JacksonConverterFactory.create())
//					.build();
//
//			// Generate the token service
//			TokenService tokenService = retrofit.create(TokenService.class);
//
//			try {
//				return tokenService.getAccessTokenFromRefreshToken(tenantId, getAppId(), getAppPassword(),
//						"refresh_token", tokens.getRefreshToken(), getRedirectUrl()).execute().body();
//			} catch (IOException e) {
//				TokenResponse error = new TokenResponse();
//				error.setError("IOException");
//				error.setErrorDescription(e.getMessage());
//				return error;
//			}
//		}
	}

}