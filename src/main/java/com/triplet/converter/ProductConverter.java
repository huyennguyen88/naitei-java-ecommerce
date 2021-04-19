package com.triplet.converter;

import java.util.ArrayList;
import java.util.List;

import com.triplet.bean.ProductInfo;
import com.triplet.model.Product;

public class ProductConverter {
	
	public List<ProductInfo> convertToProductInfos(List<Product> products) {
		List<ProductInfo> productInfos = new ArrayList<>();
		for (Product product : products) {
			productInfos.add(new ProductInfo(product));
		}
		return productInfos;
	}
}
