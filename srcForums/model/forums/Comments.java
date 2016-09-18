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
public class Comments {
   private int idComments;
   private int commentedby;
   private String comment;

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
}
