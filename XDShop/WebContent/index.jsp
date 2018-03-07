<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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

<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script>
	console.log("what is this");
	var name = document.cookie.split("=")[1];
	console.log(name);
	$.get("UserServlet?user=" + name + "&param=user", function(obj){
		var person = JSON.parse(obj);
		if(typeof person.fname != 'undefined'){
			$('#headerContainer').load("header-user.jsp");
		}else if(typeof person.storeName != 'undefined'){
			$('#headerContainer').load("header-pm.jsp");
		}else{
			$('#headerContainer').load("header-guest.jsp");
		}
	});
	
</script>
<div id="headerContainer"></div>
		
	<jsp:include page="browse-shop.jsp"></jsp:include>
	
<jsp:include page="footer.html"></jsp:include>
</body>

<!-- Bootstrap core JavaScript -->
<script src="frameworks/jquery/jquery.min.js"></script>
<script src="frameworks/bootstrap/js/bootstrap.bundle.min.js"></script>


</html>