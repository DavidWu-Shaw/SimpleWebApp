package org.sample.simplewebapp.servlets;

import java.io.IOException;

import org.sample.simplewebapp.beans.IProductService;
import org.sample.simplewebapp.beans.Product;
import org.sample.simplewebapp.beans.ProductMockService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateProductServlet
 */
@WebServlet("/createProduct")
public class CreateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IProductService productService;

	public CreateProductServlet() {
		super();
		productService = new ProductMockService();
	}

	// Show product creation page
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/createProductView.jsp");
		dispatcher.forward(request, response);
	}

	// When the user enters the product information, and click Submit.
	// This method will be called.
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String code = (String) request.getParameter("code");
		String name = (String) request.getParameter("name");
		String priceStr = (String) request.getParameter("price");
		float price = 0;
		try {
			price = Float.parseFloat(priceStr);
		} catch (Exception e) {
			// exception handling
		}

		String errorString = null;

		// Product code validation
		String regex = "\\w+";

		if (code == null || !code.matches(regex)) {
			errorString = "Product Code invalid!";
		}

		Product product = new Product(code, name, price);

		if (errorString == null) {
			try {
				productService.Insert(product);

			} catch (Exception e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}
		}

		// Store information to request attribute, before forward to views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("product", product);

		// If error, forward to Edit page.
		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/createProductView.jsp");
			dispatcher.forward(request, response);
		}
		// If everything nice.
		// Redirect to the product listing page.
		else {
			response.sendRedirect(request.getContextPath() + "/productList");
		}
	}
}
