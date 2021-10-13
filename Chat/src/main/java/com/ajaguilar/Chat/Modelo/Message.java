package com.ajaguilar.Chat.Modelo;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import Utiles.LocalDateAdapter;



@XmlAccessorType (XmlAccessType.FIELD)
public class Message implements Serializable {
	@XmlValue
	protected String mensaje;
	@XmlJavaTypeAdapter(value = LocalDateAdapter.class)
	@XmlAttribute
	protected LocalDateTime fecha;
	@XmlAttribute
	protected User usuario;
	
	
	private Message() {}
	
	public Message(String mensaje, User usuario) {
		this.mensaje = mensaje;
		this.fecha = LocalDateTime.now();
		this.usuario = usuario;
	}
	
	
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((mensaje == null) ? 0 : mensaje.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Message other = (Message) obj;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\n-->FECHA: "+fecha+"\n"+usuario+" \n---->MENSAJE: " + mensaje +" \n" ;
	}
	
	
}
