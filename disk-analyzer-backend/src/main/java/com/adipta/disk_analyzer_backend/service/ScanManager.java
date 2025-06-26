package com.adipta.disk_analyzer_backend.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.adipta.disk_analyzer_backend.model.FileInfo;
import com.adipta.disk_analyzer_backend.model.ScanResult;
import com.adipta.disk_analyzer_backend.model.ScanStatus;

@Service
public class ScanManager 
{
	private final Map<String, ScanResult> scanJobs = new ConcurrentHashMap<>();
    private final Set<String> cancelledScans = ConcurrentHashMap.newKeySet();

    public String startScan(String path) {
        String scanId = UUID.randomUUID().toString();
        ScanResult result = new ScanResult(scanId);
        result.setStatus(ScanStatus.RUNNING);
        scanJobs.put(scanId, result);
        runScanAsync(scanId, path);
        return scanId;
    }

    public String startDetailedScan(String path) {
        String scanId = UUID.randomUUID().toString();
        ScanResult result = new ScanResult(scanId);
        result.setStatus(ScanStatus.RUNNING);
        scanJobs.put(scanId, result);
        runDetailedScanAsync(scanId, path);
        return scanId;
    }

    @Async("scanExecutor")
    public void runScanAsync(String scanId, String pathStr) {
        ScanResult result = scanJobs.get(scanId);
        long start = System.currentTimeMillis();

        try {
            Path path = Paths.get(pathStr);
            List<FileInfo> files = new ArrayList<>();

            Files.walk(path)
                .filter(Files::isRegularFile)
                .forEach(p -> {
                    if (cancelledScans.contains(scanId)) return;
                    try {
                        files.add(new FileInfo(p.toString(), Files.size(p)));
                    } catch (IOException ignored) {}
                });

            files.sort((a, b) -> Long.compare(b.getSize(), a.getSize()));
            List<FileInfo> top10 = files.stream().limit(10).toList();
            long totalSize = files.stream().mapToLong(FileInfo::getSize).sum();

            result.setLargestFiles(top10);
            result.setTotalSize(totalSize);
            result.setDurationMillis(System.currentTimeMillis() - start);

            if (!cancelledScans.contains(scanId)) {
                result.setStatus(ScanStatus.COMPLETED);
            } else {
                result.setStatus(ScanStatus.CANCELLED);
            }

        } catch (Exception e) {
            result.setStatus(ScanStatus.FAILED);
        }
    }

    @Async("scanExecutor")
    public void runDetailedScanAsync(String scanId, String pathStr) {
        ScanResult result = scanJobs.get(scanId);
        long start = System.currentTimeMillis();

        try {
            Path path = Paths.get(pathStr);
            List<FileInfo> files = new ArrayList<>();

            Files.walk(path)
                .filter(Files::isRegularFile)
                .forEach(p -> {
                    if (cancelledScans.contains(scanId)) return;
                    try {
                        files.add(new FileInfo(p.toString(), Files.size(p)));
                    } catch (IOException ignored) {}
                });

            files.sort((a, b) -> Long.compare(b.getSize(), a.getSize()));

            result.setAllFiles(files);
            long totalSize = files.stream().mapToLong(FileInfo::getSize).sum();
            result.setTotalSize(totalSize);
            result.setDurationMillis(System.currentTimeMillis() - start);

            if (!cancelledScans.contains(scanId)) {
                result.setStatus(ScanStatus.COMPLETED);
            } else {
                result.setStatus(ScanStatus.CANCELLED);
            }

        } catch (Exception e) {
            result.setStatus(ScanStatus.FAILED);
        }
    }

    public ScanResult getStatus(String scanId) {
        return scanJobs.get(scanId);
    }

    public boolean cancelScan(String scanId) {
        if (scanJobs.containsKey(scanId)) {
            cancelledScans.add(scanId);
            return true;
        }
        return false;
    }

    public Map<String, ScanResult> getAllScans() {
        return scanJobs;
    }
}