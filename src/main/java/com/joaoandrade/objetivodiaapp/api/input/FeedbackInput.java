package com.joaoandrade.objetivodiaapp.api.input;

import javax.validation.constraints.NotBlank;

public class FeedbackInput {

	@NotBlank
	private String feedback;

	public FeedbackInput() {
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

}
