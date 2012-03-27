<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <c:out value="${testString}"></c:out> --%>
<c:forEach var="friend" items="${friendsList['response']}">
	<div>
		<img src = "${friend['user']['photo']}" alt = "${friend['user']['first_name']} ${friend['user']['last_name']}"></img>
		<p>${friend['user']['uid']} - ${friend['user']['first_name']} ${friend['user']['last_name']}</p>
	</div>	
</c:forEach>

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

<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/style.css">

<script src="js/libs/modernizr-2.5.3.min.js"></script>
</head>
<body>
<!--[if lt IE 7]><p class=chromeframe>Your browser is <em>ancient!</em> <a href="http://browsehappy.com/">Upgrade to a different browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">install Google Chrome Frame</a> to experience this site.</p><![endif]-->
<header>

</header>
<div class="main span8" role="main">

	<div class="row">
		<div class="span6">
			<form>
				<fieldset>
					<div class="friends-list">
						<label class="friends-list_item clearfix" for="user-1">
							<span class="friends-list_thumbnail thumbnail">
								<img src="http://placehold.it/30x40" alt="Витя">
							</span>
							<span class="friends-list_name">Ваня</span>
							<input id="user-1" class="hidden" name="username" value="user-1" type="radio" >
						</label>

						<label class="friends-list_item clearfix" for="user-1">
							<span class="friends-list_thumbnail thumbnail">
								<img src="http://placehold.it/30x40" alt="Витя 2">
							</span>
							<span class="friends-list_name">Ваня 2</span>
							<input id="user-2" class="hidden" name="username" value="user-2" type="radio" >
						</label>
						<label class="friends-list_item clearfix" for="user-1">
							<span class="friends-list_thumbnail thumbnail">
								<img src="http://placehold.it/30x40" alt="Витя 3">
							</span>
							<span class="friends-list_name">Ваня 3</span>
							<input id="user-3" class="hidden" name="username" value="user-3" type="radio" >
						</label>
						<label class="friends-list_item clearfix" for="user-1">
							<span class="friends-list_thumbnail thumbnail">
								<img src="http://placehold.it/30x40" alt="Витя 4">
							</span>
							<span class="friends-list_name">Ваня 4</span>
							<input id="user-4" class="hidden" name="username" value="user-4" type="radio" >
						</label>
						<label class="friends-list_item clearfix" for="user-1">
							<span class="friends-list_thumbnail thumbnail">
								<img src="http://placehold.it/30x40" alt="Витя 5">
							</span>
							<span class="friends-list_name">Ваня 5</span>
							<input id="user-5" class="hidden" name="username" value="user-5" type="radio" >
						</label>
						<label class="friends-list_item clearfix" for="user-1">
							<span class="friends-list_thumbnail thumbnail">
								<img src="http://placehold.it/30x40" alt="Витя 6">
							</span>
							<span class="friends-list_name">Ваня 6</span>
							<input id="user-6" class="hidden" name="username" value="user-6" type="radio" >
						</label>
						<label class="friends-list_item clearfix" for="user-1">
							<span class="friends-list_thumbnail thumbnail">
								<img src="http://placehold.it/30x40" alt="Витя 7">
							</span>
							<span class="friends-list_name">Ваня 7</span>
							<input id="user-7" class="hidden" name="username" value="user-7" type="radio" >
						</label>
						<label class="friends-list_item clearfix" for="user-1">
							<span class="friends-list_thumbnail thumbnail">
								<img src="http://placehold.it/30x40" alt="Витя 8">
							</span>
							<span class="friends-list_name">Ваня 8</span>
							<input id="user-8" class="hidden" name="username" value="user-8" type="radio" >
						</label>
					</div><!-- /.friends-list -->

					<div class="form-actions">
						<button type="submit" class="btn btn-primary pull-right">Go</button>
					</div>
				</fieldset>
			</form>
		</div>


	</div>

</div><!-- /.main -->
<footer>

</footer>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="js/libs/jquery-1.7.1.min.js"><\/script>')</script>

<script src="js/safe-log.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/script.js"></script>

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