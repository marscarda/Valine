<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@page import="valine.WebFrontStatic22"%>
<%@page import="valine.WebFrontAlpha"%>
<%@page import="valine.feedback.web.WebBackFeedbackCall"%>
<% WebBackFeedbackCall back = (WebBackFeedbackCall)request.getAttribute(WebFrontAlpha.PAGEATTRKEY); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="<%=back.getRootURL()%><%=WebFrontStatic22.PAGE%>/<%=WebFrontStatic22.CSSROOT%>">
<style>
    div.rootresponsive {
        width: 90vw; 
        max-width: 600px; 
        max-width: 600px; 
        margin-left: auto; 
        margin-right: auto;
    }
    div.introbox {
        font-size: 1.4em;
        padding: 1em 0.5em;
        border: solid 2px #0a0;
        border-bottom: none;
        border-top-left-radius: 10px;
        border-top-right-radius: 10px;
        margin-top: 20vh;
        color: #353;
    }
    button.feedback {
        width: 100%;
        padding: 1em;
        background-color: #0a0;
        border: none;
        border-bottom-left-radius: 10px;
        border-bottom-right-radius: 10px;        
        font-size: 4vh;
        font-weight: bold;
        outline: 0;
        margin: 0;
        color: #fff;
    }
</style>



<script>
var form = <%=back.jForm()%>;
var call = <%=back.jCall()%>;
</script>
<title>Your Feedback</title>
</head>
<body>
    <div class="rootresponsive">
        <div style="font-size: 1.1em">
            <div id="intro" class="introbox">
            </div>
            <button id="start" class="feedback" onclick="startClick(); return false">
            </button>
        </div>        
    </div>
</body>
</html>
