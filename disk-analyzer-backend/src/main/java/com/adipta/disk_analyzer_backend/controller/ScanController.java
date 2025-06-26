package com.adipta.disk_analyzer_backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adipta.disk_analyzer_backend.model.ScanRequest;
import com.adipta.disk_analyzer_backend.model.ScanResult;
import com.adipta.disk_analyzer_backend.service.ScanManager;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/scan")
@CrossOrigin
@Tag(name = "Disk Analyzer API", description = "Endpoints for scanning and monitoring disk usage")
public class ScanController 
{
	@Autowired
    private ScanManager scanManager;

	@Operation(summary = "Start basic scan (top 10 largest files)")
    @PostMapping("/start")
    public Map<String, String> startScan(@RequestBody ScanRequest request) {
        String scanId = scanManager.startScan(request.getPath());
        return Map.of("scanId", scanId);
    }

	@Operation(summary = "Start detailed scan (all files)")
    @PostMapping("/detailed/start")
    public Map<String, String> startDetailedScan(@RequestBody ScanRequest request) {
        String scanId = scanManager.startDetailedScan(request.getPath());
        return Map.of("scanId", scanId);
    }

	@Operation(summary = "Get status/result of scan")
    @GetMapping("/status/{scanId}")
    public ScanResult getStatus(@PathVariable String scanId) {
        return scanManager.getStatus(scanId);
    }

	@Operation(summary = "Cancel a scan")
    @DeleteMapping("/cancel/{scanId}")
    public Map<String, Boolean> cancelScan(@PathVariable String scanId) {
        boolean cancelled = scanManager.cancelScan(scanId);
        return Map.of("cancelled", cancelled);
    }

	@Operation(summary = "List all scan histories")
    @GetMapping("/history")
    public Map<String, ScanResult> getAllScans() {
        return scanManager.getAllScans();
    }
}