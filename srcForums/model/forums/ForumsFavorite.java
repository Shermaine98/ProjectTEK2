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
public class ForumsFavorite {
    private String forumTitle;
    private int createdBy;
    private int forumID;
    private int favoriteBy;

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
     * @return the favoriteBy
     */
    public int getFavoriteBy() {
        return favoriteBy;
    }

    /**
     * @param favoriteBy the favoriteBy to set
     */
    public void setFavoriteBy(int favoriteBy) {
        this.favoriteBy = favoriteBy;
    }
}
