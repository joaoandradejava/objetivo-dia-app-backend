package com.joaoandrade.objetivodiaapp.api.model;

import java.time.LocalDateTime;

public class FeedbackFullModel {
	private Long id;
	private String feedback;
	private LocalDateTime data;

	public FeedbackFullModel() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

}
