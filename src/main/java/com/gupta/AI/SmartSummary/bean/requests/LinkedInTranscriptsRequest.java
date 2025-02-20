package com.gupta.AI.SmartSummary.bean.requests;

public class LinkedInTranscriptsRequest {
   private String fileToSummarize;
    private String fileToStore;

    public String getFileToSummarize() {
        return fileToSummarize;
    }

    public void setFileToSummarize(String fileToSummarize) {
        this.fileToSummarize = fileToSummarize;
    }

    public String getFileToStore() {
        return fileToStore;
    }

    public void setFileToStore(String fileToStore) {
        this.fileToStore = fileToStore;
    }
}
