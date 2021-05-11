package com.triplet.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class FormatUtils {
	public String formatCurrency(BigDecimal price) {
		Locale loc = new Locale("vi", "VN");
		NumberFormat nf = NumberFormat.getCurrencyInstance(loc);
		String formatPrice = nf.format(price.doubleValue());
		return formatPrice;
	}
}
