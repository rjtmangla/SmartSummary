package com.gupta.AI.SmartSummary.service;

import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class OllamaService {
    @Autowired
    private OllamaChatModel ollamaChatModel;

    private static final String LINKEDIN_PROMPT =
            "Summarize the following transcript from linkedin related to the study material, ignoring any unrelated information like author details or prefaces. Present the key concepts in a structured format: ";

    public String summarizeTranscript(String transcript) {
        String prompt = LINKEDIN_PROMPT + " " + transcript;
        String response = ollamaChatModel.call(prompt);
        return filterForThink(response);
    }

    /**
     * This method will be used when we are using deepseek LLM
     * @param response
     * @return
     */
    private String filterForThink(String response) {
        if (response.contains("<think>")) {
            Pattern pattern = Pattern.compile("<think>.*?</think>", Pattern.DOTALL);
            Matcher matcher = pattern.matcher(response);
            return matcher.replaceAll("");
        }
        return response;
    }
}
