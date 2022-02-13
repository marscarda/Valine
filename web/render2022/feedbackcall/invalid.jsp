<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@page import="valine.LifeTime"%>
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
    div.itemmessage {
        margin-top: 5vh;
        font-size: 1.6em;
        color: #d00
    }
    div.visit {
        margin-top: 25vh;
        text-align: right;
    }    
</style>
<title>Invalid</title>
</head>
<body>
    <div class="rootresponsive" style="height: auto; opacity: 1">
        <div style="height: 10vh"></div>
        <div class="itemmessage" >
            This survey is no longer available
        </div>
        <div class="itemmessage" >
            La encuesta ya no se encuentra disponible
        </div>
        <div class="itemmessage" >
            L'enquête n'est plus disponible
        </div>
        
        <div class="itemmessage" >
            A pesquisa não está mais disponível
        </div>
        <div class="itemmessage" >
            調査はご利用いただけなくなりました
        </div>
        <div style="font-size: 1.1em">
            <div class="visit" >
                Take a visit to
                <a href="<%=LifeTime.HOMEPAGE%>">Radar Eleven</a>
            </div>
        </div>
        <div style="height: 200px"></div>
    </div>
</body>
</html>
