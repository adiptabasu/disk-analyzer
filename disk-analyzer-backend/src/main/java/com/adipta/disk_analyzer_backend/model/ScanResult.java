package com.adipta.disk_analyzer_backend.model;

import java.util.List;

public class ScanResult 
{
	private String scanId;
    private ScanStatus status;
    private List<FileInfo> largestFiles;
    private List<FileInfo> allFiles;
    private long totalSize;
    private long durationMillis;
    
    public ScanResult(String scanId)
    {
        this.scanId = scanId;
        this.status = ScanStatus.PENDING;
    }

    public String getScanId() 
    {
        return scanId;
    }

    public ScanStatus getStatus() 
    {
        return status;
    }

    public void setStatus(ScanStatus status)
    {
        this.status = status;
    }

    public List<FileInfo> getLargestFiles()
    {
        return largestFiles;
    }

    public void setLargestFiles(List<FileInfo> largestFiles) 
    {
        this.largestFiles = largestFiles;
    }
    
    public List<FileInfo> getAllFiles() 
    {
        return allFiles;
    }

    public void setAllFiles(List<FileInfo> allFiles) 
    {
        this.allFiles = allFiles;
    }

    public long getTotalSize() 
    {
        return totalSize;
    }

    public void setTotalSize(long totalSize) 
    {
        this.totalSize = totalSize;
    }

    public long getDurationMillis() 
    {
        return durationMillis;
    }

    public void setDurationMillis(long durationMillis) 
    {
        this.durationMillis = durationMillis;
    }
}
