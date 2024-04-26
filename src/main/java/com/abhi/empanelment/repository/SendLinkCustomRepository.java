package com.abhi.empanelment.repository;

import java.util.List;

import com.abhi.empanelment.model.SendLink;

public interface SendLinkCustomRepository {
	
	public List<SendLink> findByCustomParams(String workflowNo, String rohiniID, String providerName,String pincode);

}
