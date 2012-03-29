<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

<script src="${pageContext.request.contextPath}/js/libs/modernizr-2.5.3.min.js"></script>
</head>
<body>
<!--[if lt IE 7]><p class=chromeframe>Your browser is <em>ancient!</em> <a href="http://browsehappy.com/">Upgrade to a different browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">install Google Chrome Frame</a> to experience this site.</p><![endif]-->
<header>

</header>
<div class="main span10" role="main">
	<ul class="thumbnails row">
		<li class="span3">
			<h2 class="user-name">${me.name}</h2>
			<a href="#" class="thumbnail">
				<img src="${me.imageUrl}" alt="${me.name}">
			</a>
		</li>

		<li class="span4"></li>

		<li class="span3">
			<h2 class="user-name">${partner.name}</h2>
			<a href="#" class="thumbnail">
				<img src="${partner.imageUrl}" alt="${partner.name}">
			</a>
		</li>
	</ul>

	<div class="tabbable tabs-right row">
		<%-- tabs are hidden --%>
		<ul class="nav nav-tabs span2 hidden">
			<c:forEach var="category" items="${categories}" varStatus="index">
				<li class="${index.first ? 'active' : ''}"><a href="#section-${category.id}" data-toggle="tab">${category.name}</a></li>
			</c:forEach>
		</ul>
		<div class="tab-content span10">
			<c:forEach var="category" items="${categories}" varStatus="index">
				<div id="section-${category.id}" class="tab-pane ${index.first ? 'active' : ''}">
					<form class="form-horizontal" action="${pageContext.request.contextPath}/survey/${me.id}/${partner.id}" method="post">
						<fieldset>
							<legend>Варианты</legend>

							<c:forEach var="question" items="${category.questions}">
								<c:set var="inputName" value="question-${question.id}" />

								<div class="control-group">
									<div class="control-label">${question.text}</div>
									<div class="controls">
										<div class="btn-group" data-toggle="buttons-radio">
											<label class="btn btn-info" for="option-always-${question.id}">Всегда
												<input id="option-always-${question.id}" class="hidden" name="${inputName}" type="radio" value="ALWAYS">
											</label>
											<label class="btn btn-info" for="option-often-${question.id}">Часто
												<input id="option-often-${question.id}" class="hidden" name="${inputName}" type="radio" value="OFTEN">
											</label>
											<label class="btn btn-info" for="option-equally-${question.id}">Поровну
												<input id="option-equally-${question.id}" class="hidden" name="${inputName}" type="radio" value="EQUALY">
											</label>
											<label class="btn btn-info" for="option-sometimes-${question.id}">Иногда
												<input id="option-sometimes-${question.id}" class="hidden" name="${inputName}" type="radio" value="SOMETIMES">
											</label>
											<label class="btn btn-info" for="option-never-${question.id}">Никогда
												<input id="option-never-${question.id}" class="hidden" name="${inputName}" type="radio" value="NEVER">
											</label>
										</div>
									</div><!-- /.controls -->
								</div><!-- /.control-group -->
							</c:forEach>

							<div class="form-actions">
								<button class="btn pull-right" type="submit">Save changes</button>
							</div>
						</fieldset>
					</form>
				</div>
			</c:forEach>
		</div><!-- /.tab-content -->
	</div>

</div><!-- /.main -->
<footer>

</footer>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="js/libs/jquery-1.7.1.min.js"><\/script>')</script>

<script src="${pageContext.request.contextPath}/js/safe-log.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<script src="${pageContext.request.contextPath}/js/pages/survey.js"></script>

<script src="${pageContext.request.contextPath}/js/script.js"></script>

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