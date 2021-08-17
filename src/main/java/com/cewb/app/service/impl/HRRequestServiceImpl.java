package com.cewb.app.service.impl;

<<<<<<< HEAD
import com.cewb.app.config.ConfigRepository;
import com.cewb.app.model.HRRequest;
import com.cewb.app.repository.HRRequestRepository;
import com.cewb.app.service.HRRequestService;
=======
import java.util.List;

import javax.persistence.EntityNotFoundException;

>>>>>>> 08990fb8b9cf567a034a0620dd3296c5460574e7
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import javax.persistence.EntityNotFoundException;
=======
import com.cewb.app.dto.request.HrRequestDto;
import com.cewb.app.model.HRRequest;
import com.cewb.app.repository.HRRequestRepository;
import com.cewb.app.service.HRRequestService;
<<<<<<< HEAD
>>>>>>> 08990fb8b9cf567a034a0620dd3296c5460574e7
=======
import com.cewb.app.utility.AppUtility;
>>>>>>> 6a12f16bb5a3dc46648f63097cd42c8dac3b0d64

@Service
public class HRRequestServiceImpl implements HRRequestService {
	
	@Autowired
	HRRequestRepository requestRepository;

	@Override
	public Page<HRRequest> findAll(int pageNum) {
		return requestRepository.findAll(PageRequest.of(pageNum, ConfigRepository.PER_PAGE));
	}

	@Override
	public List<HRRequest> findByFilter(HrRequestDto hrRequest) {
		return requestRepository.findByFilter(
				hrRequest.getStartDate(),
				hrRequest.getEndDate(),
				AppUtility.getSqlKeyword(hrRequest.getDepartment()),
				AppUtility.getSqlKeyword(hrRequest.getClassification()),
				AppUtility.getSqlKeyword(hrRequest.getStatus())
			);
	}
	
	@Override
	public HRRequest findById(Long id) {
		HRRequest result = requestRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Cant find HR request with id - " + id));
		
        return result;
	}
	
	@Override
	public HRRequest save(HRRequest request) {
		return requestRepository.save(request);
	}

	@Override
	public HRRequest update(HRRequest request) {
		findById(request.getId());
		return save(request);
	}


	@Override
	public HRRequest delete(Long id) {
		HRRequest request = findById(id);
		
		requestRepository.delete(request);
		return request;
	}

}
