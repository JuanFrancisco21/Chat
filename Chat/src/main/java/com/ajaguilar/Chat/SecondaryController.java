package com.ajaguilar.Chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import Modelo.Room;
import Modelo.User;
import Utiles.Dialog;
import Utiles.XMLUtil;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

public class SecondaryController {
	@FXML
	public ObservableList<Room> salas;
	@FXML
	public ObservableList<User> usuarios;
	@FXML
	public TextField nombre;
	@FXML
	private ComboBox roomChoice=new ComboBox();
	
	public User usuario=new User("");

	

	/**
	 * Método que se ejecuta al iniciar la pantalla.
	 */
	@FXML
	protected void initialize() {
		
		try {
		configRoomChoice();
		
		Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                	PrimaryController.updateUserOnline();
                	PrimaryController.updateMessagesOnline();
                });
            }
        }, 0, 5000);
		

		} catch (Exception e) {
			Dialog.showError("Inicializacion", "Ha surgido un error al Iniciar Primary Controller","");
		}
	}
	/**
	 * Función para actualizar lista de salas .
	 */
	private void xmlCargar() {
		checkUser();
		if (salas!=null && usuarios!=null) {
			try {
				//Carga las Habitaciones nuevas--------------------------------------------------------------
				List<Room> salaNue=XMLUtil.loadXML().getSalas();
				for( Iterator nu = salaNue.iterator(); nu.hasNext();) { 
					Room n = (Room)nu.next();
					if (!salas.contains(n)) {
						salas.add(n);
					}
				}
				
			} catch (Exception e) {
				Dialog.showError("Configurar xml", "Ha surgido un error al configurar el xml","");
			}
		}
		
	}
	/**
	 * Verifica si esta disponible el nombre de usuario.
	 * @return booleano true=esta ocupado el nombre.
	 */
	@FXML
	private boolean checkUser() {
		boolean result=false;
		if (roomChoice.getValue()!=null && nombre.getText()!=null) {
			try {
				
				int sel=roomChoice.getSelectionModel().getSelectedIndex();
				this.usuarios=FXCollections.observableArrayList();
				this.usuarios.setAll(XMLUtil.loadXML().getSalas().get(sel).getUsers());
				User us = new User(nombre.getText());

				for( Iterator it = usuarios.iterator(); it.hasNext();) { 
				   User x = (User)it.next();
				   if (x.equals(us)!=false) {
					result=true;

				   }
				}
				usuarios.add(us);
				salas.get(sel).getUsers().add(us);
				if (result==true) {
					Dialog.showWarning("Usuario", "Usuario no disponible","Cambia de nombre");
				}
			} catch (Exception e) {
				Dialog.showError("Tabla", "Ha surgido un error al checkear usuario ","");

			}
		}
		return result;
	}
	/**
	 * Configura las salas disponibles del xml en el seleccionador de la interfaz.
	 */
	@FXML
	private void configRoomChoice() {
		this.salas=FXCollections.observableArrayList();
		this.salas.setAll(XMLUtil.loadXML().getSalas());
		try {
			ObservableList<String> habList = FXCollections.observableArrayList();
			salas.forEach(result->{
				habList.add(""+result.getName());
			});
			roomChoice.setItems(habList);


		} catch (Exception e) {
			Dialog.showError("Configurar Eleccion De Sala", "Ha surgido un error al configurar Las Salas Disponibles","");
		}
	}
    
	/**
	 * Comprueba si los datos son correctos y cambia de pantalla a la principal.
	 * @throws IOException
	 */
    @FXML
    private void switchToPrimary() throws IOException {
    	try {
    		if (nombre.getCharacters()!=null && nombre.getCharacters().length()>=4 && checkUser()!=true && usuarios!=null) {    			
    			String name=nombre.getText();
            	usuario.setNombre(name);
            	
            	int sel=roomChoice.getSelectionModel().getSelectedIndex();
            	
            	Set<User>usus= new HashSet<User>();
    			usuarios.forEach(item->{usus.add(item);});

                PrimaryController.inilist(usuario, sel, usus);
            	App.setRoot("primary");
    		}
		} catch (Exception e) {
			Dialog.showError("Error con usuario","Cambie nombre y selecciona habitación", "Nombre de usuario ocupado");

		}
        
    }
    
    
    
    
    
    
    
}