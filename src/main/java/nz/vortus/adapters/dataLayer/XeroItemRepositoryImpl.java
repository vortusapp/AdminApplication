package nz.vortus.adapters.dataLayer;

import com.google.gson.Gson;
import com.xero.api.ApiClient;
import com.xero.api.XeroException;
import com.xero.api.client.AccountingApi;
import com.xero.models.accounting.Item;
import com.xero.models.accounting.Items;
import nz.vortus.adapters.xeroAuth.TokenRefresh;
import nz.vortus.adapters.xeroAuth.TokenStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.OffsetDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.util.List;
@WebServlet(name = "XeroItemRepositoryImpl", value = "/XeroItemRepositoryImpl")
public class XeroItemRepositoryImpl extends HttpServlet implements XeroItemRepository {
    Logger logger = LoggerFactory.getLogger(XeroItemRepositoryImpl.class);
    private static AccountingApi apiInstance;
    @Serial
    private static final long serialVersionUID = 1L;

    TokenStorage store = new TokenStorage();
    String savedAccessToken;
    String savedRefreshToken;
    String xeroTenantId;

    public XeroItemRepositoryImpl() {
        super();
        savedAccessToken = store.get("access_token");
        savedRefreshToken = store.get("refresh_token");
        xeroTenantId = store.get("xero_tenant_id");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the list of items
        List<Item> items = getItems();

        // Convert the list to JSON
        Gson gson = new Gson();
        String json = gson.toJson(items);
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        // Set the response content type to JSON
        response.setContentType("application/json");

        // Write the JSON response
        response.getWriter().write(json);
    }

    @Override
    public List<Item> getItems() {
        String accessToken = new TokenRefresh().checkToken(savedAccessToken, savedRefreshToken);
        OffsetDateTime ifModifiedSince = OffsetDateTime.parse("1900-02-06T12:17:43.202-08:00");
        String where = "";
        String order = "Code ASC";
        Integer unitDP = 4;
        return fetchItemsFromXero(accessToken, xeroTenantId, ifModifiedSince, where, order, unitDP);
    }

    @Override
    public List<Item> getItemsSince(OffsetDateTime ifModifiedSince) {
        String accessToken = new TokenRefresh().checkToken(savedAccessToken, savedRefreshToken);
        String where = "";
        String order = "Code ASC";
        Integer unitDP = 4;
        return fetchItemsFromXero(accessToken, xeroTenantId, ifModifiedSince, where, order, unitDP);
    }

    @Override
    public List<Item> getSoldItemsSince(OffsetDateTime ifModifiedSince) {
        String accessToken = new TokenRefresh().checkToken(savedAccessToken, savedRefreshToken);
        String where = "IsSold==true";
        String order = "Code ASC";
        Integer unitDP = 4;
        return fetchItemsFromXero(accessToken, xeroTenantId, ifModifiedSince, where, order, unitDP);
    }

    @Override
    public List<Item> getSoldItems(){
        OffsetDateTime ifModifiedSince = OffsetDateTime.parse("1900-02-06T12:17:43.202-08:00");
        return getSoldItemsSince(ifModifiedSince);
    }

    @Override
    public List<Item> getBoughtItemsSince(OffsetDateTime ifModifiedSince) {
        String accessToken = new TokenRefresh().checkToken(savedAccessToken, savedRefreshToken);

        String where = "IsPurchased==true";
        String order = "Code ASC";
        Integer unitDP = 4;
        return fetchItemsFromXero(accessToken, xeroTenantId, ifModifiedSince, where, order, unitDP);
    }

    @Override
    public List<Item> getBoughtItems(){
        OffsetDateTime ifModifiedSince = OffsetDateTime.parse("1900-02-06T12:17:43.202-08:00");
        return getBoughtItemsSince(ifModifiedSince);
    }



    private List<Item> fetchItemsFromXero(String accessToken, String xeroTenantId, OffsetDateTime ifModifiedSince, String where, String order, Integer unitDP) {
        ApiClient defaultClient = new ApiClient();
        apiInstance = AccountingApi.getInstance(defaultClient);
        try {
            Items result = apiInstance.getItems(accessToken, xeroTenantId, ifModifiedSince, where, order, unitDP);
            return result.getItems();
        } catch (XeroException | IOException e) {
            System.err.println("Exception when calling AccountingApi#getItems");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateItem(Item item) {
        // Update the product in the Xero API
    }

}


