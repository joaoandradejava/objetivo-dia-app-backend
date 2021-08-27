package com.joaoandrade.objetivodiaapp.domain.service.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoandrade.objetivodiaapp.domain.model.Feedback;
import com.joaoandrade.objetivodiaapp.domain.repository.FeedbackRepository;

@Service
public class CrudUsuarioFeedbackService {

	@Autowired
	private FeedbackRepository repository;

	@Transactional
	public Feedback salvar(Feedback feedback) {
		return repository.save(feedback);
	}
}
