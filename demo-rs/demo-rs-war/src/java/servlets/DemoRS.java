package servlets;

import common.Comm;
import java.io.IOException;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


/**
 *
 * @author il.lenskii
 */
@WebServlet(name = "DemoRS", urlPatterns = {"/demo-rs"})
public class DemoRS extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        boolean isPost = "POST".equals(request.getMethod());
        String mainPage = request.getContextPath();
        String urlToWebSer = Comm.serverUrl.substring(0, Comm.serverUrl.length() -1) + mainPage +"/webresources/dir/";
        
        if(!isPost) {
            
            response.sendRedirect(mainPage);
            return;
        }

        String postText = request.getParameter("text");
        String postIsFind = request.getParameter("isfind");
        String postFindText = request.getParameter("findtext");
        
        String fullPathToDump = getServletContext().getRealPath(Comm.pathToDump);
        
        if(postText != null && postText.trim().length() > 0) {
            
        // There may be security issues here
        // postText = "../afsdf/../fsdgsdg";
        //Path adfasd = Paths.get(fullPathToDump, postText);

            postText = Paths.get(fullPathToDump, postText).toString();
        } else {
            
            postText = fullPathToDump;
        }

        if(postIsFind != null) {
            
            urlToWebSer += "find";
        }

        Client client = ClientBuilder.newClient();
        WebTarget wt = client.target(urlToWebSer);

        String encDir = URLEncoder.encode(postText);
        
        String result = null;
        
        if(postIsFind != null && postFindText != null) {

            result = wt.path("{directory}")
                    .resolveTemplate("directory", encDir)
                    .queryParam("file", postFindText)
                    .request().get(String.class);
            
            
        } else if(postText != null) {
            
            result = wt.path("{directory}")
                    .resolveTemplate("directory", encDir)
                    .request().get(String.class);
        }

        if(result != null) {
            
            System.out.println(result);
        }

        response.sendRedirect( mainPage );
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
