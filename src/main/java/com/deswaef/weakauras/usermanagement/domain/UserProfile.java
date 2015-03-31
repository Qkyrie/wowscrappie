package com.deswaef.weakauras.usermanagement.domain;

import javax.persistence.*;

@Table(name = "user_profile")
@Entity
public class UserProfile {

    @Id
    @GeneratedValue
    private Long id;

    @Lob
    @Column(name = "about_me")
    private String aboutMe;

    @Column(name = "twitch_stream")
    private String twitchStream;

    @Column(name = "twitter_name")
    private String twitterName;

    @OneToOne
    @JoinColumn(name = "user_id")
    private ScrappieUser scrappieUser;

    public Long getId() {
        return id;
    }

    public UserProfile setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public UserProfile setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
        return this;
    }

    public String getTwitchStream() {
        return twitchStream;
    }

    public UserProfile setTwitchStream(String twitchStream) {
        this.twitchStream = twitchStream;
        return this;
    }

    public String getTwitterName() {
        return twitterName;
    }

    public UserProfile setTwitterName(String twitterName) {
        this.twitterName = twitterName;
        return this;
    }

    public ScrappieUser getScrappieUser() {
        return scrappieUser;
    }

    public UserProfile setScrappieUser(ScrappieUser scrappieUser) {
        this.scrappieUser = scrappieUser;
        return this;
    }
}
