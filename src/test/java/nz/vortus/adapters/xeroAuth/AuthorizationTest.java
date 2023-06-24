package nz.vortus.adapters.xeroAuth;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import io.github.cdimascio.dotenv.Dotenv;
import nz.vortus.adapters.xeroAuth.Authorization;
import nz.vortus.adapters.xeroAuth.AuthorizationCodeFlowFactory;
import nz.vortus.adapters.xeroAuth.TokenStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthorizationTest {

    @Mock
    private AuthorizationCodeFlow flow;

    @Mock
    private TokenStorage tokenStorage;

    @Mock
    private AuthorizationCodeFlowFactory flowFactory;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private AuthorizationCodeRequestUrl authorizationCodeRequestUrl;


    private Authorization servlet;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        servlet = new Authorization(flow, "secretState", tokenStorage);
        servlet.init();
    }



    @Test
    void testDoGet() throws IOException {
        when(flow.newAuthorizationUrl()).thenReturn(authorizationCodeRequestUrl);
        when(authorizationCodeRequestUrl.setClientId(anyString())).thenReturn(authorizationCodeRequestUrl);
        when(authorizationCodeRequestUrl.setState(anyString())).thenReturn(authorizationCodeRequestUrl);
        when(authorizationCodeRequestUrl.setRedirectUri(anyString())).thenReturn(authorizationCodeRequestUrl);
        when(authorizationCodeRequestUrl.build()).thenReturn("http://localhost");
        servlet.doGet(request, response);
        verify(tokenStorage).saveItem(any(), any(), any());
        verify(response).sendRedirect(anyString());
    }

    @Test
    void testGetClientId() {
        String clientId = servlet.getClientId();
        assert clientId.equals(Dotenv.load().get("XERO_CLIENT_ID"));
    }

    @Test
    void testGetClientSecret() {
        String clientSecret = servlet.getClientSecret();
        assert clientSecret.equals(Dotenv.load().get("XERO_CLIENT_SECRET"));
    }

    @Test
    void testGetTokenServerUrl() {
        String tokenServerUrl = servlet.getTokenServerUrl();
        assert tokenServerUrl.equals(Dotenv.load().get("XERO_TOKEN_URL"));
    }

    @Test
    void testGetAuthorizationServerUrl() {
        String authServerUrl = servlet.getAuthorizationServerUrl();
        assert authServerUrl.equals(Dotenv.load().get("XERO_AUTHORIZATION_URL"));
    }

    @Test
    void testGetRedirectUri() {
        String redirectUri = servlet.getRedirectUri();
        assert redirectUri.equals(Dotenv.load().get("XERO_REDIRECT_URI"));
    }
}
