package Modelo;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType (XmlAccessType.FIELD)
public class Room implements Serializable{
	@XmlAttribute
	protected String name;
	
	@XmlElementWrapper(name="Online-Users")
    @XmlElement(name="User")
	protected Set<User> users=new HashSet<User>();

	@XmlElementWrapper(name="mensajes")
    @XmlElement(name="message")
	protected List<Message> messages=new ArrayList<Message>();
	
	
	
	
	public Room() {}
	
	public Room(String name, List<Message> messages) {
		super();
		this.name = name;
		this.messages = messages;
	}
	public Room(String name, Message message) {
		this.name=name;
		this.messages.add(message);
	}
	public Room(String name) {
		this.name=name;
	}
	public boolean addMenssage(Message m) {
		boolean result=false;
		if (m!=null ) {
			try {
				messages.add(m);

			} catch (Exception e) {
				System.out.println("Error al añadir mensaje");
			}
			
		}
		return result;
	}
	public boolean addUser(User u) {
		boolean result=false;
		if (u!=null ) {
			try {
				users.add(u);

			} catch (Exception e) {
				System.out.println("Error al añadir usuario-onile");
			}
			
		}
		return result;
	}
	public boolean removeUser(User u) {
		boolean result=false;
		if (u!=null ) {
			try {
				users.add(u);
				result=true;
			} catch (Exception e) {
				System.out.println("Error al añadir usuario-onile");
			}
			
		}
		return result;
	}
	/*public boolean searchUser(String u) {
		boolean result=false;

		if (u!=null ) {
			try {
				users.forEach(user->{
					if (user.nombre.equals(u)) {
						 result=true;
					}
				});

			} catch (Exception e) {
				System.out.println("Error al buscar usuario-online");
			}
			
		}else {}
		return result;
	}*/
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((messages == null) ? 0 : messages.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Room other = (Room) obj;
		if (messages == null) {
			if (other.messages != null)
				return false;
		} else if (!messages.equals(other.messages))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Room  Name=" + name + "\nUsuariosOnline: \n" + users+",\nMensajes: \n" + messages+"" ;
	}

	
	
}
