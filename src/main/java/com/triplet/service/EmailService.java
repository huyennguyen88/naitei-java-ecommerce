package com.triplet.service;

import com.triplet.bean.Feedback;

public interface EmailService {

	void sendFeedbackMail(Feedback feedback);
}
