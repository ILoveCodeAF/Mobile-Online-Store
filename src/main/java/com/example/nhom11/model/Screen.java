package com.example.nhom11.model;

public class Screen {

	private long id;
	private String technology, resolution;
	private float size;
	
	public Screen() {
		super();
	}

	public Screen(long id, String technology, String resolution, float size) {
		super();
		this.id = id;
		this.technology = technology;
		this.resolution = resolution;
		this.size = size;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}
	
}
