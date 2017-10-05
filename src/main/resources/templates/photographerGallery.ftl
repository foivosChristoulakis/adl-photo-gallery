<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">


	<link rel="shortcut icon" href="/favicon.ico" />
	<link rel="icon" href="/favicon.ico" />

    <title>${eventName?replace("-"," ")} by ${photographerName?replace("-"," ")}</title>

    <link href="https://cdn.rawgit.com/sachinchoolur/lightgallery.js/master/dist/css/lightgallery.css" rel="stylesheet">

    <!-- Bootstrap core CSS -->
    <link href="../../../assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../../../assets/css/custom.css" rel="stylesheet">

</head>

<body>

    <!-- Page Content -->
    <div class="container">

        <!-- Jumbotron Header -->
        <header class="jumbotron my-4 text-center">
            <img src="../../../assets/images/ADLLogo.png" class="img-fluid" width="500" alt="ADLLogo">
            <h1 class="display-3">${eventName?replace("-"," ")}</h1>
            <p class="lead">by ${photographerName?replace("-"," ")?capitalize}</p>
        </header>

		<div class="jumbotron">
		
			<!-- Page Features -->
			<div id="lightgallery" class="row text-center">


<#list photosNames as photo>
 	           <div data-src="${photo}" class="col-lg-3 col-md-6 mb-4">
   	             <div class="card">
   	                 <div class="card-img-top">
  	                      <div class="hovereffect">
  	                          <img class="card-img-top" src="../${photographerName}_thumbs/thumb-${photo}" alt="">
  	                          <div class="overlay">
  	                              <a class="info" href="${photo}">View</a>
  	                          </div>
  	                      </div>
  	                  </div>
  	              </div>
  	          </div>
</#list>

			</div>
			<!-- /.row -->

		</div>
		<!-- /.jumbotron -->
		
    </div>
    <!-- /.container -->

    <!-- Footer -->
    <footer class="py-5 bg-dark">
        <div class="container">
            <p class="m-0 text-center text-white">Copyright &copy; Arthur D. Little 2017</p>
        </div>
        <!-- /.container -->
    </footer>

    <!-- Bootstrap core JavaScript -->
    <script src="../../assets/vendor/jquery/jquery.min.js"></script>
    <script src="../../assets/vendor/popper/popper.min.js"></script>
    <script src="../../assets/vendor/bootstrap/js/bootstrap.min.js"></script>

    <script src="../../../assets/js/picturefill.min.js"></script>
    <script src="../../../assets/js/lightgallery.js"></script>
    <script src="../../../assets/js/lg-pager.js"></script>
    <script src="../../../assets/js/lg-autoplay.js"></script>
    <script src="../../../assets/js/lg-fullscreen.js"></script>
    <script src="../../../assets/js/lg-zoom.js"></script>
    <script src="../../../assets/js/lg-hash.js"></script>
    <script src="../../../assets/js/lg-share.js"></script>
	<script src="../../../assets/js/lg-thumbnail.js"></script>
    <script>
        lightGallery(document.getElementById('lightgallery'), {
            thumbnail:true
        }); 
    </script>
</body>

</html>