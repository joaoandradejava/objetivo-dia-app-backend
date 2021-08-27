package com.joaoandrade.objetivodiaapp.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joaoandrade.objetivodiaapp.api.model.FeedbackFullModel;
import com.joaoandrade.objetivodiaapp.domain.model.Feedback;

@Component
public class FeedbackFullModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public FeedbackFullModel toModel(Feedback feedback) {
		return modelMapper.map(feedback, FeedbackFullModel.class);
	}
}
