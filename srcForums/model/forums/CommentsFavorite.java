/*
 *  ProjectTEK - DLSU CCS 2016
 * 
 */

package model.forums;

/**
 *
 * @author Gian Carlo Roxas
 * @author shermaine Sy
 * @author Geraldine Atayan
 * 
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
