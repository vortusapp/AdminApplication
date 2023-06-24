package nz.vortus.adapters.servlet;
import nz.vortus.adapters.dataLayer.XeroItemRepositoryImpl;
import nz.vortus.businessLogic.InventoryService;
import com.google.gson.Gson;
import com.xero.models.accounting.Item;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
@WebServlet("/inventory")
public class InventoryServlet extends HttpServlet {

    private  InventoryService inventoryService;

    public void init() {
        XeroItemRepositoryImpl xeroItemRepository = new XeroItemRepositoryImpl();
        inventoryService = new InventoryService(xeroItemRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET");
        PrintWriter out = resp.getWriter();

        List<Item> items = inventoryService.getInventoryItems();

      Gson gson = new Gson();
      String itemsJson = gson.toJson(items);
      System.out.println(itemsJson);
        out.println(itemsJson);
    }
}
