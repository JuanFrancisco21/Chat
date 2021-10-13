package Utiles;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import Modelo.RoomList;



public class XMLUtil {
    public static String file="chats.xml";
    /**
     * Metodo para cargar datos del archivo .xml
     * @return devuelve una lista de habitaciones.
     */
    public static RoomList loadXML() {
    	RoomList result= RoomList.getInstance();       
        File f=new File(file);
        if(f.canRead()){
            try{
                JAXBContext context=JAXBContext.newInstance(RoomList.class);
                Unmarshaller um = context.createUnmarshaller();
                RoomList conec = (RoomList) um.unmarshal(f);
                result=conec;
            }catch(JAXBException ex){
                ex.printStackTrace();
                result=RoomList.getInstance();
            }
        }
        return result;
    }
    /**
     * Metodo para guardar datos en el archivo .xml
     * @param data Objeto con los datos de las salas a gurdar.
     */
    public static void writeXML(RoomList data){
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(RoomList.class);
            Marshaller m=context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            RoomList conec = RoomList.getInstance();
            m.marshal(data, new File(file));
        } catch (JAXBException ex) {
            ex.printStackTrace();
            Dialog.showError("ERROR", "Error reading "+file, ex.toString());
        }
               
    }
}
