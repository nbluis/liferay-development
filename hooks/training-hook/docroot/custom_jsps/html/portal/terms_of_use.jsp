<%--
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ include file="/html/portal/init.jsp" %>

<aui:form action='<%= themeDisplay.getPathMain() + "/portal/update_terms_of_use" %>' name="fm">
	<aui:input name="doAsUserId" type="hidden" value="<%= themeDisplay.getDoAsUserId() %>" />
	<aui:input name="<%= WebKeys.REFERER %>" type="hidden" value='<%= themeDisplay.getPathMain() + "?doAsUserId=" + themeDisplay.getDoAsUserId() %>' />

	<c:choose>
		<c:when test="<%= (PropsValues.TERMS_OF_USE_JOURNAL_ARTICLE_GROUP_ID > 0) && Validator.isNotNull(PropsValues.TERMS_OF_USE_JOURNAL_ARTICLE_ID) %>">
			<liferay-ui:journal-article groupId="<%= PropsValues.TERMS_OF_USE_JOURNAL_ARTICLE_GROUP_ID %>" articleId="<%= PropsValues.TERMS_OF_USE_JOURNAL_ARTICLE_ID %>" />
		</c:when>
		<c:otherwise>
			Welcome to our site. We maintain this web site as a service to our members. By using our site, you are agreeing to comply with and be bound by the following terms of use. Please review the following terms carefully. If you do not agree to these terms, you should not use this site.

			<br /><br />

			<ol>
				<li>
					<strong><u>Acceptance of Agreement</u></strong>.<br />

					You agree to the terms and conditions outlined in this Terms of Use Agreement (&quot;Agreement&quot;) with respect to our site (the &quot;Site&quot;). This Agreement constitutes the entire and only agreement between us and you, and supersedes all prior or contemporaneous agreements, representations, warranties and understandings with respect to the Site, the content, products or services provided by or through the Site, and the subject matter of this Agreement. This Agreement may be amended at any time by us from time to time without specific notice to you. The latest Agreement will be posted on the Site, and you should review this Agreement prior to using the Site.

					<br /><br />
				</li>
				<li>
					<strong><u>Copyright</u></strong>.<br />

					The content, organization, graphics, design, compilation, magnetic translation, digital conversion and other matters related to the Site are protected under applicable copyrights, trademarks and other proprietary (including but not limited to intellectual property) rights. The copying, redistribution, use or publication by you of any such matters or any part of the Site, except as allowed by Section 4, is strictly prohibited. You do not acquire ownership rights to any content, document or other materials viewed through the Site. The posting of information or materials on the Site does not constitute a waiver of any right in such information and materials.

					<br /><br />
				</li>
				<li>
					<strong><u>Service Marks</u></strong>.<br />

					Products and names mentioned on the Site may be trademarks of their respective owners.

					<br /><br />
				</li>
				<li>
					<strong><u>Limited Right to Use</u></strong>.<br />

					The viewing, printing or downloading of any content, graphic, form or document from the Site grants you only a limited, nonexclusive license for use solely by you for your own personal use and not for republication, distribution, assignment, sublicense, sale, preparation of derivative works or other use. No part of any content, form or document may be reproduced in any form or incorporated into any information retrieval system, electronic or mechanical, other than for your personal use (but not for resale or redistribution).

					<br /><br />
				</li>
				<li>
					<strong><u>Editing, Deleting and Modification</u></strong>.<br />

					We reserve the right in our sole discretion to edit or delete any documents, information or other content appearing on the Site.

					<br /><br />
				</li>
			</ol>
		</c:otherwise>
	</c:choose>

	<c:if test="<%= !user.isAgreedToTermsOfUse() %>">
		<aui:button-row>
			<aui:button type="submit" value="i-agree" />

			<%
			String taglibOnClick = "alert('" + UnicodeLanguageUtil.get(pageContext, "you-must-agree-with-the-terms-of-use-to-continue") + "');";
			%>

			<aui:button onClick="<%= taglibOnClick %>" type="cancel" value="i-disagree" />
		</aui:button-row>
	</c:if>
</aui:form>