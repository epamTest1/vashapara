<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${empty pageContext.request.contextPath == '/' ? '' : pageContext.request.contextPath}" />

<!doctype html>
<!--[if lt IE 7]><html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="en"><![endif]-->
<!--[if IE 7]><html class="no-js lt-ie9 lt-ie8" lang="en"><![endif]-->
<!--[if IE 8]><html class="no-js lt-ie9" lang="en"><![endif]-->
<!--[if gt IE 8]><!--><html class="no-js" lang="en"><!--<![endif]-->
<head>
<meta charset="utf-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<title>Ваша пара</title>

<meta name="description" content="">

<meta name="viewport" content="width=device-width">
<link rel="stylesheet" href="${contextPath}/css/bootstrap.css">
<link rel="stylesheet" href="${contextPath}/css/style.css">

<script src="${contextPath}/js/libs/modernizr-2.5.3.min.js"></script>
</head>
<body>
<!--[if lt IE 7]><p class=chromeframe>Your browser is <em>ancient!</em> <a href="http://browsehappy.com/">Upgrade to a different browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">install Google Chrome Frame</a> to experience this site.</p><![endif]-->
<header>

</header>
<div class="main span10" role="main">
	<ul class="thumbnails">
		<li class="span3">
			<h2 class="user-name">${me.firstName}</h2>
			<a href="#" class="thumbnail">
				<img width="200" src="${me.bigPhotoUrl}" alt="${me.firstName}">
			</a>
		</li>

		<li class="span4">
			<div class="rank">${percent}%</div>
		</li>

		<li class="span3">
			<h2 class="user-name">${partner.firstName}</h2>
			<a href="#" class="thumbnail">
				<img width="200" src="${partner.bigPhotoUrl}" alt="${partner.firstName}">
			</a>
		</li>
	</ul>

	<h4 class="result-title">${judgment}</h4>

	<%-- Commented out for now. I am not sure if we gonna show anything in this section --%>
	<%-- 
	<ul class="result-list result-list__first span5">
		<li>
			<div class="smile-icon">
				<img width="75" src="/img/positive.jpg" alt="Positive"><br/>
				<small>Совпадение</small>
			</div>
		</li>

	<c:forEach var="result" items="${positiveAnswers}">
		<li>
			<div class="question">
				<div class="question-text">${result.question}</div>
				<small>${result.description}</small>
			</div>
		</li>
	</c:forEach>
	</ul>

	<ul class="result-list span5">
		<li>
			<div class="smile-icon">
				<img width="75" src="/img/negative.jpg" alt="Positive"><br/>
				<small>Несовпадения</small>
			</div>
		</li>
			<c:forEach var="result" items="${negativeAnswers}">
		<li>
			<div class="question">
				<div class="question-text">${result.question}</div>
				<small>${result.description}</small>
			</div>
		</li>
	</c:forEach>
	</ul>
	--%>
</div><!-- /.main -->
<footer>

</footer>

<!-- Google Analytics counter -->
<script>
	var _gaq = _gaq || [];
	_gaq.push(['_setAccount', 'UA-30096563-1']);
	_gaq.push(['_trackPageview']);

	(function() {
		var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
		ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
		var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
	})();
</script>
<!-- /Google Analytics counter -->

<!-- Yandex.Metrika counter -->
<div style="display:none;"><script type="text/javascript">
(function(w, c) {
	(w[c] = w[c] || []).push(function() {
		try {
			w.yaCounter13154038 = new Ya.Metrika({id:13154038, enableAll: true, trackHash:true, webvisor:true});
		}
		catch(e) {}
	});
})(window, "yandex_metrika_callbacks");
</script></div>
<script src="//mc.yandex.ru/metrika/watch.js" type="text/javascript" defer="defer"></script>
<noscript><div><img src="//mc.yandex.ru/watch/13154038" style="position:absolute; left:-9999px;" alt="" /></div></noscript>
<!-- /Yandex.Metrika counter -->

</body>
</html>
