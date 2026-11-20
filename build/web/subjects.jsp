<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.studentportal.model.Subject"%>

<!DOCTYPE html>
<html>
<head>
    <title>Subject Management System</title>

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
            color: #333;
        }

        .add-form {
            background: #eeeeee;
            padding: 20px;
            margin-bottom: 25px;
            border-radius: 5px;
        }

        input {
            padding: 9px;
            margin: 5px;
            box-sizing: border-box;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
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
            padding: 9px 14px;
            border: none;
            background: #007bff;
            color: white;
            cursor: pointer;
            border-radius: 4px;
            margin: 3px;
        }

        .add {
            background: green;
        }

        .delete {
            background: #dc3545;
        }

        .error {
            color: red;
            text-align: center;
        }

        a {
            text-decoration: none;
            color: #007bff;
        }
    </style>
</head>

<body>

<div class="container">

    <h2>SUBJECT MANAGEMENT SYSTEM</h2>

    <p>
        <a href="<%= request.getContextPath() %>/students">
            Student Management
        </a>
    </p>

    <%
        String errorMessage =
            (String) request.getAttribute("errorMessage");

        if (errorMessage != null) {
    %>

        <p class="error"><%= errorMessage %></p>

    <%
        }
    %>

    <!-- ADD SUBJECT -->

    <div class="add-form">

        <h3>Add New Subject</h3>

        <form action="<%= request.getContextPath() %>/subjects"
              method="post">

            <input type="hidden" name="action" value="add">

            <input type="text"
                   name="subjectCode"
                   placeholder="Subject Code"
                   required>

            <input type="text"
                   name="subjectName"
                   placeholder="Subject Name"
                   required>

            <input type="number"
                   name="maxMarks"
                   placeholder="Maximum Marks"
                   min="1"
                   required>

            <button type="submit" class="add">
                Add Subject
            </button>

        </form>

    </div>

    <!-- DISPLAY SUBJECTS -->

    <h3>Subject Details</h3>

    <%
        List<Subject> subjects =
            (List<Subject>) request.getAttribute("subjects");
    %>

    <table>

        <tr>
            <th>Subject ID</th>
            <th>Subject Code</th>
            <th>Subject Name</th>
            <th>Maximum Marks</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>

        <%
            if (subjects != null && !subjects.isEmpty()) {

                for (Subject subject : subjects) {
        %>

        <tr>

            <td>
                <%= subject.getSubjectId() %>
            </td>

            <td>
                <%= subject.getSubjectCode() %>
            </td>

            <td>
                <%= subject.getSubjectName() %>
            </td>

            <td>
                <%= subject.getMaxMarks() %>
            </td>

            <!-- UPDATE SUBJECT -->

            <td>

                <form action="<%= request.getContextPath() %>/subjects"
                      method="post">

                    <input type="hidden"
                           name="action"
                           value="update">

                    <input type="hidden"
                           name="subjectId"
                           value="<%= subject.getSubjectId() %>">

                    <input type="text"
                           name="subjectCode"
                           value="<%= subject.getSubjectCode() %>"
                           required>

                    <input type="text"
                           name="subjectName"
                           value="<%= subject.getSubjectName() %>"
                           required>

                    <input type="number"
                           name="maxMarks"
                           value="<%= subject.getMaxMarks() %>"
                           min="1"
                           required>

                    <button type="submit">
                        Update
                    </button>

                </form>

            </td>

            <!-- DELETE SUBJECT -->

            <td>

                <form action="<%= request.getContextPath() %>/subjects"
                      method="post"
                      onsubmit="return confirm('Are you sure you want to delete this subject?');">

                    <input type="hidden"
                           name="action"
                           value="delete">

                    <input type="hidden"
                           name="subjectId"
                           value="<%= subject.getSubjectId() %>">

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
            <td colspan="6">
                No subjects found.
            </td>
        </tr>

        <%
            }
        %>

    </table>

    <br>

    <p>
        <a href="<%= request.getContextPath() %>/students">
            Back to Student Management
        </a>
    </p>

</div>

</body>
</html>

