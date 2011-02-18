<%@ include file="/html/portlet/enterprise_admin/init.jsp" %>

<%
User selUser = (User)request.getAttribute("user.selUser");
%>

<aui:fieldset>
	<div class="ctrl-holder">
		<liferay-ui:custom-attribute
			className="com.liferay.portal.model.User"
			classPK="<%= (selUser != null) ? selUser.getUserId() : 0 %>"
			editable="<%= true %>"
			label="<%= true %>"
			name="library-id"
		/>
	</div>

	<div class="ctrl-holder">
		<liferay-ui:custom-attribute
			className="com.liferay.portal.model.User"
			classPK="<%= (selUser != null) ? selUser.getUserId() : 0 %>"
			editable="<%= true %>"
			label="<%= true %>"
			name="favorite-books"
		/>
	</div>
</aui:fieldset>