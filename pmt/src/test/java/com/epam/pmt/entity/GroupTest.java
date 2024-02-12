package com.epam.pmt.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GroupTest {

	@Test
	void groupAndAccountTest()
	{
	  Group group=new Group("education");
	  
	  assertEquals("education", group.getGroupName());
	}
}	