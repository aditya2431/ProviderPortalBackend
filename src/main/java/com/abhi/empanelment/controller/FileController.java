package com.abhi.empanelment.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.abhi.empanelment.model.FileDB;
//import com.abhi.empanelment.SimpleCORSFilter;
import com.abhi.empanelment.message.ResponseFile;
import com.abhi.empanelment.message.ResponseMessage;
import com.abhi.empanelment.service.FileStorageService;

@RestController
@RequestMapping("api/files")
public class FileController {

	private final Logger logger = LoggerFactory.getLogger(FileController.class);

	@Autowired
	private FileStorageService storageService;

	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file,
			@RequestParam("workflowNo") String workflowNo, @RequestParam("docID") String docID,
			@RequestParam("userID") String userID) {
		logger.info("inside FileController uploadFile() method");
		String message = "";
		try {
			storageService.store(file, workflowNo, docID, userID);
			logger.info("file uploaded successfully");
			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			logger.info("something went wrong while uploading file");
			logger.debug(e.getMessage());
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@GetMapping("/files")
	public ResponseEntity<List<ResponseFile>> getListFiles() {
		logger.info("inside FileController getListFiles() method ");
		List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
					.path(dbFile.getId()).toUriString();

			return new ResponseFile(dbFile.getWorkflowNo(), dbFile.getDocID(), dbFile.getUserID(), dbFile.getName(),
					fileDownloadUri, dbFile.getType(), dbFile.getData().length, dbFile.getUploadDate());

		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(files);
	}

	@GetMapping("/files/{workflowNo}")
	public List<FileDB> getFile(@PathVariable String workflowNo) {
		logger.info("inside FileController getFile() method ");
		return storageService.findByWorkflowNo(workflowNo);
//        System.out.println(fileDB);
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
//                .body(fileDB.getData());
	}

	public class FileResponse {
		private String fileType;
		private byte[] data;

		public FileResponse(String fileType, byte[] data) {
			this.fileType = fileType;
			this.data = data;
		}

		public String getFileType() {
			return fileType;
		}

		public byte[] getData() {
			return data;
		}
	}

	@GetMapping("/files/{workflowNo}/{docID}")
	public ResponseEntity<FileResponse> getFile(@PathVariable String workflowNo, @PathVariable String docID) {
		logger.info("inside FileController getFileByDocId() method ");
		FileDB fileDB = storageService.getFileByWorkflowNoAndDocID(workflowNo, docID);

		if (fileDB != null) {
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
					.body(new FileResponse(fileDB.getType(), fileDB.getData()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
