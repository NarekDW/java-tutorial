package com.outlook.dev.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.util.Base64;
import java.util.Date;


@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdToken {
	// NOTE: This is just a subset of the claims returned in the
	// ID token. For a full listing, see:
	// https://azure.microsoft.com/en-us/documentation/articles/active-directory-v2-tokens/#idtokens
	@JsonProperty("exp")
	private long expirationTime;
	@JsonProperty("nbf")
	private long notBefore;
	@JsonProperty("tid")
	private String tenantId;
	private String nonce;
	private String name;
	private String email;
	@JsonProperty("preferred_username")
	private String preferredUsername;
	@JsonProperty("oid")
	private String objectId;
	
	public static IdToken parseEncodedToken(String encodedToken, String nonce) {
		// Encoded token is in three parts, separated by '.'
		String[] tokenParts = encodedToken.split("\\.");
		
		// The three parts are: header.token.signature
		String idToken = tokenParts[1];
		
		byte[] decodedBytes = Base64.getUrlDecoder().decode(idToken);
		
		ObjectMapper mapper = new ObjectMapper();
		IdToken newToken = null;
		try {
			newToken = mapper.readValue(decodedBytes, IdToken.class);
			if (!newToken.isValid(nonce)) {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return newToken;
	}

	private Date getUnixEpochAsDate(long epoch) {
		// Epoch timestamps are in seconds,
		// but Jackson converts integers as milliseconds.
		// Rather than create a custom deserializer, this helper will do 
		// the conversion.
		return new Date(epoch * 1000);
	}
	
	private boolean isValid(String nonce) {
		// This method does some basic validation
		// For more information on validation of ID tokens, see
		// https://azure.microsoft.com/en-us/documentation/articles/active-directory-v2-tokens/#validating-tokens
		Date now = new Date();
		
		// Check expiration and not before times
		if (now.after(this.getUnixEpochAsDate(this.expirationTime)) ||
				now.before(this.getUnixEpochAsDate(this.notBefore))) {
			// Token is not within it's valid "time"
			return false;
		}
		
		// Check nonce
		return nonce.equals(this.getNonce());
	}

}
