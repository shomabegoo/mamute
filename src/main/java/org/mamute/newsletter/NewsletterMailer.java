package org.mamute.newsletter;

import br.com.caelum.vraptor.environment.Environment;
import br.com.caelum.vraptor.http.route.Router;
import br.com.caelum.vraptor.simplemail.Mailer;
import br.com.caelum.vraptor.simplemail.template.BundleFormatter;
import br.com.caelum.vraptor.simplemail.template.TemplateMailer;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.ULocale;
import org.apache.commons.mail.Email;
import org.apache.log4j.Logger;
import org.hibernate.ScrollableResults;
import org.joda.time.DateTime;
import org.mamute.dao.NewsDAO;
import org.mamute.dao.QuestionDAO;
import org.mamute.model.News;
import org.mamute.model.Question;
import org.mamute.model.User;
import org.mamute.notification.NotificationMailer;
import org.mamute.notification.NotificationMailer.LinkToHelper;
import org.mamute.util.BrutalDateFormat;
import org.mamute.vraptor.Env;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;

import javax.inject.Inject;
import java.util.List;

public class NewsletterMailer {
	
	private static final PolicyFactory POLICY = new HtmlPolicyBuilder().toFactory();
	private static final Logger LOG = Logger.getLogger(ModeratorsNewsletterJob.class);
	@Inject private QuestionDAO questions;
	@Inject private Mailer mailer;
	@Inject private TemplateMailer templates;
	@Inject private Router router;
	@Inject private NewsDAO news;
	@Inject private BrutalDateFormat brutalDateFormat;
	@Inject private Env brutalEnv;
	@Inject private BundleFormatter bundle;
	@Inject private Environment env;

	public void sendTo(ScrollableResults results, boolean isTestNewsletter) {
		DateTime pastMonth = new DateTime().minusWeeks(4);
		DateTime twelveHoursAgo = new DateTime().minusHours(12);
		List<News> hotNews = news.hotNews();
		List<Question> hotQuestions = questions.hot(pastMonth, 8);
		List<Question> unanswered = questions.randomUnanswered(pastMonth, twelveHoursAgo, 8);
		LinkToHelper linkToHelper = new NotificationMailer.LinkToHelper(router, brutalEnv);
		String siteName = bundle.getMessage("site.name");
		String date = brutalDateFormat.getInstance("date.joda.newsletter.pattern").print(new DateTime());


		ULocale locale = new ULocale("fa_IR@calendar=persian");
		Calendar c = Calendar.getInstance();
		c.setTime(new DateTime().toDate());
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
		date = df.format(c).replace(" ه\u200D.ش.", "");

		String teste = isTestNewsletter ? bundle.getMessage("newsletter_mail_test") : "";
		
		while (results.next()) {
			User user = (User) results.get()[0];
			try {
				Email email = templates.template("newsletter_mail", siteName, teste)
						.with("hotNews", hotNews)
						.with("hotQuestions", hotQuestions)
						.with("unansweredQuestions", unanswered)
						.with("unsubscribeLink", linkToHelper.unsubscribeLink(user))
						.with("linkToHelper", linkToHelper)
						.with("l10n", bundle)
						.with("sanitizer", POLICY)
						.with("siteName", siteName)
						.with("date", date)
						.with("logoUrl", env.get("mail_logo_url"))
						.to(user.getName(), user.getEmail());
				email.setCharset("utf-8");
				mailer.send(email);
			} catch (Exception e) {
				LOG.error("could not send email", e);
			}
		}

	}

}
