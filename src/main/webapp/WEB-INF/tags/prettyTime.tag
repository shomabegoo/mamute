<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="time" required="true" type="org.joda.time.base.AbstractInstant"%>
<%@ tag import="org.mamute.util.DecimalToPersianConvertor" %>
<%@ tag language="java" pageEncoding="UTF-8"%>

<%
	if (time != null) {
		org.joda.time.base.AbstractInstant time = (org.joda.time.base.AbstractInstant) jspContext
				.getAttribute("time");
		
		if(time.toDateTime().isAfter(new org.joda.time.DateTime().minusMonths(3))){
			org.ocpsoft.prettytime.PrettyTime formatter = (org.ocpsoft.prettytime.PrettyTime) request
				.getAttribute("prettyTimeFormatter");
			out.write(DecimalToPersianConvertor.decimalToPersian(formatter.format(time.toDate())));
		}else{
			org.joda.time.format.DateTimeFormatter literalFormatter = (org.joda.time.format.DateTimeFormatter) request.getAttribute("literalFormatter");

            com.ibm.icu.util.ULocale locale = new com.ibm.icu.util.ULocale("fa_IR@calendar=persian");
            com.ibm.icu.util.Calendar c = com.ibm.icu.util.Calendar.getInstance();
            c.setTime(time.toDate());
            com.ibm.icu.text.DateFormat df = com.ibm.icu.text.DateFormat.getDateInstance(com.ibm.icu.text.DateFormat.MEDIUM, locale);

            out.write(df.format(c).replace(" ه\u200D.ش.", ""));
		}
	}
%>
