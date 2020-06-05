package com.example.nhom11.model;

public class Phone {
	
	private long id;
	private String name, manufacturer;
	private int rom, ram;
	private String cpu;
	private float frontCamera, behindCamera;
	private String os;
	private int battery;
	private String image;
	private Screen screen;
	private float price;
	
	public Phone() {
		super();
		screen = new Screen();
	}

	public Phone(long id, String name, String manufacturer, int rom, int ram, String cpu, float frontCamera,
			float behindCamera, String os, int battery, String image, Screen screen, float price) {
		super();
		this.id = id;
		this.name = name;
		this.manufacturer = manufacturer;
		this.rom = rom;
		this.ram = ram;
		this.cpu = cpu;
		this.frontCamera = frontCamera;
		this.behindCamera = behindCamera;
		this.os = os;
		this.battery = battery;
		this.image = image;
		this.screen = screen;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}


	public int getRom() {
		return rom;
	}

	public void setRom(int rom) {
		this.rom = rom;
	}

	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}


	public float getFrontCamera() {
		return frontCamera;
	}

	public void setFrontCamera(float frontCamera) {
		this.frontCamera = frontCamera;
	}

	public float getBehindCamera() {
		return behindCamera;
	}

	public void setBehindCamera(float behindCamera) {
		this.behindCamera = behindCamera;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public int getBattery() {
		return battery;
	}

	public void setBattery(int battery) {
		this.battery = battery;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Phone [id=" + id + ", name=" + name + ", manufacturer=" + manufacturer + ", rom=" + rom + ", ram=" + ram
				+ ", cpu=" + cpu + ", frontCamera=" + frontCamera + ", behindCamera=" + behindCamera + ", os=" + os
				+ ", battery=" + battery + ", image=" + image + ", screen=" + screen.toString() + ", price=" + price + "]";
	}

}
