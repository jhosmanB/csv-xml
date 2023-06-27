import java.util.List;

import backend.archivo.ControladorArchivo;

public class Main {
    
    public static void main(String[] args) throws Exception {
         final String PATH_CSV  = "phone.csv";
        ControladorArchivo archivo = new ControladorArchivo(PATH_CSV);
        List<String> contenido = archivo.obtenerArchivo();
        for (int i = 0; i < contenido.size(); i++) {
            String res = contenido.get(i);
            String[] parts = res.split("\\,");
            
    if (parts.length > 190) {
       String nombreArchivo = parts[0];
            String name = parts[190];
            String tipo = parts[41];
            String extension = parts[140];
            String modeloXml;
            if (tipo.equalsIgnoreCase("Cisco 3905")) {
                modeloXml = "modelosXML\\CP-3905\\SEPF07816A2C103.cnf.xml_22205";
            }
            else{
                if (tipo.equalsIgnoreCase("Cisco 7821")) {
                    modeloXml = "modelosXML\\CP-7861\\SEPF07816D0A358.cnf.xml_22200";
                }
                else{
                      if (tipo.equalsIgnoreCase("Cisco 7861")) {
                        modeloXml="modelosXML\\CP-7861\\SEPF07816D0A358.cnf.xml_22200";
                      }
                      else {
                        modeloXml ="modelosXML\\CP-8945\\SEP204C9E6CB941.cnf.xml_22202";
                      }
                    }
            }
        }
        
        }
      
    }
}
