package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.SupplierService;
import utils.JspHelper;

import java.io.IOException;

@WebServlet("/suppliers")
public class SupplierServlet extends HttpServlet {
    private final SupplierService suppliersService = SupplierService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        var supplierList = suppliersService.findAll();
        req.setAttribute("supplierList", supplierList);
        req.getRequestDispatcher(JspHelper.getPath("suppliers")).forward(req, resp);
    }
}
