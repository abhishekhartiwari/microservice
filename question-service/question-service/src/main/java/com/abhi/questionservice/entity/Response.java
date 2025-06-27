package com.abhi.questionservice.entity;

public class Response {

	private String id;
	String response;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public Response(String id, String response) {
		super();
		this.id = id;
		this.response = response;
	}
	public Response() {
		super();
	}



}
