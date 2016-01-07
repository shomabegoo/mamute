package org.mamute.infra;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Locale;
import java.util.regex.Pattern;

public class NormalizerBrutal {
	public static String toSlug(String input) {
		if (input == null || input.isEmpty()) return "";
		Pattern NONLATIN = Pattern.compile("[^\\w-]");
		Pattern WHITESPACE = Pattern.compile("[\\s]");
		String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
		String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
//		if (locale != null && locale.getLanguage().equals("fa")) {
//			return normalized.toLowerCase(Locale.ENGLISH);
//		} else {
//		String slug = NONLATIN.matcher(normalized).replaceAll("");
		return normalized.toLowerCase(Locale.ENGLISH);
//		}
	}
}
