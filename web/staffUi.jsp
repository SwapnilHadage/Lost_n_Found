<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="utils.DB" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Staff Dashboard</title>

  <style>
    *{
      margin: 0;
      padding: 0;
        }
        body {
          font-family: "Poppins", sans-serif;
          background: #f4f6f9;
          margin: 0;
          padding: 0;
          scroll-behavior: smooth;
        }
        header{
            margin: 0;
            padding:0;
            display: flex;
            flex-direction: column;
            gap: 0;
        }
        header  .header-1{
            margin-top: 0;
            width: 100vw;
            background-color: #0d47a1;
            color: white;
            text-align: left;
            font-size: 1.5rem;
            font-weight: 700;
            letter-spacing: 0.5px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
          }
        header .header-2{
          background-color: #2e7aeb;
          margin-top: 0;
          padding-left: 10rem;
/*          display: flex;
          align-items: center;*/
          
      }
      header .header-2 h1{
          font-size: 1.3rem;
          text-align: left;
          
         
      }
      .header-1 h1, .header-2 h1{
          color: white;
          margin: 1rem;
      }

      
    main {
      max-width: 1000px;
      margin: 40px auto;
      padding: 20px;
    }
     
    main h1 {
      text-align: center;
      color: #333;
      margin-bottom: 30px;
    }
    
    input{
        border: none;
        
    }
    
    /* Upload form */
    .found-items {
      background: #fff;
      padding: 25px;
      border-radius: 12px;
      box-shadow: 0 4px 10px rgba(0,0,0,0.1);
      margin-bottom: 40px;
    }

    .found-items form {
      display: flex;
      flex-wrap: wrap;
      gap: 15px;
      align-items: center;
      justify-content: center;
    }

    .found-items input[type="file"],
    .found-items input[type="text"],
    .found-items input[placeholder] {
      padding: 10px;
      border-radius: 8px;
      border: 1px solid #ccc;
      width: 250px;
    }

    .found-items button {
      background: #007bff;
      color: white;
      border: none;
      padding: 10px 18px;
      border-radius: 8px;
      cursor: pointer;
      transition: background 0.3s ease;
    }

    .found-items button:hover {
      background: #0056b3;
    }
    
    #messageBox{
       text-align: center;
       height: 2rem;
    }
    /* Lost items list */
    
    .main-content{
        display: flex;
        flex-direction: row;
        flex-wrap: nowrap;
        gap: 5px;
        
    }
    
    .approved-items {
      width: 50%;
    }
    .pending-items{
        width: 50%;
    }
    .header-3{
      padding: 15px;
      margin: 0.5rem;
      text-align: center;
      font-weight: 500;
      background-color: #0033ff;
      border-radius: 12px;
      color: white;
      font-family: sans-serif;
      font-size: 1.2rem;
    }
    
    .lost-item {
      background: #fff;
      border-radius: 12px;
      box-shadow: 0 3px 8px rgba(0,0,0,0.08);
      overflow: hidden;
      text-align: center;
      padding: 15px;
      transition: transform 0.2s ease;
      margin: 0.5rem;
    }

    .lost-item:hover {
      transform: translateY(-4px);
    }

    .lost-item img {
      width: 100%;
      height: 180px;
      object-fit: cover;
      border-radius: 10px;
    }

    .img-desc {
      font-weight: 500;
      margin: 10px 0 5px;
      color: #444;
    }

    .img-date {
      font-size: 0.9em;
      color: #777;
      margin-bottom: 10px;
    }

    .lost-item button {
      background: #dc3545;
      color: white;
      border: none;
      padding: 8px 14px;
      border-radius: 6px;
      cursor: pointer;
      transition: background 0.3s;
    }

    .lost-item button:hover {
      background: #b02a37;
    }
    
    #app-btn{
        background-color: #00cc33;
    }

    @media (max-width: 600px) {
      .found-items form {
        flex-direction: column;
      }
      .found-items input, .found-items button {
        width: 100%;
      }
    }
  </style>
</head>
<body>
    <header>
        <div class="header-1">
            <h1>Lost And Found</h1>
        </div>
        <div class="header-2">
            <h1>Welcome <%String user = (String)session.getAttribute("user"); %>
            <%= user %></h1>
        </div>
    </header>

    <main>
      
    <div class="found-items">
      <form action="Upload" method="post" enctype="multipart/form-data">
        <input type="file" id="fileInput" accept="image/*" name="image" required>
        <input type="text" placeholder="Describe the object and its location" name="description" required>
        <button type="submit">Upload</button>
      </form>
    </div>
    <div id="messageBox">
        <% 
            String msg = (String) request.getAttribute("message");
            if (msg != null && !msg.isEmpty()) {
        %>
            <p><%= msg %></p>
        <% 
            } 
        %>
    </div>


    <div class="main-content">
        
        <div class="approved-items">
            <div class=" header-3">Found Items</div>
      <% 
        try {
          Connection con = DB.getConnection();
          PreparedStatement ps = con.prepareStatement("SELECT * FROM lost_objects WHERE status=?");
          ps.setString(1,"approved");
          ResultSet rs = ps.executeQuery();

          while (rs.next()) {
            int id = rs.getInt("id");
            String imageDescription = rs.getString("name");
            Timestamp uploadDate = rs.getTimestamp("upload_date");
      %>
      
      <div class="lost-item">
        <img src="ImageServlet?id=<%=id%>" alt="Loading Image" loading="lazy" />
        <p class="img-desc"><%= imageDescription %></p>
        <p class="img-date"><%= uploadDate %></p>
        <button onclick="window.location.href='DeleteObject?id=<%=id%>'">Delete</button>
      </div>

      <% 
          }
          con.close();
        } catch (Exception e) {
          out.println("Error: " + e.getMessage());
        }
      %>
    </div>
    
     <div class="pending-items ">
         <div class=" header-3">Upload Requests</div>
      <% 
        try {
          Connection con = DB.getConnection();
          PreparedStatement ps = con.prepareStatement("SELECT * FROM lost_objects WHERE status=?");
          ps.setString(1,"pending");
          ResultSet rs = ps.executeQuery();

          while (rs.next()) {
            int id = rs.getInt("id");
            String imageDescription = rs.getString("name");
            Timestamp uploadDate = rs.getTimestamp("upload_date");
            String status = rs.getString("status");
      %>
      
      <div class="lost-item">
        <img src="ImageServlet?id=<%=id%>" alt="Loading Image" loading="lazy" />
        <p class="img-desc"><%= imageDescription %></p>
        <p class="img-date"><%= uploadDate %></p>
        <p class="status">Status: <%=status %></p>
        <br>
        <button onclick="window.location.href='DeleteObject?id=<%=id%>'">Delete</button>
        <button id="app-btn" onclick="window.location.href='ApproveObject?id=<%=id%>'">Approve</button>
      </div>

      <% 
          }
          con.close();
        } catch (Exception e) {
          out.println("Error: " + e.getMessage());
        }
      %>
    </div>
    </div>
  </main>

</body>
</html>
