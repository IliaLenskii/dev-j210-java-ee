<%@ page session="true" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>It's lab2 from DEV-J210. Java EE</title>

    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-size: 14px;
            font-family: Georgia, 'Times New Roman', Times, serif;
        }
        html, body{
            background-color: #ededed;
            width: 100%;
            height: 100%;
        }
        .container {
            width: 400px;
            height: inherit;
            margin-left: auto;
            margin-right: auto;
        }
        .form-select-button {
            border: 1px solid #ccc;
            display: block;
            padding: 6px;
            text-align: center;
        }
        input[type="text"], button, textarea {
            display: inline-block;
            padding: 4px;
            width: 100%;
        }

        textarea {
            height: 50px;
            resize: none;
        }

        button[type="submit"] {
            width: 45%;
            color: green;
        }
        button[type="reset"] {
            width: 25%;
        }
        .tp-pars {
            margin-top: 14px;
            margin-bottom: 14px;
        }
        .tp-pars label,
        .tp-pars input {
            display: inline-block;
        } .tp-pars input {
            margin-left: 8px;
        }
        .tp-pars label:first-child {
            margin-right: 24px;
        }
        
        .message-block {
            margin-top: 40%;
            margin-bottom: 18px;
            text-align: center;
            font-size: 18px;
        }
    </style>
</head>
<body>
    <div class="container">

        <div class="message-block"></div>

        <form class="form-select-button" action="index-request" method="post" autocomplete="false" enctype="multipart/form-data">
            <input type="text" name="user" maxlength="150" placeholder="User is..." value="user1">
            <br />
            <br />
            <input type="text" name="msg" maxlength="150" placeholder="Msg is..." value="Протечка водоблока на видеокарту RTX 3090 Inno3D">
            <br />
            <br />
            <button type="submit">Send</button>
        </form>
    </div>
</body>
</html>