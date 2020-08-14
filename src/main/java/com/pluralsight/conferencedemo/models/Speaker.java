package com.pluralsight.conferencedemo.models;

// @Entity(name = "speakers")
public class Speaker {

    private Long speaker_id;
    private String speaker_first_name;
    private String speaker_last_name;
    private String title;
    private String company;
    private String speaker_bio;

    public Speaker() {
    }

    public Long getSpeaker_id() {
        return speaker_id;
    }

    public void setSpeaker_id(Long speaker_id) {
        this.speaker_id = speaker_id;
    }

    public String getSpeaker_first_name() {
        return speaker_first_name;
    }

    public void setSpeaker_first_name(String speaker_first_name) {
        this.speaker_first_name = speaker_first_name;
    }

    public String getSpeaker_last_name() {
        return speaker_last_name;
    }

    public void setSpeaker_last_name(String speaker_last_name) {
        this.speaker_last_name = speaker_last_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSpeaker_bio() {
        return speaker_bio;
    }

    public void setSpeaker_bio(String speaker_bio) {
        this.speaker_bio = speaker_bio;
    }
}
