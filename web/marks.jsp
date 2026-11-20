<%@ page import="java.util.List" %>
<%@ page import="com.studentportal.model.Mark" %>

<html>
<head>
    <title>Marks Management</title>

    <style>
        body {
            font-family: Arial;
            margin: 30px;
            background: #f4f6f9;
        }

        h2, h3 {
            color: #263b5e;
        }

        form, table {
            background: white;
            padding: 15px;
            margin-bottom: 20px;
        }

        input {
            padding: 9px;
            margin: 5px;
        }

        button {
            padding: 10px 18px;
            background: #263b5e;
            color: white;
            border: none;
            cursor: pointer;
        }

        .delete {
            background: #c0392b;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: center;
        }

        th {
            background: #263b5e;
            color: white;
        }
    </style>
</head>

<body>

<h2>Marks Management</h2>

<!-- Display Messages -->

<%
    String errorMessage =
        (String) request.getAttribute("errorMessage");

    String successMessage =
        (String) request.getAttribute("successMessage");

    if (errorMessage != null) {
%>
    <p style="color:red;"><%= errorMessage %></p>
<%
    }

    if (successMessage != null) {
%>
    <p style="color:green;"><%= successMessage %></p>
<%
    }
%>


<!-- ADD MARKS -->

<h3>Add Marks</h3>

<form action="marks" method="post">

    <input type="hidden" name="action" value="add">

    <input type="number"
           name="studentId"
           placeholder="Student ID"
           min="1"
           required>

    <input type="number"
           name="subjectId"
           placeholder="Subject ID"
           min="1"
           required>

    <input type="number"
           name="marksObtained"
           placeholder="Marks Obtained"
           min="0"
           max="100"
           step="0.01"
           required>

    <button type="submit">Add Marks</button>

</form>


<!-- DISPLAY MARKS -->

<h3>Marks List</h3>

<%
    List<Mark> marks =
        (List<Mark>) request.getAttribute("marks");
%>

<table>

    <tr>
        <th>Mark ID</th>
        <th>Student ID</th>
        <th>Subject ID</th>
        <th>Marks Obtained</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>

<%
    if (marks != null && !marks.isEmpty()) {

        for (Mark m : marks) {
%>

    <tr>

        <td><%= m.getMarkId() %></td>

        <td><%= m.getStudentId() %></td>

        <td><%= m.getSubjectId() %></td>

        <td><%= m.getMarksObtained() %></td>


        <!-- UPDATE MARKS -->

        <td>

            <form action="marks" method="post">

                <input type="hidden"
                       name="action"
                       value="update">

                <input type="hidden"
                       name="markId"
                       value="<%= m.getMarkId() %>">

                <input type="number"
                       name="studentId"
                       value="<%= m.getStudentId() %>"
                       min="1"
                       required>

                <input type="number"
                       name="subjectId"
                       value="<%= m.getSubjectId() %>"
                       min="1"
                       required>

                <input type="number"
                       name="marksObtained"
                       value="<%= m.getMarksObtained() %>"
                       min="0"
                       max="100"
                       step="0.01"
                       required>

                <button type="submit">
                    Update
                </button>

            </form>

        </td>


        <!-- DELETE MARKS -->

        <td>

            <form action="marks"
                  method="post"
                  onsubmit="return confirm('Are you sure you want to delete this mark?');">

                <input type="hidden"
                       name="action"
                       value="delete">

                <input type="hidden"
                       name="markId"
                       value="<%= m.getMarkId() %>">

                <button type="submit" class="delete">
                    Delete
                </button>

            </form>

        </td>

    </tr>

<%
        }

    } else {
%>

    <tr>
        <td colspan="6">No marks available.</td>
    </tr>

<%
    }
%>

</table>

</body>
</html>
