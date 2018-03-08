<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>XD Shop</title>

<!-- Bootstrap Stylesheet -->
<!-- Bootstrap core CSS -->
<link href="frameworks/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom style -->
<link href="css/site.css" rel="stylesheet">

</head>
<body>
<!-- Need to make dynamic when someone logs in -->

<div id="headerContainer"></div>
		
	<jsp:include page="browse-shop.jsp"></jsp:include>
	
<jsp:include page="footer.html"></jsp:include>
</body>

<!-- Bootstrap core JavaScript -->
<script src="frameworks/jquery/jquery.min.js"></script>
<script src="frameworks/bootstrap/js/bootstrap.bundle.min.js"></script>
<script>
	console.log("what is this");
	var name_or_null = document.cookie.split("=")[1];
	console.log(document.cookie);
	if (name_or_null == null){
		console.log("Ima guest");
		$('#headerContainer').load("header-guest.jsp");
	}
	else{
		$.get("UserServlet?user=" + name_or_null + "&param=user", function(obj){
				var person = JSON.parse(obj);
				console.log(person);
				if (person.fname != null){
				$('#headerContainer').load("header-user.jsp");
				}else if(person.storeName != null){
					$('#headerContainer').load("header-pm.jsp");
				}
		});
	}
	
</script>

</html>