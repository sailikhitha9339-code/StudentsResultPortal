
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Student Result Portal - Login</title>
    <style>
        body {
            font-family: Arial;
            background: #f0f4f8;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .login-box {
            background: white;
            padding: 30px;
            width: 330px;
            border-radius: 10px;
            box-shadow: 0 0 10px #ccc;
        }

        h2 {
            text-align: center;
            color: #263c63;
        }

        input, select, button {
            width: 100%;
            padding: 11px;
            margin-top: 12px;
            box-sizing: border-box;
        }

        button {
            background: #263c63;
            color: white;
            border: none;
            cursor: pointer;
        }
    </style>
</head>
<body>

<div class="login-box">
    <h2>Student Result Portal</h2>
    <h3>Login</h3>

    <form action="LoginServlet" method="post">

        <input type="text" name="username"
               placeholder="Enter Username" required>

        <input type="password" name="password"
               placeholder="Enter Password" required>

        <select name="role" required>
            <option value="">Select Role</option>
            <option value="STUDENT">Student</option>
            <option value="TEACHER">Teacher</option>
        </select>

        <button type="submit">Login</button>

    </form>
</div>

</body>
</html>
