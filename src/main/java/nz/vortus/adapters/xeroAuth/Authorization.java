package nz.vortus.adapters.xeroAuth;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import io.github.cdimascio.dotenv.Dotenv;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;

import static java.util.UUID.randomUUID;

@WebServlet("/Authorization")
public class Authorization extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    private AuthorizationCodeFlow flow;
    private final String secretState;
    private final TokenStorage tokenStorage;
    public Authorization(){
        this.flow = null;
        this.secretState = randomUUID().toString();
        this.tokenStorage = new TokenStorage();
    }
    protected Authorization(AuthorizationCodeFlow flow, String secretState, TokenStorage tokenStorage) {
        this.flow = flow;
        this.secretState = secretState;
        this.tokenStorage = tokenStorage;
    }


    public void init() {
    AuthorizationCodeFlowFactory flowFactory = new AuthorizationCodeFlowFactory();
        try {
            this.flow = flowFactory.create(getClientId(), getClientSecret(), getTokenServerUrl(), getAuthorizationServerUrl());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        tokenStorage.saveItem(response, "state", secretState);

        String url = flow.newAuthorizationUrl()
                .setClientId(getClientId())
                .setState(secretState)
                .setRedirectUri(getRedirectUri())
                .build();

        response.sendRedirect(url);
    }
    public String getClientSecret() {
        return Dotenv.load().get("XERO_CLIENT_SECRET");
    }
    public String getClientId() {
        return Dotenv.load().get("XERO_CLIENT_ID");
    }

    public String getRedirectUri() {
        return Dotenv.load().get("XERO_REDIRECT_URI");
    }
    public String getTokenServerUrl() {
        return Dotenv.load().get("XERO_TOKEN_URL");
    }
    public String getAuthorizationServerUrl() {
        return Dotenv.load().get("XERO_AUTHORIZATION_URL");
    }


    public AuthorizationCodeFlow getFlow() {
        return flow;
    }
}