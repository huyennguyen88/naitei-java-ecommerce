package com.triplet.service;

import java.util.List;

import com.triplet.model.Rate;

public interface RateService extends BaseService<Integer, Rate>{
	
	List<Rate> loadRatings(Integer productId);
}
