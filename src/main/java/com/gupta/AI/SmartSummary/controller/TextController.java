package com.gupta.AI.SmartSummary.controller;


import com.gupta.AI.SmartSummary.bean.requests.LinkedInTranscriptsRequest;
import com.gupta.AI.SmartSummary.summarizer.Summarizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/text")
public class TextController {

    @Autowired
    private Summarizer summarizer;
    @PostMapping("/linkedin")
    public String linkedIn(@RequestBody LinkedInTranscriptsRequest request){
        summarizer.linkedInSummarizer(request.getFileToSummarize(),request.getFileToStore());
        return "Summary stored";
    }
}
