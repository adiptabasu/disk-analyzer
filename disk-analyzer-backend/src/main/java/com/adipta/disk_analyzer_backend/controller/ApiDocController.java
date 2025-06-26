package com.adipta.disk_analyzer_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiDocController 
{
	@GetMapping("/api/scan/docs")
    public String getApiDocs() {
        return """
        Disk Analyzer API Documentation

        Base URL: /api/scan

        1. POST /start
           - Start basic scan (top 10 largest files)
           - Request body: { "path": "/absolute/path" }
           - Response: { "scanId": "uuid" }

        2. POST /detailed/start
           - Start detailed scan (all files)
           - Request body: { "path": "/absolute/path" }
           - Response: { "scanId": "uuid" }

        3. GET /status/{scanId}
           - Get scan status and results
           - Response includes status, largestFiles/allFiles, totalSize, durationMillis

        4. DELETE /cancel/{scanId}
           - Cancel running scan
           - Response: { "cancelled": true|false }

        5. GET /history
           - List all scan results

        Sizes are in bytes. Scans are async, so use scanId to check progress.

        """;
    }

}
