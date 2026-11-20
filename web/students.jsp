<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.studentportal.model.Student"%>

<!DOCTYPE html>
<html>
<head>
    <title>Student Management System</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f2f2f2;
            padding: 20px;
        }

        .container {
            width: 95%;
            margin: auto;
            background: white;
            padding: 20px;
            border-radius: 8px;
        }

        h2, h3 {
            text-align: center;
        }

        .add-form {
            background: #eeeeee;
            padding: 20px;
            margin-bottom: 25px;
        }

        input {
            padding: 8px;
            margin: 5px;
            box-sizing: border-box;
            width: 95%;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid #cccccc;
            padding: 10px;
            text-align: center;
        }

        th {
            background: #333333;
            color: white;
        }

        button {
            padding: 8px 12px;
            border: none;
            color: white;
            background: #007bff;
            cursor: pointer;
            margin: 3px;
        }

        .add {
            background: green;
        }

        .delete {
            background: red;
        }

        .error {
            color: red;
            text-align: center;
        }
    </style>
</head>

<body>

<div class="container">

    <h2>STUDENT MANAGEMENT SYSTEM</h2>

    <%
        String error =
            (String) request.getAttribute("errorMessage");

        if (error != null) {
    %>
        <p class="error"><%= error %></p>
    <%
        }
    %>

    <!-- ADD STUDENT -->

    <div class="add-form">

        <h3>Add New Student</h3>

        <form action="<%= request.getContextPath() %>/students"
              method="post">

            <input type="hidden" name="action" value="add">

            <input type="text" name="rollNumber"
                   placeholder="Roll Number" required>

            <input type="text" name="name"
                   placeholder="Student Name" required>

            <input type="email" name="email"
                   placeholder="Email" required>

            <input type="text" name="department"
                   placeholder="Department" required>

            <input type="number" name="semester"
                   placeholder="Semester" min="1" required>

            <button type="submit" class="add">
                Add Student
            </button>

        </form>

    </div>

    <!-- DISPLAY STUDENTS -->

    <h3>Student Details</h3>

    <%
        List<Student> students =
            (List<Student>) request.getAttribute("students");
    %>

    <table>

        <tr>
            <th>Student ID</th>
            <th>Roll Number</th>
            <th>Student Details / Update</th>
            <th>Delete</th>
        </tr>

        <%
            if (students != null && !students.isEmpty()) {

                for (Student s : students) {
        %>

        <tr>

            <td>
                <%= s.getStudentId() %>
            </td>

            <td>
                <%= s.getRollNumber() %>
            </td>

            <td>

                <form action="<%= request.getContextPath() %>/students"
                      method="post">

                    <input type="hidden" name="action"
                           value="update">

                    <input type="hidden" name="studentId"
                           value="<%= s.getStudentId() %>">

                    <input type="text" name="name"
                           value="<%= s.getName() %>"
                           placeholder="Name" required>

                    <input type="email" name="email"
                           value="<%= s.getEmail() %>"
                           placeholder="Email" required>

                    <input type="text" name="department"
                           value="<%= s.getDepartment() %>"
                           placeholder="Department" required>

                    <input type="number" name="semester"
                           value="<%= s.getSemester() %>"
                           min="1" required>

                    <button type="submit">
                        Update
                    </button>

                </form>

            </td>

            <td>

                <form action="<%= request.getContextPath() %>/students"
                      method="post"
                      onsubmit="return confirm('Are you sure you want to delete this student?');">

                    <input type="hidden" name="action"
                           value="delete">

                    <input type="hidden" name="studentId"
                           value="<%= s.getStudentId() %>">

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
            <td colspan="4">
                No students found.
            </td>
        </tr>

        <%
            }
        %>

    </table>

</div>

</body>
</html>

