package com.abhi.empanelment.service;

import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import com.abhi.empanelment.pojo.BitlyLinkJson;
import com.abhi.empanelment.pojo.CyberarkPostAPIResponse;
import com.abhi.empanelment.pojo.ClickPSSMOUResponse;
import com.abhi.empanelment.pojo.ObjRequest;
import com.abhi.empanelment.pojo.RTdetails;
import com.abhi.empanelment.pojo.SendOTPRequest;
import com.abhi.empanelment.pojo.ValidateOTPRequest;
import com.abhi.empanelment.repository.UserRepository;
import com.abhi.empanelment.pojo.BitlyAPIResponse;
import com.abhi.empanelment.exception.ResourceNotFoundException;
import com.abhi.empanelment.message.ResponseMessage;
import com.abhi.empanelment.model.User;
import com.abhi.empanelment.pojo.Alertdata;
import com.abhi.empanelment.pojo.ClickPSSRequest;
import com.abhi.empanelment.pojo.CyberarkGetAPIResponse;
import com.abhi.empanelment.pojo.HypervergeTokenRequest;
import com.abhi.empanelment.pojo.CyberarkTokenRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class UtilityService {

	private final Logger logger = LoggerFactory.getLogger(UtilityService.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	UserRepository userRepository;

	public String callBitlyAPI(String apieEndPoint, String longURL) {

		logger.info("inside UtilityService callBitlyAPI() method");
		String returnUrl = null;

		ObjRequest newRequest = new ObjRequest(longURL, "3", "ClickPassProd", "B8iaP9sbZLX09KKbijoqkg==");

		BitlyLinkJson jsonObj = new BitlyLinkJson();
		jsonObj.setObjRequest(newRequest);

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<BitlyLinkJson> entity = new HttpEntity<BitlyLinkJson>(jsonObj, headers);

		try {
			ResponseEntity<BitlyAPIResponse> result = restTemplate.postForEntity(apieEndPoint, entity,
					BitlyAPIResponse.class);

			BitlyAPIResponse respObject = result.getBody();
			returnUrl = respObject.getURLReturned();
			logger.info("short url returned by API is: " + returnUrl);

		} catch (Exception e) {
			logger.info("Error occured while getting bitly link");
			logger.info(e.getMessage());
		}
		return returnUrl;
	}

	public ResponseEntity<ResponseMessage> callClickPSSAPI(String policyId, String appNo, String alertId,
			String channelId, String reqId, String field1, String field2, String field3, String alertMode,
			String mobileNo, String emailId, String alertV1, String alertV2, String alertV3, String alertV4,
			String alertV5, String alertV6, String alertV7, String alertV8, String alertV9, String alertV10, String ccEmailId) {
		// TODO Auto-generated method stub
		logger.info("inside UtilityService callClickPSSAPI() method");
		logger.info("Calling clickPSS API for workflow: " + appNo);
		String clickPSSEndPoint = "https://esbsit.adityabirlahealth.com/ABHICL_ClickPSS/Service1.svc/click";
		ResponseEntity<String> result = null;
		Object returnObject = null;
		Alertdata alterData = new Alertdata(mobileNo, emailId, ccEmailId, alertV1, alertV2,
				alertV3, alertV4, alertV5, alertV6, alertV7, alertV8, alertV9, alertV10);
		RTdetails rtDetails = new RTdetails(policyId, appNo, alertId, channelId, reqId, field1, field2, field3,
				alertMode, alterData);
		ClickPSSRequest clickPssRequest = new ClickPSSRequest(rtDetails);

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<ClickPSSRequest> entity = new HttpEntity<ClickPSSRequest>(clickPssRequest, headers);

		try {
			logger.info("inside clickpss api");
			result = restTemplate.postForEntity(clickPSSEndPoint, entity, String.class);
			returnObject = result.getBody();
			logger.info("Email sent successfully for workflow: " + appNo);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseMessage("ClickPSS email trieggered successfully for workflow: " + appNo));
		} catch (Exception e) {
			logger.info("Error occured while trigerring email for workflow: " + appNo);
			logger.info(e.getMessage());
			System.out.println(e.getStackTrace());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseMessage("Error occured while sending email for workflow: " + appNo));
		}

	}

//	public Object callAISOTPService(SendOTPRequest requestBody) {
//		// TODO Auto-generated method stub
//
//		logger.info("inside UtilityService callAISOTPService() method");
//		logger.info("Calling AIS send OTP service for workflow: " + requestBody.getWorkflowNo());
//		String respObject = null;
//		String apiEndPoint = "https://apimuat.abhicl.in/Abhi_OTP/OTP_Trigger";
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//
//		HttpEntity<SendOTPRequest> entity = new HttpEntity<SendOTPRequest>(requestBody, headers);
//
//		try {
//			ResponseEntity<String> result = restTemplate.postForEntity(apiEndPoint, entity, String.class);
//
//			respObject = result.getBody();
//			logger.info("SendOTP API reponse is: " + respObject);
//
//		} catch (Exception e) {
//			logger.info("Error occured while sending OTP for workflow: " + requestBody.getWorkflowNo());
//			logger.info(e.getMessage());
//		}
//		return respObject;
//
//	}
	
	public Object callAISOTPService(String requestBody) {
		// TODO Auto-generated method stub

		logger.info("inside UtilityService callAISOTPService() method");
		logger.info("Calling AIS send OTP service");
		String respObject = null;
		String apiEndPoint = "https://apimuat.abhicl.in/Abhi_OTP/OTP_Trigger";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);

		HttpEntity<String> entity = new HttpEntity<String>(requestBody, headers);

		try {
			ResponseEntity<String> result = restTemplate.postForEntity(apiEndPoint, entity, String.class);

			respObject = result.getBody();
			logger.info("SendOTP API reponse is: " + respObject);

		} catch (Exception e) {
			logger.info("Error occured while sending OTP");
			logger.info(e.getMessage());
		}
		return respObject;

	}

//	public Object callAISValidateOTPService(ValidateOTPRequest validateOTPRequest) {
//		// TODO Auto-generated method stub
//
//		logger.info("inside UtilityService callAISValidateOTPService() method");
//		logger.info("Calling AIS validate OTP service for workflow: " + validateOTPRequest.getWorkflowNo());
//		String respObject = null;
//		String apieEndPoint = "https://apimuat.abhicl.in/Abhi_OTP/OTP_Validation";
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//
//		HttpEntity<ValidateOTPRequest> entity = new HttpEntity<ValidateOTPRequest>(validateOTPRequest, headers);
//
//		try {
//			ResponseEntity<String> result = restTemplate.postForEntity(apieEndPoint, entity, String.class);
//
//			respObject = result.getBody();
//			logger.info("Validate OTP API response is: " + respObject);
//			if(result.getStatusCode() == HttpStatus.OK) {
//				return ResponseEntity.status(HttpStatus.OK)
//						.body(new ResponseMessage("OTP Validated"));
//			}else {
//				return ResponseEntity.status(HttpStatus.MULTIPLE_CHOICES)
//						.body(new ResponseMessage("OTP Validated"));
//			}			
//		} catch (Exception e) {
//			logger.info("Error occured while validating OTP for workflow: " + validateOTPRequest.getWorkflowNo());
//			logger.info(e.getMessage());
//		}
//		return ResponseEntity.status(HttpStatus.MULTIPLE_CHOICES)
//				.body(new ResponseMessage("OTP Validated"));
//	}
	
	public Object callAISValidateOTPService(String validateOTPRequest) {
		// TODO Auto-generated method stub

		logger.info("inside UtilityService callAISValidateOTPService() method");
		String respObject = null;
		String apieEndPoint = "https://apimuat.abhicl.in/Abhi_OTP/OTP_Validation";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);

		HttpEntity<String> entity = new HttpEntity<String>(validateOTPRequest, headers);

		try {
			ResponseEntity<String> result = restTemplate.postForEntity(apieEndPoint, entity, String.class);

			respObject = result.getBody();
			logger.info("Validate OTP API response is: " + respObject);			
		} catch (Exception e) {
			logger.info("Error occured while validating OTP");
			logger.info(e.getMessage());
		}
		return respObject;
	}

	public Object callGoogleAddressAPI(String addressToSearch) {
		// TODO Auto-generated method stub
		logger.info("inside UtilityService callGoogleAddressAPI() method");
		logger.info("Calling Google API for received address: " + addressToSearch);
		String respObject = null;
		String url = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json" + "?input=" + addressToSearch
				+ "&inputtype=textquery" + "&fields=formatted_address,name,geometry" + "&key="
				+ "AIzaSyB3VNlu6w8WqHR7VjVI586jFbKX_y_XPow";
		logger.info("final endpoint is: " + url);

		try {
			ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

			respObject = responseEntity.getBody();
			logger.info("Google address API response is: " + respObject);
			return ResponseEntity.status(responseEntity.getStatusCode()).body(responseEntity.getBody());

		} catch (Exception e) {
			logger.info("Error occured while calling Google address API");
			logger.info(e.getMessage());
			return respObject;
		}
	}

	public Object callHypervergeTokenAPI(String workflowNo) {
		// TODO Auto-generated method stub

		logger.info("inside UtilityService callHypervergeTokenAPI() method");
		logger.info("Calling hyperverge generate token service for workflow: " + workflowNo);
		String respObject = null;
		String apiEndPoint = "https://auth.hyperverge.co/login";

		HypervergeTokenRequest hypervergeTokenRequest = new HypervergeTokenRequest("l7zrfu", "77gq4mlseyryjxdiundi",
				"86400");

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<HypervergeTokenRequest> entity = new HttpEntity<HypervergeTokenRequest>(hypervergeTokenRequest,
				headers);

		try {
			ResponseEntity<String> result = restTemplate.postForEntity(apiEndPoint, entity, String.class);

			respObject = result.getBody();
			logger.info("Validate OTP API response is: " + respObject);
			return ResponseEntity.status(result.getStatusCode()).body(result.getBody());

		} catch (Exception e) {
			logger.info("Error occured while validating OTP for workflow: " + workflowNo);
			logger.info(e.getMessage());
		}
		return respObject;
	}

	public Object callCyberarkAPI(String tokenId) {
		// TODO Auto-generated method stub

		logger.info("inside UtilityService callCyberarkAPI() method");
		CyberarkPostAPIResponse respObject = null;
		ResponseEntity<CyberarkGetAPIResponse> getResponse = null;
		List<User> user = null;
		String apiEndPoint = "https://aaq4824.id.cyberark.cloud/oauth2/token/providerempanelmentportaluat";

		try {

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			map.add("redirect_uri", "https://providerportaluat.adityabirlahealth.com/ABHI_ProviderPortal");
			map.add("code", tokenId);
			map.add("grant_type", "authorization_code");
			map.add("client_id", "e3639705-07c0-46f8-a77d-8dcfddcfdacc");
			map.add("client_secret", "providerempanelmentportal");
			map.add("state", "abc");

			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

			ResponseEntity<CyberarkPostAPIResponse> response = restTemplate.postForEntity(apiEndPoint, request,
					CyberarkPostAPIResponse.class);

			HttpStatus statusCode = response.getStatusCode();
			respObject = response.getBody();
			String accessToken = respObject.getAccess_token();
			logger.info("Cyberark POST API response is: " + accessToken);

			if (statusCode == HttpStatus.OK) {

				try {

					String url = "https://aaq4824.id.cyberark.cloud/oauth2/userinfo/providerempanelmentportaluat";

					headers = new HttpHeaders();
					headers.set("Authorization", "Bearer " + accessToken);

					HttpEntity<String> entity = new HttpEntity<>(headers);

					RestTemplate restTemplate = new RestTemplate();
					getResponse = restTemplate.exchange(url, HttpMethod.GET, entity, CyberarkGetAPIResponse.class);

					statusCode = response.getStatusCode();
					String sAMAccountName = getResponse.getBody().getsAMAccountName();
					logger.info("Cyberark GET API response is: " + sAMAccountName);

					if (statusCode == HttpStatus.OK) {

						try {
							user = userRepository.findByAdId(sAMAccountName);
							if (user != null) {
								return ResponseEntity.status(getResponse.getStatusCode()).body(user);
							}else{
								return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
										.body(new ResponseMessage("User does not exist on Provider Portal"));
							}
						} catch (Exception e) {
							logger.info("Error occured while fetching user details from user repository: "
									+ e.getMessage());
						}
					} else {
						return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								.body(new ResponseMessage("Error occured while authenticating with Cyberark"));
					}

//					return ResponseEntity.status(getResponse.getStatusCode()).body(user);

				} catch (Exception e) {
					logger.info("Something went wrong: " + e.getMessage());
				}
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(new ResponseMessage("Error occured while authenticating with Cyberark"));
			}

		} catch (Exception e) {
			logger.info("Error while calling cyberArk POST API: " + e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseMessage("Error occured while authenticating with Cyberark"));
	}

}
