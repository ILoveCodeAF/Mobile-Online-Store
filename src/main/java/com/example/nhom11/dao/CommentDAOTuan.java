package com.example.nhom11.dao;

import java.util.List;

import com.example.nhom11.model.Comment;
import com.example.nhom11.model.Phone;

public interface CommentDAOTuan {

	//Neu them thanh cong se tra ve id cua comment
	//Neu them that bai id se la 0
	public Comment add(Comment c);
	
	//Load Add Comment of a Phone
	public List<Comment> loadAllComment(Phone phone);
	
}
