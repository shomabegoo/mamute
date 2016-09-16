<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="value" required="false" type="java.lang.String" %>
<%@attribute name="hintId" required="false" type="java.lang.String" %>
<%@attribute name="htmlClass" required="false" type="java.lang.String" %>
<%@attribute name="placeholder" required="false" type="java.lang.String" %>
<%@attribute name="name" required="false" type="java.lang.String" %>
<%@attribute name="minlength" required="false" type="java.lang.Long" %>
<%@attribute name="maxlength" required="false" type="java.lang.Long" %>

<c:set var="siteName" value="${t['site.name']}"/>

<c:if test="${empty name}">
    <c:set var="name" value="description"/>
</c:if>
<!-- build:css /css/moratab.css -->
<link type="text/css" rel="stylesheet" href="/css/deps/font-awesome/css/font-awesome.min.css">
<link type="text/css" rel="stylesheet" href="/css/deps/moratab/editor.css">
<!-- endbuild -->

<!-- build:js /js/moratab.js -->
<script type="text/javascript" src="/assets/js/deps/moratab/jquery.js"></script>
<script type="text/javascript" src="/assets/js/deps/moratab/classie.js"></script>
<script type="text/javascript" src="/assets/js/deps/moratab/prism.js"></script>
<script type="text/javascript" src="/assets/js/deps/moratab/libs.js"></script>
<script type="text/javascript" src="/assets/js/deps/moratab/syntax.js"></script>
<script type="text/javascript" src="/assets/js/deps/moratab/buttons.js"></script>
<script type="text/javascript" src="/assets/js/deps/moratab/editor.js"></script>
<script type="text/javascript" src="/assets/js/deps/moratab/mousetrap.js"></script>
<!-- endbuild -->


<div class="moratab-container">
    <div id="moratab" data-hint-id="<c:out value="${hintId}"/>" class="hintable"><c:out value="${value}"
                                                                                        escapeXml="true"/></div>
</div>

<script type="text/javascript">
    content = $('<textarea/>').html($('#moratab').html()).val();
    var editor = $('#moratab').moratab(content,
            {
                strings: {help: ''},
                hintId: "<c:out value="${hintId}"/>",
                htmlClass: "<c:out value="${htmlClass}" />",
                placeholder: "<c:out value="${placeholder}" />",
                minlength: "<c:out value="${minlength}" />",
                maxlength: "<c:out value="${maxlength}" />",
                name: "<c:out value="${name}" />"
            }
    );


    $('#submitform').submit(function () {
        console.log(editor.getValue());
        $("textarea#hiddentextarea").val(editor.getValue());
        return true;
    });

    $("#moratab").keyup(function () {
        $("textarea#hiddentextarea").val(editor.getValue());
    });

</script>

<div class="wmd">
    <div class="wmd-panel">
        <%--<div id="wmd-button-bar"></div>--%>
        <img id="image-editor-preview" alt="Preview" style="display: none">
        <textarea id="hiddentextarea" class="${htmlClass} hintable wmd-input" id="wmd-input"
                  placeholder="${placeholder}"
                  data-hint-id="${hintId}"
                  minlength="${minlength}"
                  maxlength="${maxlength}"
                  name="${name}" style="display:none"><c:out value="${value}" escapeXml="true"/></textarea>
    </div>
    <div id="wmd-preview" class="md-panel md-preview hidden"></div>
</div>