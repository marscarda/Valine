<%@page import="valine.WebFrontStatic22"%>
<%@page import="valine.WebFrontAlpha"%>
<%@page import="valine.feedback.web.WebBackFeedbackCall"%>
<% WebBackFeedbackCall mnback = (WebBackFeedbackCall)request.getAttribute(WebFrontAlpha.PAGEATTRKEY); %>
<script>
/* Metric type */
let METRICTYPE_PUBLICVIEW = 2;
/* Global navigation */
var position = 0;
let metricPublicView = (metric) => {
    var metricpage = document.getElementById('metricpage');
    var formulation = document.getElementById('formulation');
    var options = document.getElementById('options');
    /* Formulation part */ {
        var line = document.createElement('div');
        line.style.fontSize = "1em";
        line.style.color = "#555";
        line.innerHTML = decodeURI(decodeURIComponent(labels.pubviewformulation));
        formulation.appendChild(line);
        line = document.createElement('div');
        line.style.marginTop = "6vh";
        line.style.fontSize = "1em";
        line.style.fontWeight = "bold";
        line.style.color = "#353";
        line.style.backgroundColor = "#eee";
        line.style.border = "none";
        line.style.borderRadius = "10px";
        line.style.padding = "2vh 2vw";
        line.innerHTML = decodeURI(decodeURIComponent(metric.label));
        formulation.appendChild(line);
    }
    var option;
    var cell;
    var img;
    /* Positive */ {
        option = document.createElement('div');
        option.id = "positive"
        option.style.border = "solid 2px #0a0";
        option.style.borderRadius = "10px";
        option.style.padding = "10px";
        option.style.display = "flex";
        option.style.flexDirection = "row";        
        /* Icon */ {
            cell = document.createElement('div');
            cell.style.display = "flex";
            cell.style.flexDirection = "column";
            cell.style.justifyContent = "center";
            cell.style.width = "5em"
            img = document.createElement("img");
            img.src = "<%=mnback.getRootURL()%><%=WebFrontStatic22.PAGE%>/<%=WebFrontStatic22.SURVEYOPT_POSITIVE%>";
            img.style.height = "4.8em";
            img.style.width = "4.8em";
            cell.appendChild(img)
        }
        /* Label */ {
            option.appendChild(cell);
            cell = document.createElement('div');
            cell.style.flex = 1;
            cell.style.display = "flex";
            cell.style.flexDirection = "column";
            cell.style.justifyContent = "center";
            cell.style.textAlign = "center";
            cell.style.fontSize = "1.7em";
            cell.style.fontWeight = "bold";
            cell.style.color = "#555";
            cell.innerHTML = decodeURI(decodeURIComponent(labels.pubviewpositive));
            option.appendChild(cell);            
        }
        option.onclick = () => {
            document.getElementById('positive').style.backgroundColor = "#aaa";
            var outup = new ElementOutUp();
            outup.onFinish = () => {
                metric.value = 1;
                position++;
                nextQuestion();
            }
            outup.start();
        }
        options.appendChild(option);
    }
    /* Neutral */ {
        option = document.createElement('div');
        option.id = "neutral"
        option.style.marginTop = "3vh";
        option.style.border = "solid 2px #06e";
        option.style.borderRadius = "10px";
        option.style.padding = "10px";
        option.style.display = "flex";
        option.style.flexDirection = "row";        
        /* Icon */ {
            cell = document.createElement('div');
            cell.style.display = "flex";
            cell.style.flexDirection = "column";
            cell.style.justifyContent = "center";
            cell.style.width = "5em"
            img = document.createElement("img");
            img.src = "<%=mnback.getRootURL()%><%=WebFrontStatic22.PAGE%>/<%=WebFrontStatic22.SURVEYOPT_NEUTRAL%>";
            img.style.height = "4.8em";
            img.style.width = "4.8em";
            cell.appendChild(img)
        }
        /* Label */ {
            option.appendChild(cell);
            cell = document.createElement('div');
            cell.style.flex = 1;
            cell.style.display = "flex";
            cell.style.flexDirection = "column";
            cell.style.justifyContent = "center";
            cell.style.textAlign = "center";
            cell.style.fontSize = "1.7em";
            cell.style.fontWeight = "bold";
            cell.style.color = "#555";
            cell.innerHTML = decodeURI(decodeURIComponent(labels.pubviewneutral));
            option.appendChild(cell);            
        }
        option.onclick = () => {
            document.getElementById('neutral').style.backgroundColor = "#aaa";
            var outup = new ElementOutUp();
            outup.onFinish = () => {
                metric.value = 2;
                position++;
                nextQuestion();
            }
            outup.start();
        }
        options.appendChild(option);
    }
    /* Negative */ {
        option = document.createElement('div');
        option.id = "negative"
        option.style.marginTop = "3vh";
        option.style.border = "solid 2px #f20";
        option.style.borderRadius = "10px";
        option.style.padding = "10px";
        option.style.display = "flex";
        option.style.flexDirection = "row";        
        /* Icon */ {
            cell = document.createElement('div');
            cell.style.display = "flex";
            cell.style.flexDirection = "column";
            cell.style.justifyContent = "center";
            cell.style.width = "5em"
            img = document.createElement("img");
            img.src = "<%=mnback.getRootURL()%><%=WebFrontStatic22.PAGE%>/<%=WebFrontStatic22.SURVEYOPT_NEGATIVE%>";
            img.style.height = "4.8em";
            img.style.width = "4.8em";
            cell.appendChild(img)
        }
        /* Label */ {
            option.appendChild(cell);
            cell = document.createElement('div');
            cell.style.flex = 1;
            cell.style.display = "flex";
            cell.style.flexDirection = "column";
            cell.style.justifyContent = "center";
            cell.style.textAlign = "center";
            cell.style.fontSize = "1.7em";
            cell.style.fontWeight = "bold";
            cell.style.color = "#555";
            cell.innerHTML = decodeURI(decodeURIComponent(labels.pubviewnegative));
            option.appendChild(cell);            
        }
        option.onclick = () => {
            document.getElementById('negative').style.backgroundColor = "#aaa";
            var outup = new ElementOutUp();
            outup.onFinish = () => {
                metric.value = 3;
                position++;
                nextQuestion();
            }
            outup.start();
        }        
        options.appendChild(option);
    }
    /* Unknown */ {
        option = document.createElement('div');
        option.id = "unknown"
        option.style.marginTop = "3vh";
        option.style.border = "solid 2px #bbb";
        option.style.borderRadius = "10px";
        option.style.padding = "10px";
        option.style.display = "flex";
        option.style.flexDirection = "row";        
        /* Icon */ {
            cell = document.createElement('div');
            cell.style.display = "flex";
            cell.style.flexDirection = "column";
            cell.style.justifyContent = "center";
            cell.style.width = "5em"
            img = document.createElement("img");
            img.src = "<%=mnback.getRootURL()%><%=WebFrontStatic22.PAGE%>/<%=WebFrontStatic22.SURVEYOPT_UNKNOWN%>";
            img.style.height = "4.8em";
            img.style.width = "4.8em";
            cell.appendChild(img)
        }
        /* Label */ {
            option.appendChild(cell);
            cell = document.createElement('div');
            cell.style.flex = 1;
            cell.style.display = "flex";
            cell.style.flexDirection = "column";
            cell.style.justifyContent = "center";
            cell.style.textAlign = "center";
            cell.style.fontSize = "1.7em";
            cell.style.fontWeight = "bold";
            cell.style.color = "#555";
            cell.innerHTML = decodeURI(decodeURIComponent(labels.pubviewunknown));
            option.appendChild(cell);            
        }
        option.onclick = () => {
            document.getElementById('unknown').style.backgroundColor = "#aaa";
            var outup = new ElementOutUp();
            outup.onFinish = () => {
                metric.value = 4;
                position++;
                nextQuestion();
            }
            outup.start();
        }        
        options.appendChild(option);
    }    
}
</script>