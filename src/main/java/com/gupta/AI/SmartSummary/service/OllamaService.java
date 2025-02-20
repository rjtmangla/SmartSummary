package com.gupta.AI.SmartSummary.service;

import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OllamaService {
    @Autowired
    private OllamaChatModel ollamaChatModel;

    private static final String LINKEDIN_PROMPT=
            "Summarize the following LinkedIn transcript for study purposes, extracting only key concepts while ignoring unrelated details such as author information or prefaces. Present the summary in a well-structured format for easy reference. Transcript: ";

    public String summarizeTranscript(String transcript){
        String prompt=LINKEDIN_PROMPT+" "+transcript;
        return ollamaChatModel.call(prompt);
    }
}
