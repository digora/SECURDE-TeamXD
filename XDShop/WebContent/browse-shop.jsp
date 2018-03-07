
<!-- Page Content -->
<script>
	$.get("ProductServlet?param=all", function(obj)
	     {
	     	console.log(obj);
	         //imageCount = obj.split(",,,").length - 1;
	         addPhotos(obj);
	     });

	     function addPhotos(obj)
	     {
	         if(obj!=null){
	             var jsonarr = JSON.parse(obj);	
	             var x = jsonarr.length;
	             if(obj!=""){
	                 for (var i = 0; i < x; i++)
	                 {
	                     var jsonobj = jsonarr[i];
	                     var photo = $("<div>");
	                     $(photo).addClass("photos");
	                     $(photo).addClass("col-md-2");
	                     $(photo).prepend("<img height = 250 width = 250 class = 'img img-responsive' src = '" + jsonobj.img_link + "'/>");
	                     var imgid = $("<div>");
	                     $(imgid).addClass("id");
	                     $((imgid).html("<p>" + i + "</p>"));  
	                     $(photo).append(imgid); 
	                     $("#productListing").append(photo);
	                 }
	             }
	             
	         }
	     }

</script>

    <div class="container">

      <div class="row">

        <div class="col-lg-3">

          <h1 class="my-4">XD Shop</h1>
          <form>
          	<div class="form-group">
          		<label for="searchInput">Search</label>
          		<input type="text" id="searchInput" class="form-control" placeholder="Search an Item">
          	</div>
          	<button type="submit" class="btn btn-primary" id="searchProdBtn">Search</button>
          </form>
          
          <div class="list-group">
            <a href="#" class="list-group-item">Category 1</a>
            <a href="#" class="list-group-item">Category 2</a>
            <a href="#" class="list-group-item">Category 3</a>
          </div>

        </div>
        <!-- /.col-lg-3 -->
		<div class="col-lg-9">

          <div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
            <ol class="carousel-indicators">
              <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
              <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
              <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
            </ol>
            <div class="carousel-inner" role="listbox">
              <div class="carousel-item active">
                <img class="d-block img-fluid" src="http://placehold.it/900x350" alt="First slide">
              </div>
              <div class="carousel-item">
                <img class="d-block img-fluid" src="http://placehold.it/900x350" alt="Second slide">
              </div>
              <div class="carousel-item">
                <img class="d-block img-fluid" src="http://placehold.it/900x350" alt="Third slide">
              </div>
            </div>
            <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
              <span class="carousel-control-prev-icon" aria-hidden="true"></span>
              <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
              <span class="carousel-control-next-icon" aria-hidden="true"></span>
              <span class="sr-only">Next</span>
            </a>
          </div>

          <div class="row">
			<div id="productListing"></div>
	            

          </div>
          <!-- /.row -->

        </div>
        <!-- /.col-lg-9 -->
        
      </div>
      <!-- /.row -->

	</div>
	<!-- /.container -->