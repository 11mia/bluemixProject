package com.ibmMeeting.Dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface AdminDao {
	
	Integer loginCheck(HashMap<String,Object> loginInformation);

}