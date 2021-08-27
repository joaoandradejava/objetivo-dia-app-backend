package com.joaoandrade.objetivodiaapp.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joaoandrade.objetivodiaapp.api.input.FeedbackInput;
import com.joaoandrade.objetivodiaapp.domain.model.Feedback;

@Component
public class FeedbackInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Feedback toDomainModel(FeedbackInput feedbackInput) {
		return modelMapper.map(feedbackInput, Feedback.class);
	}
}
