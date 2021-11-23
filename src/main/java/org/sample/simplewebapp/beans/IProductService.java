package org.sample.simplewebapp.beans;

import java.util.List;

public interface IProductService {
	
	public List<Product> GetList();
	public Product GetByCode(String code);
	public void Insert(Product product);
	public void Update(Product product);

}
