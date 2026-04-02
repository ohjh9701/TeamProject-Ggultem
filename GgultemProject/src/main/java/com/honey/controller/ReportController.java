package com.honey.controller;

import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.honey.dto.ReportDTO;
import com.honey.service.ReportService;
import com.honey.util.CustomFileUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/report")
public class ReportController {

    private final ReportService reportService;
    private final CustomFileUtil fileUtil;

    @PostMapping("/register")
    public Map<String, Long> register(ReportDTO reportDTO) {
        if (reportDTO.getFiles() != null && !reportDTO.getFiles().isEmpty()) {
            List<String> uploadFileNames = fileUtil.saveFiles(reportDTO.getFiles());
            reportDTO.setUploadFileNames(uploadFileNames);
        }
        Long reportId = reportService.register(reportDTO);
        return Map.of("REPORT_ID", reportId);
    }

    @GetMapping("/view/{fileName}") // ✅ 추가
    public ResponseEntity<Resource> viewFile(@PathVariable String fileName) {
        return fileUtil.getFile(fileName);
    }
}