package model.forums;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Shermaine
 */
public class Forums {
    private int forumID;
    private String forumTitle;
    private int createdBy;
    private String body;
    private int reportCount;
    private Date dateCreated;
    private ArrayList<Comments> comments = new ArrayList<Comments>();
    private ArrayList<Tags> tags = new ArrayList<Tags>();
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
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * @return the reportCount
     */
    public int getReportCount() {
        return reportCount;
    }

    /**
     * @param reportCount the reportCount to set
     */
    public void setReportCount(int reportCount) {
        this.reportCount = reportCount;
    }

    /**
     * @return the comments
     */
    public ArrayList<Comments> getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(ArrayList<Comments> comments) {
        this.comments = comments;
    }

    /**
     * @return the tags
     */
    public ArrayList<Tags> getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(ArrayList<Tags> tags) {
        this.tags = tags;
    }

    /**
     * @return the dateCreated
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
   
    
}