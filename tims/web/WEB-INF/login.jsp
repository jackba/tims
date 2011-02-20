<%-- 
    Document   : login
    Created on : 10 Aug 2010, 4:31:21 PM
    Author     : abismail
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<center>
    <!--<input name="unm" type="text">
        <br/>
        <input name="pwd" type="text">
        <br/>
        <script type="javascript">
            function func1(){
                alert(window.document.unm.value);
                alert(window.document.pwd.value);
                //document.write(window.document.pwd.value);
                //document.write(window.document.unm.value);
                alert("login-btn");
            }

        </script>
        <input name="btn1" type="button" value="login" onClick="func1()">-->
    <form action="j_spring_security_check" method="POST">
        <label for="j_username">Username</label>
        <input type="text" name="j_username" id="j_username" />
        <br/>
        <label for="j_password">Password</label>
        <input type="password" name="j_password" id="j_password"/>
        <br/>
        <input type='checkbox' name='_spring_security_remember_me'/> Remember me on this computer.
        <br/>
        <input type="submit" value="Login"/>
    </form>
</center>