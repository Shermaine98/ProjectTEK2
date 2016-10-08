/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.forums;

/**
 *
 * @author Shermaine
 */
public class Tags {
    private String tag;
    private int forumID;
    private int createdBy;
    private String forumTitle;

    /**
     * @return the tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * @param tag the tag to set
     */
    public void setTag(String tag) {
        this.tag = tag;
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
    
}
