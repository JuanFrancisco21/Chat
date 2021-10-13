package com.ajaguilar.Chat;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


import Modelo.Message;
import Modelo.Room;
import Modelo.RoomList;
import Modelo.User;
import Utiles.Dialog;
import Utiles.XMLUtil;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

public class PrimaryController {
	@FXML
	public static ObservableList<Room> salas;
	@FXML
	public static ObservableList<Message> mensajes;
	@FXML
	public static ObservableList<User> usuarios;
	@FXML
	private ComboBox roomChoice=new ComboBox();
	@FXML
	public static int roomindex=-1;
	@FXML
	public static User usuario=new User("");
			
	@FXML
	private TableView<Message> Habitacion;
	@FXML
	private TableColumn<Message, LocalTime> FechaColum;
	@FXML
	private TableColumn<Message, String> NameUColum;
	@FXML
	private TableColumn<Message,String> MensajeColum;
	

	@FXML
	private TableView<User> Online;
	@FXML
	private TableColumn<User,String> OnlineUsu;

	@FXML
	public TextField mensaje;
	
	/**
	 * Método que se ejecuta al iniciar la pantalla.
	 */
	@FXML
	protected void initialize() throws IOException {
		
		try {
			configuraTablas();
			
		} catch (Exception e) {
			Dialog.showError("Inicializacion", "Ha surgido un error al Iniciar Primary Controller","");
		}
		
	}
	/**
	 * Configura los datos cargados del xml a la interfaz grafica.
	 */
	@FXML
	private void configuraTablas() {
		if (roomindex>=0) {
			try {
				this.mensajes=FXCollections.observableArrayList();
				this.mensajes.setAll(salas.get(roomindex).getMessages());
			

				FechaColum.setCellValueFactory(cellData->{
					return new SimpleObjectProperty<>(cellData.getValue().getFecha().toLocalTime());
				});

				
				NameUColum.setCellValueFactory(cellData->{
					return new SimpleObjectProperty<>(cellData.getValue().getUsuario().getNombre());
				});
				NameUColum.setCellFactory(TextFieldTableCell.forTableColumn());
				
				
				MensajeColum.setCellValueFactory(cellData->{
					return new SimpleObjectProperty<>(cellData.getValue().getMensaje());
				});
				MensajeColum.setCellFactory(TextFieldTableCell.forTableColumn());
				
				
				Habitacion.setItems(mensajes);
				
				
				
				OnlineUsu.setCellValueFactory(cellData->{
					return new SimpleObjectProperty<>(cellData.getValue().getNombre());
				});
				OnlineUsu.setCellFactory(TextFieldTableCell.forTableColumn());
				Online.setItems(usuarios);
			} catch (Exception e) {
				Dialog.showError("Tabla", "Ha surgido un error al añadir los mensajes","");

			}
			
		}
		
	}
	
	/**
	 * Añade un nuevo mensaje a la sala donde se esta.
	 * @throws IOException
	 */
	@FXML
    private void addmessage() throws IOException {
    	if (mensaje.getText()!=null && mensaje.getText()!="") {
    		try { 
    			Message mine=new Message(mensaje.getText(), usuario);
        		mensajes.add(mine);
        		salas.get(roomindex).getMessages().add(mine);
        		mensaje.setText(null);
        		
        		save();
    		} catch (Exception e) {
    			System.out.println("error al añadir message ");
    		}
		}
    	
    }
	
	/**
	 * Método para cambiar de pantalla.
	 * @throws IOException
	 */
    @FXML
    private void switchToSecondary() throws IOException {
    	try {
    		quitUser();
    		App.setRoot("secondary");
		} catch (Exception e) {
			System.out.println("error al cambiar escena ");
		}
    	
    }
    /**
     * Inicializa valores del secondarycontroller en el primarycontroller.
     * @param user usuario con el que entramos.
     * @param roominde número de la habitación a la que accedemos.
     * @param list lista de usuarios.
     */
	public static void inilist(User user, int roominde, Set<User> list) {
		salas=FXCollections.observableArrayList();
		salas.setAll(XMLUtil.loadXML().getSalas());
		usuarios=FXCollections.observableArrayList();
		
		usuarios.setAll(list);
		usuario.setNombre(user.getNombre());
    	roomindex=roominde;
    	salas.get(roomindex).setUsers(list);
    	
    	save();
		
	}
	/**
	 * Elimina nuestro usuario de la sala al salirnos de la misma.
	 */
	public static void quitUser() {
		if (usuario!=null) {
			usuarios.remove(usuario);
			salas.get(roomindex).getUsers().remove(usuario);
			save();
		}
		
	}
	/**
	 * Actualiza usuarios online
	 */
	public static void updateUserOnline() {
		if (usuarios!=null) {
			try {
				List<User> t=new ArrayList();
				t.addAll(XMLUtil.loadXML().getSalas().get(roomindex).getUsers());
				//Añade los nuevos user online del xml a ram
				for( Iterator it = t.iterator(); it.hasNext();) { 
					User e = (User)it.next();
					if (!usuarios.contains(e)) {
						usuarios.add(e);
						salas.get(roomindex).getUsers().add(e);
					}
				}
				try {
					//Borra los user online de la ram que no esten en xml.
					for( Iterator it = usuarios.iterator(); it.hasNext();) { 

						User e = (User)it.next();
						if (!t.contains(e)) {
							usuarios.remove(e);
							salas.get(roomindex).getUsers().remove(e);

						}
					}
				} catch (Exception e2) {
					System.out.print("");
				}
			} catch (Exception e) {
				Dialog.showError("Tabla", "Ha surgido un error actualizar usuarios ","");

			}
		}
	}
	/**
	 * Actualiza los mensajes nuevos del xml.
	 */
	public static void updateMessagesOnline() {
		if (mensajes!=null) {
			try {
				List<Message> t=new ArrayList();
				t.addAll(XMLUtil.loadXML().getSalas().get(roomindex).getMessages());
				//Añade los nuevos mensajes del xml a ram.
				for( Iterator it = t.iterator(); it.hasNext();) { 
					Message e = (Message)it.next();
					if (!mensajes.contains(e)) {
						mensajes.add(e);
						salas.get(roomindex).getMessages().add(e);
					}
				}
				
				save();
			} catch (Exception e) {
				Dialog.showError("Tabla", "Ha surgido un error al actualizar mensajes ","");

			}
		}
	}
	/**
	 * Método de guardado en el xml.
	 */
	public static void save() {
    	//Guarda en xml (añade nuestro usuarioa la sala)
    	RoomList saved =RoomList.getInstance();
    	saved.setSalas(salas);
    	XMLUtil.writeXML(saved);
		
	}
    
    
}
