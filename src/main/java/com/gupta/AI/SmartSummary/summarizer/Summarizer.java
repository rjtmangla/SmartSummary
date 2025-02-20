package com.gupta.AI.SmartSummary.summarizer;

import com.gupta.AI.SmartSummary.handler.LinkedinHandler;
import com.gupta.AI.SmartSummary.service.OllamaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Summarizer {

    Logger logger = LoggerFactory.getLogger(Summarizer.class);
    @Autowired
    private OllamaService ollamaService;

    public void linkedInSummarizer(String fileToSummarize, String fileToStore) {
        LinkedinHandler.LinkedinCourse course = LinkedinHandler.loadCourse(fileToSummarize);
        logger.info("Course is ready for summarization. name={} , number of topics={}", course.getCourseName(), course.getTopics().size());
        for (LinkedinHandler.Topic topic : course.getTopics()) {
            topic.setSummary(ollamaService.summarizeTranscript(topic.getTranscript()));
            logger.info("Summarize for topic={}", topic.getTopicName());
        }
        LinkedinHandler.storeSummary(course, fileToStore);
    }
}
