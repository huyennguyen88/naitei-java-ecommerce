package com.triplet.utils;

import java.util.List;

import com.triplet.model.Rate;
import com.triplet.model.Rate.Value;

public class RateUtils {
	private List<Rate> rates;
	private int valueRange;

	public RateUtils(List<Rate> rates) {
		this.rates = rates;
		this.valueRange = Value.values().length; //Value in enum type
	}

	public int[] getRatesPercent() {

		// Count number of each rate's value in rates
		int[] rateCount = new int[valueRange];
		int[] ratesPercent = new int[valueRange];
		
		if(rates.size()==0) return  ratesPercent;
		
		int numberOfRates = rates.size();
		for (Rate rate : rates) {
			rateCount[rate.getValue().ordinal()]++;
		}

		// Find percentage of each rate's value
		for (int i = 0; i < valueRange; i++) {
			ratesPercent[i] = (rateCount[i] * 100) / numberOfRates;
		}

		// Reverse result's array to show in view
		for (int i = 0; i < valueRange / 2; i++) {
			int temp = ratesPercent[i];
			ratesPercent[i] = ratesPercent[valueRange - i -1];
			ratesPercent[valueRange - i -1] = temp;
		}
		return ratesPercent;
	}
}
