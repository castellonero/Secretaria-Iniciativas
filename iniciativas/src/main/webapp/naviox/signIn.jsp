<%
String app = request.getParameter("application");
%>
<div><p align="center"><img width="250px" alt="Ajuntament de Castell� de la Plana. Anar a la p�gina d'inici" src="<%=request.getContextPath()%>/xava/images/castello_logo_val.png"></p></div>
<div id="sign_in_box">
	<jsp:include page='<%="../xava/module.jsp?application=" + app + "&module=SignIn"%>'/>
</div>

