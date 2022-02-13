<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@page import="valine.LifeTime"%>
<%@page import="valine.feedback.web.ApiSubmitResponse"%>
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
        opacity: 0;
        height: 0;
        overflow: hidden;
    }
    div.commandbox {
        font-size: 1.4em;
        padding: 1em 0.5em;
        border: solid 2px #0a0;
        border-bottom: none;
        border-top-left-radius: 10px;
        border-top-right-radius: 10px;
        margin-top: 25vh;
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
    div.formulation {
        margin-top: 1vh;
        font-size: 1.8em;
        text-align: left;
        margin-top: 10vh;
    }
    div.option {
        margin-top: 10vh;
    }
    div.thanks {
        margin-top: 30vh; 
        font-size: 1.9em;
        text-align: left;
        color: #353;
    }
    div.powered {
        margin-top: 35vh;
        text-align: right;
    }    
</style>
<script src="<%=back.getRootURL()%><%=WebFrontStatic22.PAGE%>/<%=WebFrontStatic22.JSHTTP%>"></script>
<script src="<%=back.getRootURL()%><%=WebFrontStatic22.PAGE%>/<%=WebFrontStatic22.JSTAGANIMATE%>"></script>
<script>
var form = <%=back.jForm()%>;
var call = <%=back.jCall()%>;
var labels = <%=back.jLabels()%>;
var metrics = <%=back.jMetrics()%>;
let doFinish = () => {
    var submitpacket = { '<%=ApiSubmitResponse.COUNT%>' : 0, <%=ApiSubmitResponse.ITEMS%> : [] };
    /* Prepare the data */ {
        var metric;
        var inpath;
        for (n = 0; n < metrics.count; n++) {
            inpath = false;
            metric = metrics.items[n];
            if (metric.value !== 0) inpath = true;
            if (!inpath) continue;
            var item = {};
            item.metricid = metric.metricid;
            item.metricrefid = metric.metricrefid;
            item.value = metric.value;
            submitpacket.count++;
            submitpacket.items.push(item);
        }
    }
    var req = new HttpRequest();
    var callback = (status, objresp) => {
        if (status === 0) {
            alert ('Could not connect to server');
            return;
        }
        if (status !== 200) {
            alert('Error server. Probably in maintenance');
            return;
        }
        if (objresp.result !== 'OK') {
            alert(objresp.description);
            return;
        }
        /* Success */
        showThanks();
    }
    req.setCallBack(callback);
    req.addParam('<%=ApiSubmitResponse.RESPONSECALLID%>', call.responsecallid);
    req.addParam('<%=ApiSubmitResponse.REPVALUES%>', JSON.stringify(submitpacket));
    req.setURL('<%=back.apiSubmitURL()%>');
    try { req.executepost(); }
    catch(err) { alert (err.getMessage); }    
}
<%@include file="mainjs.js" %>
</script>
<%@include file="metricsjs.jsp" %>
<title>Your Feedback</title>
</head>
<body>
    <div id="intropage" class="rootresponsive" style="height: auto">
        <div style="font-size: 1.1em">
            <div id="intro" class="commandbox">
            </div>
            <button id="start" class="feedback" onclick="startClick(); return false"></button>
            <div class="powered" >
                Powered by 
                <a href="<%=LifeTime.HOMEPAGE%>">Radar Eleven</a>
            </div>
        </div>        
    </div>
    <div id="metricnav" class="rootresponsive">
        <div id="formulation" class="formulation"></div>
        <div id="options" class="option"></div>
        <div style="height: 200px"></div>
    </div>
    <div id="endpage" class="rootresponsive"">
        <div style="font-size: 1.1em">
            <div id="endlabel" class="commandbox"></div>
            <button id="finish" class="feedback" onclick="doFinish(); return false"></button>
        </div>
    </div>
    <div id="thankspage" class="rootresponsive"">
        <div class="thanks" >
            <div id="thankslabel"></div>
        </div>
        <div class="powered" >
            Powered by 
            <a href="<%=LifeTime.HOMEPAGE%>">Radar Eleven</a>
        </div>
    </div>    
</body>
<script>
    initialize();
</script>
</html>
