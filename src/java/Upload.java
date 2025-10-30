import utils.DB;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import static java.lang.System.out;
import java.sql.*;
@WebServlet("/Upload")
@MultipartConfig(maxFileSize = 16177215)
public class Upload extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        PrintWriter out = response.getWriter();
        Part image = request.getPart("image");
        String imageDescription = request.getParameter("description");
        HttpSession session = request.getSession(false);
        String userName;
        boolean isStudent=false;
        if(session !=null){
            userName = (String) session.getAttribute("user");
            Boolean isStudentObj = (Boolean)session.getAttribute("isStudent");
            isStudent = (isStudentObj != null && isStudentObj);
        }else{
            response.sendRedirect("index.html");
            return;
        } if(imageDescription != null && (image != null && image.getSize()>0)){
            try(Connection con = DB.getConnection()){
                String sqlQuery = "insert into lost_objects (image, name, status) values(?,?,?)";
                PreparedStatement ps = con.prepareStatement(sqlQuery); ps.setBlob(1, image.getInputStream());
                ps.setString(2,imageDescription);
                if(!isStudent){ ps.setString(3,"approved");
                }else{
                    ps.setString(3,"pending");
                }
                ps.executeUpdate();
                out.println("<p style='color:green;'>Image uploaded successfully!</p>");
                RequestDispatcher rd;
                if(isStudent){
                    rd = request.getRequestDispatcher("studentUi.jsp");
                }else{
                    rd = request.getRequestDispatcher("staffUi.jsp");
                }
                rd.include(request, response);
                con.close();
            } catch (SQLException ex){
                ex.printStackTrace();
                out.println("<p style='color:red;'>Error: " + ex.getMessage() + "</p>");
            }
        }else{
            out.println("<p style='color:red;'>Please select an image to upload.</p>");
        }
    }
}