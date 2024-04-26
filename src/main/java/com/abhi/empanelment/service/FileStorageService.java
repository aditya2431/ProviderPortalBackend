package com.abhi.empanelment.service;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.abhi.empanelment.model.FileDB;
import com.abhi.empanelment.repository.FileDBRepository;
import org.springframework.util.StringUtils;
import java.util.List;
import java.util.stream.Stream;

import javax.transaction.Transactional;

@Service
public class FileStorageService {

	private final Logger logger = LoggerFactory.getLogger(FileStorageService.class);

	@Autowired
	private FileDBRepository fileDBRepository;

	@Transactional
	public FileDB store(MultipartFile file, String workflowNo, String docID, String userID) throws IOException {
		logger.info("inside FileStorageService store() method");
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		FileDB fileDB = null;
		FileDB newFileDB = null;
		logger.info("filename is: " + fileName);
		try {
			fileDB = fileDBRepository.findByWorkflowNoAndDocID(workflowNo, docID).orElse(null);
			if (fileDB != null) {
				fileDBRepository.updateData(file.getBytes(), fileName, file.getContentType(), fileDB.getWorkflowNo(),
						fileDB.getDocID());
				newFileDB = fileDB;
			} else {
				FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes(), workflowNo, docID, userID);
				newFileDB = fileDBRepository.save(FileDB);
			}

		} catch (Exception e) {
			logger.info("Error occured while fetching file details");
		}
		return newFileDB;
	}

	public List<FileDB> findByWorkflowNo(String workflowNo) {
		logger.info("inside FileStorageService getFile() method");
		List<FileDB> fileObjArray = fileDBRepository.findByWorkflowNo(workflowNo);
		List<FileDB> fileObjNewArray = null;
		for (FileDB temp : fileObjArray) {
			temp.setData(null);
		}
		fileObjNewArray = fileObjArray;
		return fileObjNewArray;
	}

	public Stream<FileDB> getAllFiles() {
		logger.info("inside FileStorageService getAllFiles() method");
		return fileDBRepository.findAll().stream();
	}

	public FileDB getFileByWorkflowNoAndDocID(String workflowNo, String docID) {
		logger.info("inside FileStorageService getFileByWorkflowNoAndDocID() method");
		return fileDBRepository.findByWorkflowNoAndDocID(workflowNo, docID).orElse(null);
	}

}
