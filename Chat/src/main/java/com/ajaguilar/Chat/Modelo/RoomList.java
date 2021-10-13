package com.ajaguilar.Chat.Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement 
@XmlAccessorType (XmlAccessType.FIELD)
public class RoomList implements Serializable{
	@XmlElement(name="Room")
	protected List<Room> salas=new ArrayList<Room>();
	
	
	protected RoomList() {}
	
	public RoomList(List<Room> salas) {
		this.salas = salas;
	}

	
	public List<Room> getSalas() {
		return salas;
	}

	public void setSalas(List<Room> salas) {
		this.salas = salas;
	}

	@Override
	public String toString() {
		return ""+salas;
	}

	
	
}
