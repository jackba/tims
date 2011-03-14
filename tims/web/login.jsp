<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>
        <title>Login Page </title>
       
    </head>
    <body>


        <center>

<form id="loginForm" method="post" action="j_spring_security_check">
<table width="800" border="0" class="Table">
  <tr>
    <td colspan="4" class="CobaltButton"><div align="center" class="style1">Login</div></td>
  </tr>
  <tr>
    <td class="CobaltDataTD">&nbsp;</td>
    <td colspan="2" rowspan="2" class="CobaltErrorDataTD">&nbsp;</td>
    <td class="CobaltDataTD">&nbsp;</td>
  </tr>
  <tr>
    <td width="187" class="CobaltDataTD">&nbsp;</td>
    <td width="235" class="CobaltDataTD">&nbsp;</td>
  </tr>
  <tr>
    <td class="CobaltDataTD">&nbsp;</td>
    <td width="96" class="CobaltAltDataTD">Email Address</td>
    <td width="264" class="CobaltAltDataTD"><input name="j_username" type="text" class="CobaltInput" size="30"></td>
    <td class="CobaltDataTD">&nbsp;</td>
  </tr>
  <tr>
    <td class="CobaltDataTD">&nbsp;</td>
    <td class="CobaltAltDataTD">Password</td>
    <td class="CobaltAltDataTD"><input name="j_password" type="password" class="CobaltInput" size="30">

</td>
    <td class="CobaltDataTD">&nbsp;</td>
  </tr>
  <tr>
    <td class="CobaltDataTD">&nbsp;</td>
    <td colspan="2" class="CobaltAltDataTD"><div align="center">
      <input name="Submit" type="submit" class="CobaltButton" value="Login">
      <input name="Reset" type="reset" class="CobaltButton" value="Reset">
      <input name="Reset" type="reset" class="CobaltButton" value="Cancel">
    </div></td>
    <td class="CobaltDataTD">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="4" class="CobaltColumnTD">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="4" class="CobaltAltDataTD">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="4" class="CobaltFieldCaptionTD">&nbsp;</td>
  </tr>
</table>

</form>
</center>
       

    </body>
</html>