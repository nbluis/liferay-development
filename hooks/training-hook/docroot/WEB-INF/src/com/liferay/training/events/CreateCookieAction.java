package com.liferay.training.events;

import com.liferay.portal.kernel.events.Action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PortalUtil;

public class CreateCookieAction extends Action {

	public void run(HttpServletRequest req, HttpServletResponse res) throws ActionException {
		try {
			long companyId = PortalUtil.getCompanyId(req);
			long userId = PortalUtil.getUserId(req);
			String domain = PropsUtil.get("session.cookie.domain");
			String cookieValue = "companyId=" + companyId + ",userId=" + userId;
			Cookie cookie = new Cookie("TRAINING_COOKIE", cookieValue);

			if (Validator.isNotNull(domain)) {
				cookie.setDomain(domain);
			}

			cookie.setPath(StringPool.SLASH);

			res.addCookie(cookie);

			System.out.println("Added TRAINING_COOKIE value to response: " + cookieValue);
		} catch (Exception e) {
			throw new ActionException(e);
		}
	}

	public static boolean isEncodedCookie(String name) {
		if (name.equals("ID") || name.equals("LOGIN") || name.equals("PASSWORD") || name.equals("SCREEN_NAME")) {
			return true;
		} else {
			return false;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CreateCookieAction.class);

}