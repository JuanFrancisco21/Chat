package com.ajaguilar.Chat;

import Modelo.Message;
import Modelo.Room;
import Modelo.RoomList;
import Modelo.User;
import Utiles.XMLUtil;

public class ejecutable {
	public static void main(String[] args) {
		RoomList Lista=  RoomList.getInstance();
			Lista=XMLUtil.loadXML();
			//System.out.println(XMLUtil.loadXML().getSalas().get(0).getName());
			
		//User u1=new User(1,"Juan");
		//User u2=new User("Pedro");

		/*Room r=new Room("AC");

		Lista.addRoom(r);
		
		Message m=new Message("Hola",u1);
		r.addMenssage(m);
		Message m1=new Message("Mundo",u1);
		r.addMenssage(m1);
*/
			

		
		/*Lista.getSalas().get(0).addUser(u2);
		XMLUtil.writeXML(Lista);*/
		
		Lista.getSalas().forEach(item->{
			System.out.println("NOMBRE DE LA SALA: "+item.getName()+
								"\n--------------------------------\n"+"USUARIOS-ONLINE:\n"+item.getUsers()
								+ "\n---------------------------------\nMENSAJES DE LA SALA: ");
				item.getMessages().forEach(item1->{
				System.out.print(item1)
		;});});
		
		
		//System.out.println(XMLUtil.loadXML().getSalas().get(0).getUsers());

		
		
		
	}

}
