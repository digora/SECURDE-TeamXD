<!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="#">XD Shop</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
              <a class="nav-link" href="#">Home
                <span class="sr-only">(current)</span>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#" id="navAbout">About</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#" id="navContact">Contact</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="manage-products.jsp" id="navManage">Manage Products</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#" id="navLogout">Logout</a>
            </li>

          </ul>
        </div>
      </div>
      <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    	<script>
    		$("#navLogout").click(function(){
    			$.get("UserServlet?param=logout", function(obj){
    				window.location.href = "login.html";
    			});
    		});
    	</script>
    </nav>