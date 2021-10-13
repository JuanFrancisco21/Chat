package com.ajaguilar.Chat.ModeloDAO;


import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.ajaguilar.Chat.Modelo.Room;
import com.ajaguilar.Chat.Modelo.RoomList;


@XmlSeeAlso(value = { RoomList.class })
@XmlRootElement(name="Rooms")
public class RoomListDAO extends RoomList{
	private static RoomListDAO _instance;

	
	
	public static RoomList getInstance(){
        if(_instance==null){
            _instance = new RoomListDAO();
        }
        return _instance;
    }
	public RoomListDAO() {
		super();
	}
	public  RoomListDAO(List<Room> salas) {
		super(salas);
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

}
