package com.abhi.empanelment.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.abhi.empanelment.model.SendLink;

import java.util.List;

@Repository

class SendLinkCustomRepositoryImpl implements SendLinkCustomRepository {

//	@Autowired
//	SendLinkRepository sendLinkRepository;

	EntityManager em;

	public SendLinkCustomRepositoryImpl(EntityManager em) {
		super();
		this.em = em;
	}

	public List<SendLink> findByCustomParams(String workflowNo, String rohiniID, String providerName,String pincode) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		Predicate workflowNoPredicate = null;
		Predicate rohiniIDPredicate = null;
		Predicate providerNamePredicate = null;
		Predicate pincodePredicate = null;
		Predicate searchParamsOr = null;
		CriteriaQuery<SendLink> cq = cb.createQuery(SendLink.class);
		
		Root<SendLink> sendLink = cq.from(SendLink.class);
		if(workflowNo !=  null) {
			workflowNoPredicate = cb.equal(sendLink.get("workflowNo"), workflowNo);
		}
		if(rohiniID != null) {
			rohiniIDPredicate = cb.equal(sendLink.get("rohiniId"), rohiniID);
		}
		if(providerName != null) {
			providerNamePredicate = cb.equal(sendLink.get("providerName"), providerName);
		}
		if(pincode != null) {
			pincodePredicate = cb.equal(sendLink.get("pincode"), pincode);
		}
		searchParamsOr = cb.or(workflowNoPredicate,rohiniIDPredicate,providerNamePredicate,pincodePredicate);
		
		cq.where(searchParamsOr);

		TypedQuery<SendLink> query = em.createQuery(cq);
		return query.getResultList();

	}

}