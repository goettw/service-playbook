package serviceplaybook.controller.viewmodel;

import serviceplaybook.model.Comment;

public class CommentViewBean {
    private boolean allowDelete;
    private Comment comment;

    public CommentViewBean(Comment comment) {
	this.comment = comment;
    }

    public Comment getComment() {
	return comment;
    }

    public boolean isAllowDelete() {
	return allowDelete;
    }

    public void setAllowDelete(boolean allowDelete) {
	this.allowDelete = allowDelete;
    }
}
