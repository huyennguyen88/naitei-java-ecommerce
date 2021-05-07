package com.triplet.service.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.triplet.model.Rate;
import com.triplet.service.RateService;

public class RateServiceImpl extends BaseServiceImpl implements RateService {
	private static final Logger logger = Logger.getLogger(RateServiceImpl.class);

	@Override
	public List<Rate> loadRatings(Integer productId) {
		try {
			return getRateDAO().loadRatings(productId);
		} catch (Exception e) {
			logger.error(e);
			return Collections.emptyList();
		}
	}

	@Override
	public Rate findById(Serializable id) {
		try {
			return getRateDAO().findById(id);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public Rate saveOrUpdate(Rate rate) {
		try {
			return getRateDAO().saveOrUpdate(rate);
		} catch (Exception e) {
			logger.error("Fail to save rate! ",e);
			throw(e);
		}
	}

	@Override
	public boolean delete(Rate rate) {
		try {
			getRateDAO().delete(rate);
			return true;
		} catch (Exception e) {
			logger.error("Fail to delete rate! ",e);
			throw(e);
		}
	}
}