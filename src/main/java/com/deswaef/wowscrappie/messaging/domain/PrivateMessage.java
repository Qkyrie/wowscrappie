package com.deswaef.wowscrappie.messaging.domain;

import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "private_message")
public class PrivateMessage {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "content")
    private String content;
    @Column(name = "dateOfPosting")
    private Date dateOfPosting;

    @ManyToOne
    @JoinColumn(name = "from_user_id")
    private ScrappieUser fromUser;

    @ManyToOne
    @JoinColumn(name = "to_user_id")
    private ScrappieUser toUser;

    @ManyToOne
    @JoinColumn(name = "response_to_private_message_id")
    private PrivateMessage responseTo;

    public Long getId() {
        return id;
    }

    public PrivateMessage setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public PrivateMessage setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public PrivateMessage setContent(String content) {
        this.content = content;
        return this;
    }

    public Date getDateOfPosting() {
        return dateOfPosting;
    }

    public PrivateMessage setDateOfPosting(Date dateOfPosting) {
        this.dateOfPosting = dateOfPosting;
        return this;
    }

    public ScrappieUser getFromUser() {
        return fromUser;
    }

    public PrivateMessage setFromUser(ScrappieUser fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    public ScrappieUser getToUser() {
        return toUser;
    }

    public PrivateMessage setToUser(ScrappieUser toUser) {
        this.toUser = toUser;
        return this;
    }

    public PrivateMessage getResponseTo() {
        return responseTo;
    }

    public PrivateMessage setResponseTo(PrivateMessage responseTo) {
        this.responseTo = responseTo;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrivateMessage that = (PrivateMessage) o;

        if (!content.equals(that.content)) return false;
        if (!dateOfPosting.equals(that.dateOfPosting)) return false;
        if (!id.equals(that.id)) return false;
        if (responseTo != null ? !responseTo.equals(that.responseTo) : that.responseTo != null) return false;
        if (!title.equals(that.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + content.hashCode();
        result = 31 * result + dateOfPosting.hashCode();
        result = 31 * result + (responseTo != null ? responseTo.hashCode() : 0);
        return result;
    }
}
