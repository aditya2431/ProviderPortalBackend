package com.abhi.empanelment.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.abhi.empanelment.message.ResponseMessage;
import com.abhi.empanelment.model.FileDB;
import com.abhi.empanelment.model.MOUDocument;
import com.abhi.empanelment.model.SendLink;
import com.abhi.empanelment.model.User;
import com.abhi.empanelment.pojo.Alertdata;
import com.abhi.empanelment.pojo.ClickPSSMOURequest;
import com.abhi.empanelment.pojo.ClickPSSMOUResponse;
import com.abhi.empanelment.pojo.RTdetails;
import com.abhi.empanelment.repository.FileDBRepository;
import com.abhi.empanelment.repository.SendLinkRepository;
import com.abhi.empanelment.repository.UserRepository;

@Service
public class MOUDocumentService {

	private final Logger logger = LoggerFactory.getLogger(MOUDocumentService.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	SendLinkRepository sendLinkRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	private FileDBRepository fileDBRepository;

	@Autowired
	AuditTrailService auditTrailService;

	@Transactional
	public ResponseEntity<ResponseMessage> callClickPSSMOUAPI(MOUDocument request) {
		// TODO Auto-generated method stub
		logger.info("inside MOUDocumentService callClickPSSMOUAPI() method");

		String rmEmailId = null;
		String zmEmailId = null;
		SendLink sendLink = sendLinkRepository.findByWorkflowNo(request.getWorkflowNo());
//		List<User> userList = userRepository.findByAdId(request.getRmAdId());
		List<User> userList = userRepository.findByAdIdAndLocation(sendLink.getRmAdId(), sendLink.getState());
		for (User user : userList) {
			rmEmailId = user.getRmEmailId();
			zmEmailId = user.getZmEmailId();
		}

		logger.info("Calling clickPSS MOU API for workflow: " + request.getWorkflowNo());
		String clickPSSEndPoint = "https://esbsit.adityabirlahealth.com/Empanelment/Service1.svc/Empanelment";
		ResponseEntity<ClickPSSMOUResponse> result = null;
		Object returnObject = null;
		ClickPSSMOURequest clickPSSMOURequest = new ClickPSSMOURequest("621", request.getWorkflowNo(),
				request.getAgreementMadeOn(), request.getHospitalName(), sendLink.getEmail(),
				rmEmailId + ","+ zmEmailId, sendLink.getAddress(), request.getRepresentedBy(),
				request.getSignedBy(), request.getServiceLevelDiscount(), request.getDiscountValidityFrom(),
				request.getDiscountValidityTo(), request.getOpdDiscount(), request.getDiagnosticTestDiscount(),
				request.getPharmacyDiscount(), request.getEpdForPayment5Days(), request.getEpdForPayment7Days(),
				request.getEpdForPayment10Days(), request.getEpdForPayment15Days(), request.getEpdForPayment20Days(),
				request.getEpdForPayment5DaysDesc(), request.getEpdForPayment7DaysDesc(),
				request.getEpdForPayment10DaysDesc(), request.getEpdForPayment15DaysDesc(),
				request.getEpdForPayment20DaysDesc());

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<ClickPSSMOURequest> entity = new HttpEntity<ClickPSSMOURequest>(clickPSSMOURequest, headers);

		try {
			logger.info("inside clickpss MOU API");
			result = restTemplate.postForEntity(clickPSSEndPoint, entity, ClickPSSMOUResponse.class);
			logger.info("Successfully retrived MOU document for workflow no: " + request.getWorkflowNo());
			auditTrailService.insertAuditTrail(sendLink.getWorkflowNo(), "MOU Generated", "Hospital Queue",
					"MOU pending for stamping", sendLink.getRmAdId(), new java.util.Date(), 0);
			returnObject = result.getBody();
			FileDB fileDB = null;
			try {
				fileDB = fileDBRepository.findByWorkflowNoAndDocID(request.getWorkflowNo(), "MOUDOC").orElse(null);
				if (fileDB != null) {
					fileDBRepository.updateData(result.getBody().getByteArray(), "mou_document.pdf", "application/pdf",
							fileDB.getWorkflowNo(), fileDB.getDocID());
				} else {
					FileDB FileDB = new FileDB("mou_document.pdf", "application/pdf", result.getBody().getByteArray(),
							request.getWorkflowNo(), "MOUDOC", "SYSTEM");
					fileDBRepository.save(FileDB);
				}
				logger.info("Successfully inserted MOU document in DB for workflow no: " + request.getWorkflowNo());
			} catch (Exception e) {
				logger.info("Error occured while iserting MOU document for workflow: " + request.getWorkflowNo());
				logger.info(e.getMessage());
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseMessage("Error occured while generating MOU document"));
			}
			try {
				FileDB FileDB = new FileDB("mou_document_backup.pdf", "application/pdf",
						result.getBody().getByteArray(), request.getWorkflowNo(), "MOUDOCBKP", "SYSTEM");
				fileDBRepository.save(FileDB);
			} catch (Exception e) {
				logger.info("Error occured while inserting MOU backup document in DB for workflow: "
						+ request.getWorkflowNo());
			}
			try {
				sendLink.setWorkflowStatus("MOU Generated");
				sendLink.setMouGenerationDate(new Date());
				SendLink updatedSendLink = sendLinkRepository.save(sendLink);
				logger.info("Workflow status successfully updated after MOU generation for workflow: "
						+ updatedSendLink.getWorkflowNo());
			} catch (Exception e) {
				logger.info("Error occured while updating workflow status after generating MOU document for workflow: "
						+ sendLink.getWorkflowNo());
			}
		} catch (Exception e) {
			logger.info("Error occured while trigerring MOU API for workflow: " + request.getWorkflowNo());
			logger.info(e.getMessage());
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseMessage("Error occured while generating MOU document"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("MOU document generated successfully"));

	}

}
