package scrum.server.css;

import ilarkesto.io.DynamicClassLoader;
import ilarkesto.logging.Logger;
import ilarkesto.ui.web.CssRenderer;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scrum.server.ScrumWebApplication;
import scrum.server.common.AHttpServlet;

public class CssServlet extends AHttpServlet {

	private static final Logger LOG = Logger.get(CssServlet.class);
	private static final long serialVersionUID = 1;

	private transient final ScreenCssBuilder screenCssBuilder = new ScreenCssBuilder();

	@Override
	protected void onRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/css");
		CssRenderer css = new CssRenderer(resp.getWriter());
		getCssBuilder().buildCss(css);
		css.flush();
		LOG.debug(css);
	}

	private CssBuilder getCssBuilder() {
		if (ScrumWebApplication.get().isDevelopmentMode()) {
			ClassLoader loader = new DynamicClassLoader(getClass().getClassLoader(), ScreenCssBuilder.class.getName());
			Class<? extends CssBuilder> type;
			try {
				type = (Class<? extends CssBuilder>) loader.loadClass(ScreenCssBuilder.class.getName());
				return type.newInstance();
			} catch (Throwable ex) {
				LOG.fatal(ex);
				throw new RuntimeException(ex);
			}
		} else {
			return screenCssBuilder;
		}
	}

	@Override
	protected void onInit(ServletConfig config) {
		ScrumWebApplication.get(config);
	}

}