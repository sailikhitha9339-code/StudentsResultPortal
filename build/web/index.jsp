<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String role = (String) session.getAttribute("role");
    String username = (String) session.getAttribute("username");

    if (role == null || username == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Student Result Management System</title>
    <style>
        * { box-sizing: border-box; }

        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: #f1f5f9;
        }

        header {
            background: #243b64;
            color: white;
            padding: 25px;
            text-align: center;
        }

        nav {
            background: #172b4d;
            padding: 15px;
            text-align: center;
        }

        nav a {
            color: white;
            text-decoration: none;
            margin: 0 20px;
        }

        .container {
            padding: 35px;
            text-align: center;
        }

        .cards {
            display: flex;
            justify-content: center;
            flex-wrap: wrap;
            gap: 25px;
            margin-top: 35px;
        }

        .card {
            background: white;
            width: 260px;
            padding: 30px 20px;
            border-radius: 10px;
            box-shadow: 0 3px 12px #ccc;
        }

        .card h3 { color: #243b64; }

        .card a {
            display: inline-block;
            background: #243b64;
            color: white;
            padding: 12px 22px;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 12px;
        }

        .card a:hover { background: #4169a1; }

        footer {
            text-align: center;
            padding: 20px;
            margin-top: 40px;
            color: #555;
        }
    </style>
</head>

<body>

<header>
    <h1>Student Result Management System</h1>
    <p>Manage Student Records, Subjects, Marks and Results</p>
    <p>Welcome, <%= username %> (<%= role %>)</p>
</header>

<nav>
    <a href="index.jsp">Home</a>

    <% if ("TEACHER".equals(role)) { %>
        <a href="students">Students</a>
        <a href="subjects">Subjects</a>
        <a href="marks">Marks</a>
    <% } %>
 <a href="result.jsp">Results</a>
    <a href="LogoutServlet">Logout</a>
    <a href="result.jsp">Results</a>
</nav>

<div class="container">

    <h2>Welcome to Student Result Portal</h2>

    <% if ("TEACHER".equals(role)) { %>

        <p>Select a section to manage.</p>

        <div class="cards">

            <div class="card">
                <h3>Student Management</h3>
                <p>Add, update, view and delete student details.</p>
                <a href="students">Manage Students</a>
            </div>

            <div class="card">
                <h3>Subject Management</h3>
                <p>Manage subject codes, names and maximum marks.</p>
                <a href="subjects">Manage Subjects</a>
            </div>

            <div class="card">
                <h3>Marks Management</h3>
                <p>Add, update and delete student marks.</p>
                <a href="marks">Manage Marks</a>
            </div>

            <div class="card">
                <h3>View Student Result</h3>
                <p>Check student marks, percentage, grade and result.</p>
                <a href="result.jsp">View Result</a>
            </div>

        </div>

    <% } else if ("STUDENT".equals(role)) { %>

        <p>View your academic result below.</p>

        <div class="cards">
            <div class="card">
                <h3>My Result</h3>
                <p>View your marks, percentage, grade and result.</p>
                <a href="result.jsp">View My Result</a>
            </div>
        </div>

    <% } else { %>
        <p>Invalid user role. Please log in again.</p>
    <% } %>

</div>

<footer>
    <p>Student Result Management System | Advanced Java Term Work</p>
</footer>

</body>
</html>
