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
public class CommentsFavorite {
    private int idComment;
    private int favoriteBy;

    /**
     * @return the idComment
     */
    public int getIdComment() {
        return idComment;
    }

    /**
     * @param idComment the idComment to set
     */
    public void setIdComment(int idComment) {
        this.idComment = idComment;
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
