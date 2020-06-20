package com.example.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class test {
	
	public static void main(String[] args) {
		java.sql.Date date = java.sql.Date.valueOf(LocalDate.now());
		java.sql.Time time = java.sql.Time.valueOf(LocalTime.now());
		java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(LocalDateTime.now());
		System.out.println(timestamp);
		
	}

}
