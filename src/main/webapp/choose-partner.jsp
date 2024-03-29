<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
<header style="text-align: center;"  >
		<img align="middle" alt="Ваша пара" height="150" src="/img/head-img.png">
</header>
<div class="main span8" role="main">
	<form action="${contextPath}/survey" method="get">
		<fieldset>
			<div class="friends-list">
				<c:forEach var="friend" items="${friends}" varStatus="friendListStatus">
					<c:choose>
						<c:when test="${friendListStatus.count == 1}">
							<label class="friends-list_item clearfix friends-list_item__first" for="${friend.id}">
						</c:when>
						<c:otherwise>
							<label class="friends-list_item clearfix" for="user-${friend.id}">
						</c:otherwise>
					</c:choose>
								<span class="friends-list_thumbnail thumbnail">
									<img src="${friend.smallPhotoUrl}" alt="${friend.firstName} ${friend.lastName}" width="50">
								</span>
								<span class="friends-list_name">${friend.firstName} ${friend.lastName}</span>
								<input id="user-${friend.id}" class="hidden" name="partnerId" value="${friend.id}" type="radio" >
							</label>
				</c:forEach>
			</div><!-- /.friends-list -->
			<div class="form-actions">
				<button id="choose-partner-button" class="btn pull-right disabled" type="submit">Продолжить</button>
			</div>
		</fieldset>
		<input name="myId" value="${myId}" type="hidden">
	</form>
</div><!-- /.main -->
<footer>

</footer>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="js/libs/jquery-1.7.1.min.js"><\/script>')</script>

<script src="${contextPath}/js/safe-log.js"></script>
<script src="${contextPath}/js/bootstrap.min.js"></script>

<script src="${contextPath}/js/pages/choose-partner.js"></script>

<script src="${contextPath}/js/script.js"></script>

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
		} catch(e) {}
	});
})(window, "yandex_metrika_callbacks");
</script></div>
<script src="//mc.yandex.ru/metrika/watch.js" type="text/javascript" defer="defer"></script>
<noscript><div><img src="//mc.yandex.ru/watch/13154038" style="position:absolute; left:-9999px;" alt="" /></div></noscript>
<!-- /Yandex.Metrika counter -->

</body>
</html>
