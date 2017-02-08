<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DB check</title>

<LINK href="<%=request.getContextPath()%>/css/mystyle.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<h3>Product list</h3>
	<f:view>
		<h:messages layout="table"></h:messages>

		<%-- Possibility to insert  a new Product --%>
		<h:form>
			<h:commandLink binding="#{shopListBeans.addCommand}" accesskey="n"
				action="#{shopListBeans.addNew}" value="Add new Product">
			</h:commandLink>
		</h:form>


		<h:form binding="#{shopListBeans.form}" rendered="false"
			styleClass="todo">
			<h:panelGrid columns="2">
				<h:outputText value="Title"></h:outputText>
				<h:inputText value="#{shopListBeans.name}" required="true"
					requiredMessage="Product name is required">
				</h:inputText>
				<h:outputText value="Description"></h:outputText>
				<h:inputText value="#{shopListBeans.description}" required="true"
					requiredMessage="Product description is required"></h:inputText>
				<h:outputText value="Price"></h:outputText>
				<h:inputText value="#{shopListBeans.price}" required="true"
					requiredMessage="Product price is required">
				</h:inputText>
			</h:panelGrid>
			<h:panelGroup>
				<h:commandButton action="#{shopListBeans.save}" value="Save"
					accesskey="s">
				</h:commandButton>
				<h:commandButton action="#{shopListBeans.cancel}" value="Cancel"
					accesskey="c" immediate="true">
				</h:commandButton>
			</h:panelGroup>
		</h:form>

		<%-- These buttons allow to show and hide the table --%>
		<h:form>
			<h:panelGrid columns="2">
				<h:commandLink id="hide"
					actionListener="#{shopListBeans.displayTable}" value="Hide Table">
				</h:commandLink>
				<h:commandLink id="show"
					actionListener="#{shopListBeans.displayTable}" value="Show Table">
				</h:commandLink>
			</h:panelGrid>
		</h:form>

		<%-- Here we start the form for the data table --%>
		<h:form binding="#{shopListBeans.tableForm}">
			<%-- Here we start the data table --%>

			<h:dataTable value="#{shopListBeans.repoProducts}" var="prod"
				styleClass="todo" headerClass="todoheader"
				columnClasses="first, rest">
				<h:column>
					<%-- Via this facet we define the table header (column 1) --%>
					<f:facet name="header">
						<h:column>
							<h:outputText value="id"></h:outputText>
						</h:column>
					</f:facet>
					<h:outputText value="#{prod.id}"></h:outputText>
				</h:column>
				<h:column>
					<%-- Via this facet we define the table header (column 2) --%>
					<f:facet name="header">
						<h:column>
							<h:outputText value="Name"></h:outputText>
						</h:column>
					</f:facet>
					<h:outputText value="#{prod.name}"></h:outputText>
				</h:column>

				<h:column>
					<%-- Via this facet we define the table header (column 3) --%>
					<f:facet name="header">
						<h:column>
							<h:outputText value="Description"></h:outputText>
						</h:column>
					</f:facet>
					<h:outputText value="#{prod.description}"></h:outputText>
				</h:column>
				<h:column>
					<%-- Via this facet we define the table header (column 3) --%>
					<f:facet name="header">
						<h:column>
							<h:outputText value="Price"></h:outputText>
						</h:column>
					</f:facet>
					<h:outputText value="#{prod.price}"></h:outputText>
				</h:column>

				<h:column>
					<%-- Via this facet we define the table header (column 4) --%>
					<f:facet name="header">
						<h:column>
							<h:outputText value="Actions"></h:outputText>
						</h:column>
					</f:facet>
					<h:panelGrid columns="2">
						<h:commandLink value="delete" action="#{shopListBeans.delete}">
							<f:setPropertyActionListener target="#{shopListBeans.product}"
								value="#{prod}" />
						</h:commandLink>
					</h:panelGrid>
				</h:column>
			</h:dataTable>

		</h:form>
	</f:view>
</body>
</html>