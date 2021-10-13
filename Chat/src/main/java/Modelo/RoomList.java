package Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;




@XmlRootElement 
@XmlAccessorType (XmlAccessType.FIELD)
public class RoomList implements Serializable{
	@XmlTransient
	private static RoomList _instance;

	@XmlElement(name="Room")
	public static List<Room> salas=new ArrayList<Room>();
	
	

	
	
	public static RoomList getInstance(){
        if(_instance==null){
            _instance = new RoomList();
        }
        return _instance;
    }
	protected RoomList() {}
	
	protected RoomList(List<Room> salas) {
		this.salas = salas;
	}
	public boolean addRoom(Room o) {
		boolean result=false;
		if (o.getName()!=null ) {
			try {
				salas.add(o);

			} catch (Exception e) {
				System.out.println("Error al añadir sala");
			}
			
		}
		return result;
	}
	
	public  List<Room> getSalas() {
		return salas;
	}

	public void setSalas(List<Room> salas) {
		this.salas = salas;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((salas == null) ? 0 : salas.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoomList other = (RoomList) obj;
		if (salas == null) {
			if (other.salas != null)
				return false;
		} else if (!salas.equals(other.salas))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ""+salas;
	}
	
	
	
}
