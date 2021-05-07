package com.triplet.dao;

import java.util.List;

import com.triplet.model.Rate;

public interface RateDAO  extends BaseDAO<Integer, Rate> {
	
	List<Rate> loadRatings(Integer productId);
}