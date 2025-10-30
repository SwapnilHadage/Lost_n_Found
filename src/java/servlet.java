import utils.DB;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.sql.*;
    
    @WebServlet("/servlet")
    public class servlet extends HttpServlet {
        
        
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            String mail = request.getParameter("mail");
            String password = request.getParameter("password");
            String choice = request.getParameter("login");
            String table = null;

            if(mail == null || mail.trim().isEmpty()){
                out.println("<p style = 'color : red';>Email cannot be empty!!</p>");
                return;
            }
            String mailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            if(! mail.matches(mailPattern)){
                out.println("<p style = 'color : red';>Invalid Email!!</p>");
                return;
            }
                
            
            boolean t = "studentLogin".equals(choice)? true : false;
            if(t){
                table = "student";
            }else{
                table = "staff";
            }
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM "+ table + " WHERE mail=? AND password=?");
            ps.setString(1, mail);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
            // Valid user → Redirect to Respective Paged
                
                String user = rs.getString("name");
                HttpSession shareIt = request.getSession();
                
                shareIt.setAttribute("user", user);
                if(t){
                    shareIt.setAttribute("isStudent", t);
                    RequestDispatcher rd = request.getRequestDispatcher("studentUi.jsp");
                    rd.forward(request, response);
                }else{
                    t = false;
                    shareIt.setAttribute("isStudent", t);
                    RequestDispatcher rd = request.getRequestDispatcher("staffUi.jsp");
                    rd.forward(request, response);
                }
                
            } else {
            // Invalid user → Show error
            out.println("<h3 style='color:red'>Invalid Username or Password</h3>");
            }
        con.close();
        } catch(ServletException | IOException | ClassNotFoundException | SQLException e) {
            out.println("Error: " + e.getMessage());
              }
    }
    }