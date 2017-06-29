package com.outlook.dev.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenResponse {
    @JsonProperty("token_type")
    private String tokenType;
    private String scope;
    @JsonProperty("expires_in")
    private int expiresIn;
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("id_token")
    private String idToken;
    private String error;
    @JsonProperty("error_description")
    private String errorDescription;
    @JsonProperty("error_codes")
    private int[] errorCodes;
    private Date expirationTime;

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
        Calendar now = Calendar.getInstance();
        now.add(Calendar.SECOND, expiresIn);
        this.expirationTime = now.getTime();
    }

    public String toString() {
        return "\ncom.outlook.dev.auth.TokenResponse(" +
                "tokenType=" + this.getTokenType() + "\n" +
                "scope=" + this.getScope() + "\n" +
                "expiresIn=" + this.getExpiresIn() + "\n" +
                "accessToken=" + this.getAccessToken() + "\n" +
                "refreshToken=" + this.getRefreshToken() + "\n" +
                "idToken=" + this.getIdToken() + "\n" +
                "error=" + this.getError() + "\n" +
                "errorDescription=" + this.getErrorDescription() + "\n" +
                "errorCodes=" + java.util.Arrays.toString(this.getErrorCodes()) + "\n" +
                "expirationTime=" + this.getExpirationTime() + ")\n";
    }
}
