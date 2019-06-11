<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List" %>
    <%@ page import="CRUDsystem.crudSys.model.Usuario" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript">
function confirmaExclusao(id){
	if(window.confirm('Tem certeza que deseja excluir?')){
		location.href="usucontroller.do?acao=exc&id=" + id;
	}
}
</script>
</head>
<body>

<%
	List<Usuario> lista = (List<Usuario>)request.getAttribute("lista");
%>

	<table border=1>
		<tr> <th> id </th> <th>nome</th> <th>Ação</th></tr>
		
		<% for (Usuario u: lista) {%>
			<tr>
				<td><%out.print(u.getId()); %></td>
				<td><%=u.getNome() %></td>
				<td> <a href="javascript:confirmaExclusao(<%=u.getId()%>)"> excluir</a> | <a href="usucontroller.do?acao=alt&id=<%=u.getId() %>"> alterar </a></td>
			</tr>
		<% }%>
	</table>
</body>
</html>