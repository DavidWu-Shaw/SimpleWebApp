package org.sample.simplewebapp.servlets;

import java.io.IOException;
import java.util.List;

import org.sample.simplewebapp.beans.IProductService;
import org.sample.simplewebapp.beans.Product;
import org.sample.simplewebapp.beans.ProductMockService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = { "/productList" })
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IProductService productService;
	
	public ProductListServlet() {
		super();
		productService = new ProductMockService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Product> list = productService.GetList();
			
		// Store info in request attribute, before forward to views
		request.setAttribute("productList", list);
		
		// Forward to /WEB-INF/views/productListView.jsp
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/productListView.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}