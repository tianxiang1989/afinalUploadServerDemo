    <%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
    <html>
    <head></head>
    <body style="font-size:30px;">
        <form action="fileupload" method="post"
        enctype="multipart/form-data">
            username:<input name="username"/>
            <br/>
            phone:<input type="file" name="file1"/>
            <br/>
             car:<input type="file" name="file2"/>
            <br/>
            <input type="submit" value="Confirm"/>
        </form>
    </body>
    </html>