/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.forums;

import java.util.Date;

/**
 *
 * @author Shermaine
 */
public class Comments {
   private int idComments;
   private int commentedby;
   private String comment;
private String forumTitle;
private int createdBy;
private int forumID;
private Date commentedDate;
    /**
     * @return the idComments
     */
    public int getIdComments() {
        return idComments;
    }

    /**
     * @param idComments the idComments to set
     */
    public void setIdComments(int idComments) {
        this.idComments = idComments;
    }

    /**
     * @return the commentedby
     */
    public int getCommentedby() {
        return commentedby;
    }

    /**
     * @param commentedby the commentedby to set
     */
    public void setCommentedby(int commentedby) {
        this.commentedby = commentedby;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the forumTitle
     */
    public String getForumTitle() {
        return forumTitle;
    }

    /**
     * @param forumTitle the forumTitle to set
     */
    public void setForumTitle(String forumTitle) {
        this.forumTitle = forumTitle;
    }

    /**
     * @return the createdBy
     */
    public int getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the forumID
     */
    public int getForumID() {
        return forumID;
    }

    /**
     * @param forumID the forumID to set
     */
    public void setForumID(int forumID) {
        this.forumID = forumID;
    }

    /**
     * @return the commentedDate
     */
    public Date getCommentedDate() {
        return commentedDate;
    }

    /**
     * @param commentedDate the commentedDate to set
     */
    public void setCommentedDate(Date commentedDate) {
        this.commentedDate = commentedDate;
    }
}
