<div class="container form-container">

	<div class="row justify-content-center">
		<div class="col col-md-6">
			<form>
			  <div class="form-group">
			    <label for="usernameInput">Username</label>
			    <input type="text" class="form-control" id="usernameInput" placeholder="Enter Username">
			  </div>
			  <div class="form-group">
			    <label for="passwordInput">Password</label>
			    <input type="password" class="form-control" id="passwordInput" placeholder="Password">
			  </div>
			  <button type="submit" class="btn btn-primary" id="btnSubmit">Submit</button>

			</form>
		</div>
	</div>
    </body>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script>
            $("#btnSubmit").click(function(){
            	console.log("Hehe");
                var chec = 'false';
//	                if(document.getElementById('remember').checked){
	//                    chec = 'true';
	//                }
                var user = document.getElementById("usernameInput").value;
                var pass = document.getElementById("passwordInput").value;
                var path = "UserServlet?user="+user+"&pass="+pass+"&remembered="+chec+"&param=login";
                
                $.get(path,function(obj){
                    if(obj == "true"){
                    	console.log("ayy lmao");
                        window.open("index.jsp");
                        close();
                        
                    }else{
                    	console.log("ayy");
                        alert("Invalid username / pass");
                    }
                });
            });
        </script>
</div>