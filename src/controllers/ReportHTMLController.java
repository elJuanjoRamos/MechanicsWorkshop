/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Client;
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
    private FileWriter reporte;
    private PrintWriter rep;
    
    /**
     * HEADER
     * @return header
     */
    public String getHeader() {
        return "<!DOCTYPE html>\n" +
        "<html>\n" +
        "<head>\n" +
        "    <meta charset='utf-8'>\n" +
        "    <meta http-equiv='X-UA-Compatible' content='IE=edge'>\n" +
        "    <title>Reporte Clientes</title>\n" +
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
        "            labels: ['Oro', 'Normal'],\n" +
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
            rep.print(getHeader());
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
                    if(aux.getRole().equalsIgnoreCase("oro")) {
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
}
