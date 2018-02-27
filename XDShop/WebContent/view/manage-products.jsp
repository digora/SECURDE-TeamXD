<!-- Kek -->

<div class="container form-container">
	<div class="row justify-content-center">
		<div class="col-lg-10">
		<h1>Your Products:</h1>
			<table class="table" id="product-list">
			  <thead>
			    <tr>
			      <th scope="col">ID</th>
			      <th scope="col">Name</th>
			      <th scope="col">Price</th>
			      <th scope="col">Stock</th>
			      <th></th>
			    </tr>
			  </thead>
			  <tbody>
			    <tr>
			      <th scope="row">1</th>
			      <td>Product 1</td>
			      <td>$100</td>
			      <td>10</td>
			      <td>
			      	<a href="#" class="btn btn-primary" id="btnEditProd">Edit</a>
			      	<a href="#" class="btn btn-danger" id="btnDeleteProd">Delete</a>
			      </td>
			    </tr>
			    <tr>
			      <th scope="row">2</th>
			      <td>Product 2</td>
			      <td>$200</td>
			      <td>5</td>
			      <td>
			      	<a href="#" class="btn btn-primary" id="btnEditProd1">Edit</a>
			      	<a href="#" class="btn btn-danger" id="btnDeleteProd1">Delete</a>
			      </td>
			    </tr>
			    <tr>
			      <th scope="row">3</th>
			      <td>Product 3</td>
			      <td>$10</td>
			      <td>35</td>
			      <td>
			      	<a href="#" class="btn btn-primary" id="btnEditProd2">Edit</a>
			      	<a href="#" class="btn btn-danger" id="btnDeleteProd2">Delete</a>
			      </td>
			    </tr>
			  </tbody>
			</table>
			<div class="row justify-content-end">
				<div class="col">
					<a href="#" class="btn btn-success btn-block" id="addProdBtn">Add Product</a>
				</div>
			</div>
		</div>
	</div>
	
	<div class="row justify-content-center product-card">
		<div class="col-lg-10">
		<h1>Your Orders:</h1>
			<table class="table" id="orders-list">
			  <thead>
			    <tr>
			      <th scope="col">Order#</th>
			      <th scope="col">Name</th>
			      <th scope="col">Price</th>
			      <th scope="col">Quantity</th>
			      <th></th>
			    </tr>
			  </thead>
			  <tbody>
			    <tr>
			      <td>1109</td>
			      <td>Product 1</td>
			      <td>$100</td>
			      <td>4</td>
			      <td>
			      	<a href="#" class="btn btn-warning" id="btnProcess">Process</a>
			      </td>
			    </tr>
			    <tr>
			      <td>3333</td>
			      <td>Product 66</td>
			      <td>$333</td>
			      <td>6</td>
			      <td>
			      	<a href="#" class="btn btn-warning" id="btnProcessTwo">Process</a>
			      </td>
			    </tr>		    
			  </tbody>
			</table>
		</div>
		
	</div>
</div>