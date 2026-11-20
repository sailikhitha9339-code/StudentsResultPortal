<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
    <title>Student Result Portal</title>
    <style>
        body {
            font-family: Arial;
            background: #f2f4f8;
            padding: 30px;
        }
        .container {
            background: white;
            max-width: 900px;
            margin: auto;
            padding: 25px;
            border-radius: 10px;
        }
        h2 { text-align: center; color: #243b64; }
        input, button {
            padding: 10px;
            margin: 8px 0;
        }
        button {
            background: #243b64;
            color: white;
            border: none;
            cursor: pointer;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: center;
        }
        th { background: #243b64; color: white; }
        .summary { margin-top: 20px; line-height: 2; }
        .error { color: red; }
    </style>
</head>
<body>

<div class="container">

    <h2>Student Result Portal</h2>

    <form action="result" method="post">
        <label>Enter Student Roll Number:</label><br>
        <input type="text" name="rollNumber"
               placeholder="Enter roll number" required>
        <button type="submit">View Result</button>
    </form>

    <%
        String error = (String) request.getAttribute("errorMessage");
        if (error != null) {
    %>
        <p class="error"><%= error %></p>
    <%
        }

        Boolean found = (Boolean) request.getAttribute("studentFound");

        if (found != null && !found) {
    %>
        <p class="error">Student not found. Please check the roll number.</p>
    <%
        }

        if (found != null && found) {
    %>

        <h3>Student Details</h3>
        <p><b>Name:</b> <%= request.getAttribute("studentName") %></p>
        <p><b>Roll Number:</b> <%= request.getAttribute("rollNumber") %></p>
        <p><b>Department:</b> <%= request.getAttribute("department") %></p>
        <p><b>Semester:</b> <%= request.getAttribute("semester") %></p>

        <%
            List<Map<String, Object>> rows =
                (List<Map<String, Object>>) request.getAttribute("resultRows");

            if (rows == null || rows.isEmpty()) {
        %>
            <p class="error">No marks have been entered for this student.</p>
        <%
            } else {
        %>

        <h3>Subject-wise Marks</h3>

        <table>
            <tr>
                <th>Subject Code</th>
                <th>Subject Name</th>
                <th>Marks Obtained</th>
                <th>Maximum Marks</th>
            </tr>

            <% for (Map<String, Object> row : rows) { %>
            <tr>
                <td><%= row.get("subjectCode") %></td>
                <td><%= row.get("subjectName") %></td>
                <td><%= row.get("obtained") %></td>
                <td><%= row.get("maxMarks") %></td>
            </tr>
            <% } %>
        </table>

        <div class="summary">
            <b>Total Marks:</b>
            <%= request.getAttribute("totalObtained") %> /
            <%= request.getAttribute("totalMaximum") %><br>

            <b>Percentage:</b>
            <%= request.getAttribute("percentage") %>%<br>

            <b>Grade:</b>
            <%= request.getAttribute("grade") %><br>

            <b>Result:</b>
            <%= request.getAttribute("status") %>
        </div>

        <% } %>
    <% } %>

</div>
</body>
</html>
