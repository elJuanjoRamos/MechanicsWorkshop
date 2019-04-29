
package controllers;

import beans.Employee;
import beans.SpareParts;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.filechooser.FileNameExtensionFilter;
import views.Admin.Employee.EmployeeViewController;
import views.Admin.Service.ServicesViewController;
import views.Admin.SpareParts.SparePartsViewController;

public class InterpreterController {

    private static InterpreterController instance;
    
    public static InterpreterController getInstance() {
        if(instance == null) {
            instance = new InterpreterController();
        }
        return instance;
    }

    public InterpreterController() {
    }
    
    
    
    public void openSelecFile(String text){
        
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("IPC Files", text));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
                interpret(selectedFile, text);
            
        }
    }
    
    public static final String SEPARATOR = "-";
    public static final String QUOTE = "\"";

    private static String[] removeTrailingQuotes(String[] fields) {

        String result[] = new String[fields.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = fields[i].replaceAll("^" + QUOTE, "").replaceAll(QUOTE + "$", "");
        }
        return result;
    }

    public void interpret(File file, String text) {
        
        
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;

      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = file;
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);

         // Lectura del fichero
         String line;
         while((line=br.readLine())!=null){
             String[] fields = line.split(SEPARATOR);
             for (int i = 0; i < fields.length; i++) {
                    if (fields[i] != null) {
                        fields = removeTrailingQuotes(fields);
                        
                        if (text.equals("*.tme")) {
                            EmployeesController.getInstance().addLast(fields[i], fields[i+1], fields[i+2], fields[i+3]);
                            EmployeeViewController.getInstance().initTableView();  
             
                        } else if(text.equals("*.tmr")) {
                            SparesPartsController.getInstance().add(fields[i], fields[i+1], fields[i+2], Integer.parseInt(fields[i+3]), Double.parseDouble(fields[i+4]));
                            SparePartsViewController.getInstance().initTableView();
             
                        } else if(text.equals("*.tms")) {
                            String[] parts = fields[i+3].split(";");
                            Stack a = new Stack();
                            Double sPrice = 00.0;
                            for (int j = 0; j < parts.length; j++) {
                                SpareParts s = SparesPartsController.getInstance().search(Integer.parseInt(parts[j]));
                                if (s != null) {
                                    a.add(s);
                                    sPrice = sPrice + s.getPrice();
                                }
                            }
                            ServicesController.getInstance().add(fields[i], fields[i+1], fields[i+2],  a, Double.parseDouble(fields[i+4]), sPrice);
                            ServicesViewController.getInstance().initTableView();
            
                        } else if(text.equals("*.tmca")){
                            System.out.println("---------------------");
                            System.out.println("Ident "  + fields[i]);
                            System.out.println("nombre "  + fields[i+1]);
                            System.out.println("username "  + fields[i+2]);
                            System.out.println("password " + fields[i+3]);
                            System.out.println("tipo " + fields[i+4]);
                            
                            String[] cars = fields[i+5].split(";");
                            for (int j = 0; j < cars.length; j++) {
                                System.out.println( "car " + j + " " + cars[j]);
                                String[] detailsCar = cars[j].split(",");
                                System.out.println("Details Car");
                                for (int k = 0; k < detailsCar.length; k++) {
                                    System.out.println(detailsCar[k]);
                                }
                                
                            }
                            
                            System.out.println("---------------------");
                            
                        }
                        
                        
                  }
                    break;
                }   
         }
            
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
        
        /*       BufferedReader br = null;

        try {

            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line=br.readLine()) != null) {
                System.out.println(line);
                String[] fields = line.split(SEPARATOR);
                System.out.println(fields.length);
                for (int i = 0; i < fields.length; i++) {
                    //if (fields[i] != null) {
                        fields = removeTrailingQuotes(fields);
                        //System.out.println(i);
                        if (text.equals("*.tme")) {
                            EmployeesController.getInstance().addLast(fields[i], fields[i+1], fields[i+2], fields[i+3]);
                            EmployeeViewController.getInstance().initTableView();  
                        
                    //}
                }
                line = br.readLine();
        
            }

        } catch (Exception e) {

        }*/
    }
    
    
    
}
