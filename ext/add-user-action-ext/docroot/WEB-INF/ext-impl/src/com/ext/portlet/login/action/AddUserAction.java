package com.ext.portlet.login.action;

import com.liferay.portlet.login.action.CreateAccountAction;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

public class AddUserAction extends CreateAccountAction {

	protected void addUser(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		System.out.println("New user added!");

		super.addUser(actionRequest, actionResponse);
	}

}