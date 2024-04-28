<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.bo.GameState"%>
 
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Game Over</title>
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">
  <div class="row justify-content-center mt-5">
    <div class="col-md-6">
      <div class="card">
        <div class="card-body">
          <h2 class="card-title text-center">Game Over!</h2>
          <div class="row">
            <div class="col-md-6">
              <p class="card-text">Your score:</p>
              <%
				GameState gameState = (GameState) request.getSession().getAttribute("gameState");

				if (gameState != null && gameState.getUser() != null) {
						out.print(gameState.getUser().getScore());
				}
				
			%>
            </div>
            <div class="col-md-6">
              <p class="card-text">Best score:</p>
             	 <%
					if (gameState != null && gameState.getUser() != null) {
						out.print(Math.max(gameState.getUser().getBestScore(), gameState.getUser().getScore()));
					}
				%>
            </div>
          </div>
          <div class="progress">
            <div class="progress-bar" role="progressbar" style="width: 0%;" aria-valuenow="0" aria-valuemin="0" aria-valuemax="14"></div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

</body>
</html>

