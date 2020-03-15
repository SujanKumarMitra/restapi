<%@page import="com.tutorials.restapi.model.Error"%>
<%@page import="com.tutorials.restapi.model.Client"%>
<%
	Client client = (Client)request.getSession().getAttribute("Client");
	if(client == null)
	{
		request.getSession().setAttribute("Error", new Error(404,"Invalid Session"));
		response.sendRedirect("error.jsp");
		return;
	}
%>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Hello, world!</title>
  </head>
  <body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">Rest Api In Java</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav navbar-logo mx-auto">
            <li class="nav-item active">
              <a class="nav-link" href="./menu.jsp">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
              <a class="nav-link" href="GetAllUsers">Get All Users <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
              <a class="nav-link" href="./search_user.html">Search User <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
              <a class="nav-link" href="./add_user.html">Add User<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
              <a class="nav-link" href="./update_user.html">Update User<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
              <a class="nav-link" href="./delete_user.html">Delete User <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="Logout">Logout <span class="sr-only">(current)</span></a>
              </li>
          </ul>
        </div>
      </nav>
      <div class="align-self-center mr-auto">
        <a href="GetAllUsers" id="button" class="btn btn-primary btn-block" role="button" aria-pressed="true">Get All Users</a><br/>
        <a href="search_user.html" id="button" class="btn btn-primary btn-block" role="button" aria-pressed="true">Search User</a><br/>
        <a href="add_user.html" id="button" class="btn btn-primary btn-block" role="button" aria-pressed="true">Add User</a><br/>
        <a href="update_user.html" id="button" class="btn btn-primary btn-block" role="button" aria-pressed="true">Update User</a><br/>
        <a href="delete_user.html" id="button" class="btn btn-primary btn-block" role="button" aria-pressed="true">Delete User</a><br/>
      </div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
  </body>
</html>