package com.liferay.training.action;

import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.expando.DuplicateColumnNameException;
import com.liferay.portlet.expando.NoSuchTableException;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
import com.liferay.portlet.expando.util.ExpandoBridgeIndexer;

public class ExpandoStartupAction extends SimpleAction {

	public void run(String[] ids) {
		try {

			// Get a reference to the ExpandoTable (User class)

			ExpandoTable table = null;

			long companyId = Long.parseLong(ids[0]);

			try {
				table = ExpandoTableLocalServiceUtil.getDefaultTable(companyId, User.class.getName());
			} catch (NoSuchTableException nste) {
				table = ExpandoTableLocalServiceUtil.addDefaultTable(companyId, User.class.getName());
			}

			// Add the ExpandoColumn ("library-id")

			ExpandoColumn column = null;

			long tableId = table.getTableId();

			try {
				column = ExpandoColumnLocalServiceUtil.addColumn(tableId, "library-id", ExpandoColumnConstants.STRING);

				// Add Unicode Properties

				UnicodeProperties properties = new UnicodeProperties();

				properties.setProperty(ExpandoBridgeIndexer.INDEXABLE, Boolean.TRUE.toString());

				column.setTypeSettingsProperties(properties);

				ExpandoColumnLocalServiceUtil.updateExpandoColumn(column);
			} catch (DuplicateColumnNameException dcne) {

				// Get the ExpandoColumn ("library-id")

				column = ExpandoColumnLocalServiceUtil.getColumn(tableId, "library-id");
			}

			// Get the User object for "test@liferay.com"

			String emailAddress = "test@liferay.com";

			User user = UserLocalServiceUtil.getUserByEmailAddress(companyId, emailAddress);

			// Add or Update Library ID to "777"

			long classNameId = table.getClassNameId();
			long columnId = column.getColumnId();
			long classPK = user.getUserId();
			String data = "777";

			ExpandoValue value = ExpandoValueLocalServiceUtil.addValue(classNameId, tableId, columnId, classPK, data);

			System.out.println("Library ID: " + value.getData());
		} catch (Exception e) {
			_log.error(e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ExpandoStartupAction.class);

}