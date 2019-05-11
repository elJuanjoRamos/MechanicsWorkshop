/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Client;
import beans.SpareParts;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Jose Morente
 */
public class ReportHTMLController {
    private static ReportHTMLController instance;
    public static ReportHTMLController getInstance() {
        if(instance == null) {
            instance = new ReportHTMLController();
        }
        return instance;
    }
    
    /**
     * VARIABLES
     */
    private SparePartsAssignment tope;
    private FileWriter reporte;
    private PrintWriter rep;
    
    /**
     * HEADER
     * @return header
     */
    public String getHeader(String title) {
        return "<!DOCTYPE html>\n" +
        "<html>\n" +
        "<head>\n" +
        "    <meta charset='utf-8'>\n" +
        "    <meta http-equiv='X-UA-Compatible' content='IE=edge'>\n" +
        "    <title>Reporte "+title+"</title>\n" +
        "    <meta name='viewport' content='width=device-width, initial-scale=1'>\n" +
        "    <link rel='stylesheet' type='text/css' media='screen' href='main.css'>\n" +
        "    <script src='main.js'></script>\n" +
        "    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">\n" +
        "</head>" +
        "<body>\n" +
        "  <nav class=\"navbar navbar-light bg-light\">\n" +
        "    <span class=\"navbar-brand mb-0 h1\">Mechanics Workshop</span>\n" +
        "  </nav>";
    }
    
    /**
     * FOOTER
     * @param oro
     * @param normal
     * @return footer
     */
    public String getFooterPie(int oro, int normal) {
        return "  <script src=\"https://cdn.jsdelivr.net/npm/chart.js@2.8.0\"></script>\n" +
        "  <script>\n" +
        "    var ctx = document.getElementById('myChart').getContext('2d');\n" +
        "    var myChart = new Chart(ctx, {\n" +
        "        type: 'pie',\n" +
        "        data: {\n" +
        "            labels: ['Gold', 'Normal'],\n" +
        "            datasets: [{\n" +
        "                label: '# of Votes',\n" +
        "                data: [" + oro + ", " + normal + "],\n" +
        "                backgroundColor: [\n" +
        "                    'rgba(212, 175, 57, 0.8)',\n" +
        "                    'rgba(0, 59, 121, 0.8)'\n" +
        "                ],\n" +
        "                borderColor: [\n" +
        "                    'rgba(212, 175, 57, 0.8)',\n" +
        "                    'rgba(0, 59, 121, 0.8)'\n" +
        "                ],\n" +
        "                borderWidth: 1\n" +
        "            }]\n" +
        "        },\n" +
        "        options: {\n" +
        "            scales: {\n" +
        "                yAxes: [{\n" +
        "                    ticks: {\n" +
        "                        beginAtZero: true\n" +
        "                    }\n" +
        "                }]\n" +
        "            }\n" +
        "        }\n" +
        "    });\n" +
        "  </script>\n" +
        "  <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\"></script>\n" +
        "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\"></script>\n" +
        "  <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\"></script>\n" +
        "</body>\n" +
        "</html>";
    }
    
    
    public String getFooterBar() {
        return "";
    }
    
    /**
     * REPORTE DE CLIENTES
     */
    public void getReportClient() {
        System.out.println("SI ENTRO");
        try {
            int oro = 0;
            int normal = 0;
            reporte = new FileWriter("clientes.html");
            rep = new PrintWriter(reporte);
            rep.print(getHeader("Clientes"));
            rep.print("<div class=\"container\">\n" +
            "    <div class=\"jumbotron jumbotron-fluid\">\n" +
            "      <div class=\"container\">\n" +
            "        <h1 class=\"display-4\">Clientes</h1>\n" +
            "        <p class=\"lead\">Reporte de todos los clientes.</p>\n" +
            "      </div>\n" +
            "    </div>\n" +
            "    <table class=\"table\">\n" +
            "      <thead class=\"thead-dark\">\n" +
            "        <tr>\n" +
            "          <th scope=\"col\">DPI</th>\n" +
            "          <th scope=\"col\">NOMBRE</th>\n" +
            "          <th scope=\"col\">USUARIO</th>\n" +
            "          <th scope=\"col\">CONTRASEÑA</th>\n" +
            "          <th scope=\"col\">TIPO CLIENTE</th>\n" +
            "        </tr>\n" +
            "      </thead>"+ 
            "<tbody>");
            if(!ClientsController.getInstance().isEmpty()) {
                Client aux = ClientsController.getInstance().start;
                do {
                    if(aux.getRole().equalsIgnoreCase("gold")) {
                        oro++;
                    } else if(aux.getRole().equalsIgnoreCase("normal")) {
                        normal++;
                    }
                    rep.print("<tr>\n" +
                    "     <th scope=\"row\">" + aux.getDpi() + "</th>\n" +
                    "     <td>" + aux.getFullName()+ "</td>\n" +
                    "     <td>" + aux.getUsername()+ "</td>\n" +
                    "     <td>" + aux.getPassword()+ "</td>\n" +
                    "     <td>" + aux.getRole()+ "</td>\n" +
                    "</tr>");
                    aux = aux.getNext();
                } while(aux != ClientsController.getInstance().start);
            }
            rep.print("</tbody>\n" +
            "    </table>\n" +
            "    <canvas id=\"myChart\" style=\"height:400; width:800\"></canvas>\n" +
            "  </div>");
            rep.print(getFooterPie(oro, normal));
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "clientes.html"); 
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                if(reporte != null) {
                    reporte.close();
                }
            } catch (IOException e) {
                System.err.println("e");
            }
        }
    }
    
    public void getTopSpareParts() {
        //IMPRIMIR LISTA SIN ORDENAR
        tope = null;
        SpareParts aux = null;
        aux = SparesPartsController.getInstance().first;
        while (aux != null) {   
            addAssignment(new SparePartsAssignment(aux));
            aux = aux.getNext();
        }
        
        SparePartsAssignment aux1, aux2;
        aux2 = tope;
        while (aux2 != null) {
            aux1 = aux2.getNext();
            while (aux1 != null) {
                if (aux1.getSparePart().getPrice() > aux2.getSparePart().getPrice()) {
                    SpareParts auxiliar = aux2.getSparePart();
                    aux2.setSparePart(aux1.getSparePart());
                    aux1.setSparePart(auxiliar);
                }
                aux1 = aux1.getNext();
            }
            aux2 = aux2.getNext();
        }
        
        try {
            reporte = new FileWriter("spare-parts.html");
            rep = new PrintWriter(reporte);
            rep.print(getHeader("Spare Parts"));
            rep.print("<div class=\"container\">\n" +
            "    <div class=\"jumbotron jumbotron-fluid\">\n" +
            "      <div class=\"container\">\n" +
            "        <h1 class=\"display-4\">Clientes</h1>\n" +
            "        <p class=\"lead\">Top 5 de los repuestos mas caros.</p>\n" +
            "      </div>\n" +
            "    </div>\n" +
            "    <table class=\"table\">\n" +
            "      <thead class=\"thead-dark\">\n" +
            "        <tr>\n" +
            "          <th scope=\"col\">ID</th>\n" +
            "          <th scope=\"col\">NAME</th>\n" +
            "          <th scope=\"col\">MARK</th>\n" +
            "          <th scope=\"col\">MODEL</th>\n" +
            "          <th scope=\"col\">STOCK</th>\n" +
            "          <th scope=\"col\">PRICE</th>\n" +
            "        </tr>\n" +
            "      </thead>"+ 
            "<tbody>");
            int contador = 1;
            SparePartsAssignment auxiliar1 = tope;
            if(auxiliar1 != null) {
                while(auxiliar1 != null) {
                    if(contador <= 5) {
                        System.out.println(contador);
                        rep.print("<tr>\n" +
                        "     <th scope=\"row\">" + auxiliar1.getSparePart().getId() + "</th>\n" +
                        "     <td>" + auxiliar1.getSparePart().getName() + "</td>\n" +
                        "     <td>" + auxiliar1.getSparePart().getMark() + "</td>\n" +
                        "     <td>" + auxiliar1.getSparePart().getModel() + "</td>\n" +
                        "     <td>" + auxiliar1.getSparePart().getStock() + "</td>\n" +
                        "     <td>" + auxiliar1.getSparePart().getPrice() + "</td>\n" +
                        "</tr>");
                        contador++;
                    }
                    auxiliar1 = auxiliar1.getNext();
                }
            }
            rep.print("</tbody>\n" +
            "    </table>\n" +
            "    <canvas id=\"myChart\" style=\"height:400; width:800\"></canvas>\n" +
            "  </div>\n" +
            "<script src=\"https://cdn.jsdelivr.net/npm/chart.js@2.8.0\"></script>\n" +
            "  <script>\n" +
            "    var ctx = document.getElementById('myChart').getContext('2d');\n" +
            "    var myChart = new Chart(ctx, {\n" +
            "        type: 'bar',\n" +
            "        data: {\n" +
            "            labels: [");
            int contador2 = 1;
            SparePartsAssignment auxiliar2 = tope;
            if(auxiliar2 != null) {
                while(auxiliar2 != null) {
                    System.out.println(contador2);
                    if(contador2 <= 5) {
                        rep.print("'" + auxiliar2.getSparePart().getName() + "',\n");
                        contador2++;
                    }
                    auxiliar2 = auxiliar2.getNext();
                }
            }
            rep.print("],\n" +
            "            datasets: [{\n" +
            "                label: '# of Votes',\n" +
            "                data: [");
            int contador3 = 1;
            SparePartsAssignment auxiliar3 = tope;
            if(auxiliar3 != null) {
                while(auxiliar3 != null) {
                    if(contador3 <= 5) {
                        rep.print(auxiliar3.getSparePart().getPrice() + ",\n");
                        contador3++;
                    }
                    auxiliar3 = auxiliar3.getNext();
                }
            }
            rep.print("],\n" +
            "                backgroundColor: [\n" +
            "                    'rgba(0, 59, 121, 0.6)',\n" +
            "                    'rgba(0, 59, 121, 0.6)',\n" +
            "                    'rgba(0, 59, 121, 0.6)',\n" +
            "                    'rgba(0, 59, 121, 0.6)',\n" +
            "                    'rgba(0, 59, 121, 0.6)',\n" +
            "                ],\n" +
            "                borderColor: [\n" +
            "                    'rgba(0, 59, 121, 0.8)',\n" +
            "                    'rgba(0, 59, 121, 0.8)',\n" +
            "                    'rgba(0, 59, 121, 0.8)',\n" +
            "                    'rgba(0, 59, 121, 0.8)',\n" +
            "                    'rgba(0, 59, 121, 0.8)',\n" +
            "                ],\n" +
            "                borderWidth: 1\n" +
            "            }]\n" +
            "        },\n" +
            "        options: {\n" +
            "            scales: {\n" +
            "                yAxes: [{\n" +
            "                    ticks: {\n" +
            "                        beginAtZero: true\n" +
            "                    }\n" +
            "                }]\n" +
            "            }\n" +
            "        }\n" +
            "    });\n" +
            "  </script>\n" +
            "  <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\"></script>\n" +
            "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\"></script>\n" +
            "  <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\"></script>\n" +
            "</body>\n" +
            "</html>");
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "spare-parts.html"); 
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                if(reporte != null) {
                    reporte.close();
                }
            } catch (IOException e) {
                System.err.println("e");
            }
        }
    }
        
    //ADD SPARE PART ASSIGNAMENT
    void addAssignment(SparePartsAssignment temp){
        if(tope == null){
            tope = temp;
        }else{
            SparePartsAssignment aux1, aux2;
            aux2 = tope;
            aux1 = tope.getNext();
            while(aux1!=null){
                aux1 = aux1.getNext();
                aux2 = aux2.getNext();
            }
            aux2.setNext(temp);
        }
    }
}

class SparePartsAssignment {
    private SpareParts sparePart;
    private SparePartsAssignment next;

    public SparePartsAssignment() {
    }

    public SparePartsAssignment(SpareParts sparePart) {
        this.sparePart = sparePart;
    }

    /**
     * @return the sparePart
     */
    public SpareParts getSparePart() {
        return sparePart;
    }

    /**
     * @param sparePart the sparePart to set
     */
    public void setSparePart(SpareParts sparePart) {
        this.sparePart = sparePart;
    }

    /**
     * @return the next
     */
    public SparePartsAssignment getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(SparePartsAssignment next) {
        this.next = next;
    }
    
}

