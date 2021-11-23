package org.sample.simplewebapp.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductMockService implements IProductService{

	private static HashMap<String, Product> products;
	
	public ProductMockService()
	{
		products = makeList();
	}
	
	@Override
	public List<Product> GetList() {
		List<Product> list = new ArrayList<Product>(products.values());
		return list;
	}

	@Override
	public Product GetByCode(String code) {
		return products.get(code);
	}

	@Override
	public void Insert(Product product) {
		products.put(product.getCode(), product);
		
	}

	@Override
	public void Update(Product product) {
		products.put(product.getCode(), product);
	}

	private HashMap<String, Product> makeList() {
		HashMap<String, Product> products = new HashMap<String, Product>();
		Product p1 = new Product();
		p1.setName("Coffee machine");
		p1.setCode("p1");
		p1.setPrice(500);
		products.put(p1.getCode(), p1);
		
		Product p2 = new Product();
		p2.setName("Computer");
		p2.setCode("p2");
		p2.setPrice(1300);
		products.put(p2.getCode(), p2);

		return products;
	}

}
