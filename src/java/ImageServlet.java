
import utils.DB;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.System.out;
import java.sql.*;

//@WebServlet("/ImageServlet");
@WebServlet("/ImageServlet")
public class ImageServlet extends HttpServlet {
    
    @Override
            protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
                String id = request.getParameter("id");
                if(id==null){
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing image id");
                    return;
                }
                
                try(Connection con = DB.getConnection()){
                    
                    PreparedStatement ps = con.prepareStatement(
                            "Select image from lost_objects where id=?");
                    ps.setInt(1,Integer.parseInt(id));
                    ResultSet rs = ps.executeQuery();
                    
                    if(rs.next()){
                        Blob image = rs.getBlob("image");
                        
                        response.setContentType("image/jpg");
                        
                        try(InputStream is = image.getBinaryStream();
                            OutputStream os = response.getOutputStream()){
                            
                            byte[] buffer = new byte[1024];
                            int bytesRead;
                            while((bytesRead = is.read(buffer)) != -1){
                                os.write(buffer, 0, bytesRead);
                            }
                        }catch(Exception e){
                            out.println(e);
                        }
                    }else{
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Image not Found for id=" + id);
                    }
                rs.close();
                ps.close();
                con.close();
                }catch( Exception e){
                    
            }
                
            }
}

