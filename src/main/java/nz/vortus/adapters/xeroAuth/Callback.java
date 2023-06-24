package nz.vortus.adapters.xeroAuth;

import java.io.IOException;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.interfaces.DecodedJWT;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.MemoryDataStoreFactory;
import com.xero.api.ApiClient;
import com.xero.api.client.IdentityApi;
import com.xero.models.identity.Connection;
import io.github.cdimascio.dotenv.Dotenv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/Callback")
public class Callback extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(Callback.class);
    @Serial
    private static final long serialVersionUID = 1L;
    private final Dotenv dotEnv = Dotenv.load();
    final String clientId = dotEnv.get("XERO_CLIENT_ID");
    final String clientSecret = dotEnv.get("XERO_CLIENT_SECRET");
    final String redirectURI = dotEnv.get("XERO_REDIRECT_URI");
    final String TOKEN_SERVER_URL = dotEnv.get("XERO_TOKEN_URL");
    final String AUTHORIZATION_SERVER_URL = dotEnv.get("XERO_AUTHORIZATION_URL");
    final NetHttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    final JsonFactory JSON_FACTORY = new GsonFactory();
    final ApiClient defaultClient = new ApiClient();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Callback() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = "123";
        if (request.getParameter("code") != null) {
            code = request.getParameter("code");
        }

        // Retrieve your stored secretState variable
        TokenStorage store = new TokenStorage();
        String secretState =store.get(request, "state");

        // Compare to state prevent CSRF
        if (request.getParameter("state") != null && secretState.equals(request.getParameter("state"))) {

            ArrayList<String> scopeList = new ArrayList<>();
            scopeList.add("openid");
            scopeList.add("email");
            scopeList.add("profile");
            scopeList.add("offline_access");
            scopeList.add("accounting.settings");
            scopeList.add("accounting.transactions");
            scopeList.add("accounting.contacts");
            scopeList.add("accounting.journals.read");
            scopeList.add("accounting.reports.read");
            scopeList.add("accounting.attachments");

            DataStoreFactory DATA_STORE_FACTORY = new MemoryDataStoreFactory();

            AuthorizationCodeFlow flow = new AuthorizationCodeFlow.Builder(BearerToken.authorizationHeaderAccessMethod(),
                    HTTP_TRANSPORT, JSON_FACTORY, new GenericUrl(TOKEN_SERVER_URL),
                    new ClientParametersAuthentication(clientId, clientSecret), clientId, AUTHORIZATION_SERVER_URL)
                    .setScopes(scopeList).setDataStoreFactory(DATA_STORE_FACTORY).build();

            TokenResponse tokenResponse = flow.newTokenRequest(code).setRedirectUri(redirectURI).execute();


            try {
                DecodedJWT verifiedJWT = defaultClient.verify(tokenResponse.getAccessToken());

                ApiClient defaultIdentityClient = new ApiClient("https://api.xero.com", null, null, null, null);
                IdentityApi idApi = new IdentityApi(defaultIdentityClient);
                List<Connection> connection = idApi.getConnections(tokenResponse.getAccessToken(),null);

                store.saveItem(response, "token_set", tokenResponse.toPrettyString());
                store.saveItem(response, "access_token", verifiedJWT.getToken());
                store.saveItem(response, "refresh_token", tokenResponse.getRefreshToken());
                store.saveItem(response, "expires_in_seconds", tokenResponse.getExpiresInSeconds().toString());
                store.saveItem(response, "xero_tenant_id", connection.get(0).getTenantId().toString());

                response.sendRedirect("https://google.com");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            logger.error("Invalid state - possible CSFR");
        }
    }
}