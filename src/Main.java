import java.util.List;
import java.util.Scanner;

import backend.archivo.ControladorArchivo;
import backend.xml.XMLModification;

public class Main {
    
    public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in); 
    System.out.println("Ingrese la direccion ip  de servidor");
     String ip = sc.nextLine();
     System.out.println("Ingrese la contrasena de las extensiones");
     String contra = sc.nextLine();
        final String PATH_CSV  = "phone.csv";
        ControladorArchivo archivo = new ControladorArchivo(PATH_CSV);
        List<String> contenido = archivo.obtenerArchivo();
        System.out.println(contenido.size());
        for (int i = 0; i < contenido.size(); i++) {
            String res = contenido.get(i);
            String[] parts = res.split(",",-1);
       String nombreArchivo = parts[1];
            String name = parts[2];
            String modelo = parts[41];
            String extension = parts[140];
            String modeloXml;
            int  tipo;
            
            if (modelo.equalsIgnoreCase("Cisco 3905")) {
                modeloXml = "modelosXML\\CP-3905\\SEPF07816A2C103.cnf.xml_22205";
                tipo = 1;
            }
            else{
                if (modelo.equalsIgnoreCase("Cisco 7821")) {
                    modeloXml = "modelosXML\\CP-7861\\SEPF07816D0A358.cnf.xml_22200";
                    tipo = 2;
                }
                else{
                      if (modelo.equalsIgnoreCase("Cisco 7861")) {
                        modeloXml="modelosXML\\CP-7861\\SEPF07816D0A358.cnf.xml_22200";
                        tipo = 2;
                      }
                      else {
                        modeloXml ="modelosXML\\CP-8945\\SEP204C9E6CB941.cnf.xml_22202";
                        tipo = 2;
                      }
                    }
            }
           
            XMLModification modification = new XMLModification(nombreArchivo,name,extension,modeloXml,tipo,ip,contra);
            modification.construirArchivos();
        
        
        }
      
    }
}
