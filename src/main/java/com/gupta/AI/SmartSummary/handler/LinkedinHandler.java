package com.gupta.AI.SmartSummary.handler;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Format Should be:
 * Link of Course
 * Name of course
 * <p>
 * List of transcripts.
 */
public class LinkedinHandler {


    public static void storeSummary(LinkedinCourse course, String fileToStore) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileToStore));
            writer.write("Link: " + course.link);
            writer.write(System.lineSeparator());
            writer.write("Name: " + course.courseName);
            writer.write(System.lineSeparator());
            for (Topic topic : course.topics) {
                if (topic.summary != null) {
//                    writer.write(topic.getTopicName());
                    writer.write(System.lineSeparator());
                    writer.write(topic.getSummary());
                    writer.write(System.lineSeparator());
                }
            }
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static LinkedinCourse loadCourse(String file) {
        String link;
        String name;
        LinkedinCourse course;
        BufferedReader reader = null;
        try {
            String topic = "";
            reader = new BufferedReader(new FileReader(file));
            link = reader.readLine();
            name = reader.readLine();
            course = new LinkedinCourse(name, link);
            String line = reader.readLine();
            while (line != null) {
                if (line.length() > 0 && line.length() < 100) {
                    if (topic.isEmpty()) {
                        topic = line;
                    } else {
                        topic = topic + " " + line;
                    }
                } else {
                    if (line.length() > 100) {
                        course.topics.add(new Topic(topic, line));
                        topic = "";
                    }
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return course;
    }

    public static class LinkedinCourse {
        private final String courseName;
        private final String link;

        private List<Topic> topics = new ArrayList<>();

        public LinkedinCourse(String courseName, String link) {
            this.courseName = courseName;
            this.link = link;
            this.topics = new ArrayList<>();
        }

        public String getCourseName() {
            return courseName;
        }

        public String getLink() {
            return link;
        }

        public List<Topic> getTopics() {
            return topics;
        }

        public void setTopics(List<Topic> topics) {
            this.topics = topics;
        }
    }

    public static class Topic {
        private final String topicName;
        private final String transcript;

        public Topic(String topicName, String transcipt) {
            this.topicName = topicName;
            this.transcript = transcipt;
        }

        private String summary;

        public String getTopicName() {
            return topicName;
        }

        public String getTranscript() {
            return transcript;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }
    }
}
