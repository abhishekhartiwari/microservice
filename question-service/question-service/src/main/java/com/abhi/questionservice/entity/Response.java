package com.abhi.questionservice.entity;

public class Response {

	private Long id;
	String response;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public Response(Long id, String response) {
		super();
		this.id = id;
		this.response = response;
	}
	public Response() {
		super();
	}
	@Override
	public String toString() {
		return "Response [id=" + id + ", response=" + response + "]";
	}



}
