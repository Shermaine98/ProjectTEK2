package model.forums;

import java.util.ArrayList;

/**
 *
 * @author Shermaine
 */
public class Forums {
    private int forumID;
    private String forumTitle;
    private int createdBy;
    private String createdByName;
    private String body;
    private String dateCreated;
    private int favoritesCount;
    private int CommentsCount;
    private boolean isLike;
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
    public String getDateCreated() {
        return dateCreated;
    }

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * @return the favoritesCount
     */
    public int getFavoritesCount() {
        return favoritesCount;
    }

    /**
     * @param favoritesCount the favoritesCount to set
     */
    public void setFavoritesCount(int favoritesCount) {
        this.favoritesCount = favoritesCount;
    }

    /**
     * @return the CommentsCount
     */
    public int getCommentsCount() {
        return CommentsCount;
    }

    /**
     * @param CommentsCount the CommentsCount to set
     */
    public void setCommentsCount(int CommentsCount) {
        this.CommentsCount = CommentsCount;
    }

    /**
     * @return the createdByName
     */
    public String getCreatedByName() {
        return createdByName;
    }

    /**
     * @param createdByName the createdByName to set
     */
    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    /**
     * @return the isLike
     */
    public boolean isIsLike() {
        return isLike;
    }

    /**
     * @param isLike the isLike to set
     */
    public void setIsLike(boolean isLike) {
        this.isLike = isLike;
    }    
}