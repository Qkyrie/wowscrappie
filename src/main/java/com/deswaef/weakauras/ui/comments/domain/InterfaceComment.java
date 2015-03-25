package com.deswaef.weakauras.ui.comments.domain;

import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "interface_comment")
@DiscriminatorColumn(name="interface_type")
public class InterfaceComment {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "post_date")
    private Date postDate;

    @ManyToOne
    @JoinColumn(name = "commenter_id")
    private ScrappieUser commenter;

    public Long getId() {
        return id;
    }

    public InterfaceComment setId(Long id) {
        this.id = id;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public InterfaceComment setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public ScrappieUser getCommenter() {
        return commenter;
    }

    public InterfaceComment setCommenter(ScrappieUser commenter) {
        this.commenter = commenter;
        return this;
    }

    public Date getPostDate() {
        return postDate;
    }

    public InterfaceComment setPostDate(Date postDate) {
        this.postDate = postDate;
        return this;
    }
}
