/*
 * Copyright 2011 Witoslaw Koczewsi <wi@koczewski.de>, Artjom Kochtchi
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package scrum.server.pr;

import ilarkesto.base.Str;
import ilarkesto.core.base.Utl;
import ilarkesto.core.logging.Log;
import ilarkesto.persistence.AEntity;
import ilarkesto.persistence.DaoService;
import ilarkesto.persistence.TransactionService;
import ilarkesto.webapp.RequestWrapper;
import ilarkesto.webapp.Servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;

import scrum.server.WebSession;
import scrum.server.common.AKunagiServlet;
import scrum.server.common.KunagiUtl;

public class UnsubscribeServlet extends AKunagiServlet {

	private static final long serialVersionUID = 1;

	private static Log log = Log.get(UnsubscribeServlet.class);

	private transient SubscriptionService subscriptionService;
	private transient DaoService daoService;
	private transient TransactionService transactionService;

	@Override
	protected void onRequest(RequestWrapper<WebSession> req) throws IOException {
		String subjectId = req.get("subject");
		String email = Str.cutRight(req.get("email"), 64);
		if (Str.isBlank(email)) email = null;
		String key = req.get("key");

		log.info("Unsubscription from the internet");
		log.info("    subject: " + subjectId);
		log.info("    email: " + email);
		log.info("    key: " + key);
		log.info("  Request-Data:");
		log.info(Servlet.toString(req.getHttpRequest(), "        "));

		if (email == null) {
			sendFailureResponse(req, "email required");
			return;
		}

		String message;
		try {
			AEntity subject = unsubscribe(email, subjectId, key);
			message = subject == null ? "Succesfully unsubscribed from all entities"
					: "Succesfully unsubscribed from <strong>" + KunagiUtl.createExternalRelativeHtmlAnchor(subject)
							+ "</strong>";
		} catch (Throwable ex) {
			log.error("Unsubscription failed.", "\n" + Servlet.toString(req.getHttpRequest(), "  "), ex);
			sendFailureResponse(req, Utl.getRootCauseMessage(ex));
			return;
		}

		sendResponse(req, message);
	}

	private void sendFailureResponse(RequestWrapper<WebSession> req, String message) throws IOException {
		sendResponse(req, "<h2>Failure</h2><p>Unsubscription failed: <strong>" + message
				+ "</strong></p><p>We are sorry, please try again later.</p>");
	}

	private void sendResponse(RequestWrapper<WebSession> req, String message) throws IOException {
		String returnUrl = req.get("returnUrl");
		if (returnUrl == null) returnUrl = "http://kunagi.org/message.html?#{message}";
		returnUrl = returnUrl.replace("{message}", Str.encodeUrlParameter(message));

		req.sendRedirect(returnUrl);
	}

	private AEntity unsubscribe(String email, String subjectId, String key) throws InvalidKeyException {
		AEntity subject = subjectId == null ? null : daoService.getById(subjectId);
		subscriptionService.unsubscribe(email, subject, key);
		transactionService.commit();
		return subject;
	}

	@Override
	protected void onInit(ServletConfig config) {
		super.onInit(config);
		subscriptionService = webApplication.getSubscriptionService();
		daoService = webApplication.getDaoService();
		transactionService = webApplication.getTransactionService();
	}

}