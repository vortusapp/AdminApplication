package nz.vortus.adapters.xeroAuth;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.MemoryDataStoreFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

public class AuthorizationCodeFlowFactory {
    public AuthorizationCodeFlow create(String clientId, String clientSecret, String tokenServerUrl, String authorizationServerUrl) throws IOException {
        NetHttpTransport HTTP_TRANSPORT = new NetHttpTransport();
        JsonFactory JSON_FACTORY = new GsonFactory();
        DataStoreFactory DATA_STORE_FACTORY = new MemoryDataStoreFactory();

        return new AuthorizationCodeFlow.Builder(
                BearerToken.authorizationHeaderAccessMethod(),
                HTTP_TRANSPORT,
                JSON_FACTORY,
                new GenericUrl(tokenServerUrl),
                new ClientParametersAuthentication(clientId, clientSecret),
                clientId,
                authorizationServerUrl
        )
                .setScopes(getScopes()).setDataStoreFactory(DATA_STORE_FACTORY).build();
    }

    private Collection<String> getScopes() {
        return Arrays.asList("openid", "email", "profile", "offline_access", "accounting.settings",
                "accounting.transactions", "accounting.contacts", "accounting.journals.read",
                "accounting.reports.read", "accounting.attachments");
    }
}

