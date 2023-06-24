package nz.vortus.adapters.xeroAuth;


import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.api.client.auth.oauth2.RefreshTokenRequest;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.auth.oauth2.TokenResponseException;
import com.google.api.client.http.BasicAuthentication;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.xero.api.ApiClient;
import io.github.cdimascio.dotenv.Dotenv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class TokenRefresh {
    final static Logger logger = LoggerFactory.getLogger(TokenRefresh.class);
    final ApiClient defaultClient = new ApiClient();
    private final Dotenv dotEnv = Dotenv.load();
    final String clientId = dotEnv.get("XERO_CLIENT_ID");
    final String clientSecret = dotEnv.get("XERO_CLIENT_SECRET");
    final String TOKEN_SERVER_URL = dotEnv.get("XERO_TOKEN_URL");

    public TokenRefresh() {
        super();
    }


    public String checkToken(String accessToken, String refreshToken) {
        String currToken = null;

        try {
            DecodedJWT jwt = JWT.decode(accessToken);

            if (jwt.getExpiresAt().getTime() > System.currentTimeMillis()) {
                System.out.println("Refresh Token : NOT NEEDED - return current token");
                currToken = accessToken;
            } else {
               logger.info("Refresh Token : BEGIN");
                try {
                    TokenResponse tokenResponse = new RefreshTokenRequest(new NetHttpTransport(), new GsonFactory(),
                            new GenericUrl(TOKEN_SERVER_URL), refreshToken)
                            .setClientAuthentication(new BasicAuthentication(this.clientId, this.clientSecret))
                            .execute();
                    logger.info("Refresh Token : SUCCESS");

                    try {
                        DecodedJWT verifiedJWT = defaultClient.verify(tokenResponse.getAccessToken());

                        // DEMO PURPOSE ONLY - You'll need to implement your own token nz.vortus.resources.storage solution
                        TokenStorage store = new TokenStorage();
                        store.saveItem("token_set", tokenResponse.toPrettyString());
                        store.saveItem("access_token", verifiedJWT.getToken());
                        store.saveItem("refresh_token", tokenResponse.getRefreshToken());
                        store.saveItem("expires_in_seconds", tokenResponse.getExpiresInSeconds().toString());

                        currToken = verifiedJWT.getToken();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (TokenResponseException e) {
                    logger.error("Refresh Token : EXCEPTION");
                    if (e.getDetails() != null) {
                        logger.error("Error: " + e.getDetails().getError());
                        if (e.getDetails().getErrorDescription() != null) {
                            logger.error(e.getDetails().getErrorDescription());
                        }
                        if (e.getDetails().getErrorUri() != null) {
                            logger.error(e.getDetails().getErrorUri());
                        }
                    } else {
                        logger.error("Refresh Token : EXCEPTION");
                        logger.error(e.getMessage());
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        } catch (JWTDecodeException exception) {
            logger.error("Refresh Token : INVALID TOKEN");
            logger.error(exception.getMessage());

        }

        return currToken;
    }
}

