package com.adipta.disk_analyzer_backend.model;

public class ScanRequest 
{
	private String path;

    public ScanRequest() {}

    public ScanRequest(String path) 
    {
        this.path = path;
    }

    public String getPath() 
    {
        return path;
    }

    public void setPath(String path) 
    {
        this.path = path;
    }
}
