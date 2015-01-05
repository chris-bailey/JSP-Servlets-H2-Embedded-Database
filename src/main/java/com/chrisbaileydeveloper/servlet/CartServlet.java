package com.chrisbaileydeveloper.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chrisbaileydeveloper.domain.LineItem;
import com.chrisbaileydeveloper.domain.Order;
import com.chrisbaileydeveloper.domain.Product;
import com.chrisbaileydeveloper.util.ProductFactory;

public class CartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		// Synchronize the session in case user opens multiple browser tabs
		// which would be part of the same session
		
		synchronized (session) {
			// Process the cart. Keep track of Orders.
			Order order = (Order) session.getAttribute("order");
			
			// If an Order for the Session doesn't yet exist, then create one.
			if (order == null) {
				order = new Order();
			}

			String prodId = request.getParameter("prodId");
			String quantityAsString = request.getParameter("quantity");

			// Set quantity
			int quantity = 1;
			try {
				quantity = Integer.parseInt(quantityAsString);
				if (quantity < 0)
					quantity = 1;
			} catch (NumberFormatException nfe) {
				quantity = 1;
			}

			// Retrieve the product from the Servlet Context's 'products'
			// attribute.
			Product p = ProductFactory.createProduct(getServletContext()
					.getAttribute("products"), prodId);
			LineItem li = new LineItem(p, quantity);

			// Add or remove Product from Order
			if (quantity == 0) {
				order.removeItem(li);
			} else {
				order.addItem(li);
			}

			// Update Cart session attribute
			session.setAttribute("order", order);

			// Forward to JSP
			String url = "/shopping_cart.jsp";
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}
