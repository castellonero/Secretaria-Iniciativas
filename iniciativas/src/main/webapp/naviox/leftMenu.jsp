<%@page import="org.openxava.util.Users"%>
<%@page import="com.openxava.naviox.util.NaviOXPreferences"%>

<%@include file="../xava/imports.jsp"%>

<jsp:useBean id="modules" class="com.openxava.naviox.Modules" scope="session"/>

<%
boolean isFirstSteps = com.openxava.naviox.Modules.FIRST_STEPS.equals(modules.getCurrentModuleName());
String display = isFirstSteps?"style='display:block'":""; 
%>
<div id="modules_list" <%=display%>>  

	<div id="modules_list_top"> 

		<div id="application_title">
		
			<div id="application_name">
			  <%-- Nacho A�ado este p�rrafo para insertar el logo del ayuntamiento 08/07/2022--%>
				<p>
					<img width="150px" alt="Ajuntament de Castell� de la Plana. Anar a la p�gina d'inici" src="<%=request.getContextPath()%>/xava/images/castello_logo_val.png">
				</p>
			  <%-- A�ado este p�rrafo para insertar el logo del ayuntamiento --%>
				<%=modules.getApplicationLabel(request)%>
			</div>
		
			<div id="organization_name">
				<% String organizationName = modules.getOrganizationName(request); %>
				<%=organizationName%>
				<%@ include file="organizationNameExt.jsp"%>
			</div>
		
		</div>
		
		<% if (Users.getCurrent() != null && modules.showsIndexLink()) { %>
			 
			<a href="<%=request.getContextPath()%>/m/Index">
				<div id='organizations_index' class='<%="Index".equals(request.getParameter("module"))?"selected":""%>'>	
					<i class="mdi mdi-apps"></i>
					<xava:label key="myOrganizations"/>
				</div>	
			</a>
			
		<% } %>
	
		<jsp:include page="selectModules.jsp">
			<jsp:param name="bookmarkModules" value="true"/>
		</jsp:include>
		
		<% if (modules.showsSearchModules(request)) { %>
		<div id="search_modules">
		<input id="search_modules_text" type="text" size="38" placeholder='<xava:message key="search_modules"/>'/>
		</div>
		<% } %>
		
	</div> 	
							
	<div id="modules_list_outbox">
		<table id="modules_list_box">
			<tr id="modules_list_content">
				<td>
					<jsp:include page="<%=NaviOXPreferences.getInstance().getModulesMenuJSP()%>"/>
				</td>						
			</tr>
		</table>
	</div>
	
</div>

<% if (!isFirstSteps) { %> 
	<a id="modules_list_hide" href="javascript:naviox.hideModulesList('<%=request.getParameter("application")%>', '<%=request.getParameter("module")%>')">
		<i class="mdi mdi-chevron-left"></i>
	</a>
	
	<a id="modules_list_show" href="javascript:naviox.showModulesList('<%=request.getParameter("application")%>', '<%=request.getParameter("module")%>')">
		<i class="mdi mdi-chevron-right"></i>
	</a>
<% } %>
