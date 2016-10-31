/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */

package model.forums;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Gian Carlo Roxas
 * @author shermaine Sy
 * @author Geraldine Atayan
 * 
 */

public class Comments {

    private int idComments;
    private int commentedby;
    private String comment;
    private String forumTitle;
    private int createdBy;
    private int forumID;
    private String commentedDate;
    private String dateOfComment;
    private String commentedByName;
    private int commentCounts;
    private boolean isLiked;
    SimpleDateFormat formatters = new SimpleDateFormat("MMM. dd, yyyy hh:mm a");

    /**
     * @return the idComments
     */
    public int getIdComments() {
        return idComments;
    }

    public String getDateOfComment() {
        return dateOfComment;
    }

    public void setDateOfComment(String dateOfComment) {
        this.dateOfComment = dateOfComment;
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
    public String getCommentedDate() {
        return commentedDate;
    }

    /**
     * @param commentedDate the commentedDate to set
     */
    public void setCommentedDate(Date commentedDate) {
        this.commentedDate = formatters.format(commentedDate);
    }

    /**
     * @return the commentedByName
     */
    public String getCommentedByName() {
        return commentedByName;
    }

    /**
     * @param commentedByName the commentedByName to set
     */
    public void setCommentedByName(String commentedByName) {
        this.commentedByName = commentedByName;
    }

    /**
     * @return the commentCounts
     */
    public int getCommentCounts() {
        return commentCounts;
    }

    /**
     * @param commentCounts the commentCounts to set
     */
    public void setCommentCounts(int commentCounts) {
        this.commentCounts = commentCounts;
    }

    /**
     * @return the isLiked
     */
    public boolean isIsLiked() {
        return isLiked;
    }

    /**
     * @param isLiked the isLiked to set
     */
    public void setIsLiked(boolean isLiked) {
        this.isLiked = isLiked;
    }
}
