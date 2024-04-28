<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.bo.GameState"%>

<!DOCTYPE html>
<html>
<head>
    <title>Home Game</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
 <h4>User Connected: <c:out value="${sessionScope.gameState.user.nom}" /></h4><br>
	
	<h4>Mon meilleur Score (Avec Java)</h4>

	<%
		GameState gameState = (GameState) request.getSession().getAttribute("gameState");

		if (gameState != null && gameState.getUser() != null) {
			out.print(Math.max(gameState.getUser().getBestScore(), gameState.getUser().getScore()));
		}
	%>
	
	<div class="text-center">
	<a href="${pageContext.request.contextPath}/back/bestScore">Meilleurs
		scores</a>|
	<a href="${pageContext.request.contextPath}/back/ReinitGameServlet">Réinitialiser
		le jeu</a>|
	<a href="${pageContext.request.contextPath}/DeconnectServlet">Se
		deconnecter</a>
		<br><br>
	</div>
    <div class="container">
        <h1 class="mt-5">Select the number of the dice then click the button</h1>
        <!-- Bootstrap alert-->
        <div id="errorAlert" class="alert alert-danger d-none" role="alert">        
        Please enter a valid value (1, 2, or 3).
    	</div>
    	
        <form id='diceForm' action="${pageContext.request.contextPath}/back/GameServlet" >
            <div class="form-group">
                <input type="text" class="form-control" id="diceNumber" name="throw" required>
            </div>            
            <button type="button" class="btn btn-primary" onclick="checkValue()">Throw</button>
        </form>
        
    </div>
    
	<%
		out.print(gameState);
	%>
	
	
	
<script>
	function checkValue() {
    	var input = document.getElementById('diceNumber').value;
    	var errorAlert = document.getElementById('errorAlert');

    	// Check if input value is 1, 2, or 3
    	if (input === '1' || input === '2' || input === '3') {
        errorAlert.classList.add('d-none'); // Hide error alert if shown
        // Handle success scenario, for example, submit the form or perform further action
        document.getElementById('diceForm').submit(); // Submitting the form in this example
    	} else {
        errorAlert.classList.remove('d-none'); // Show error alert
    	}
	}
</script>
    <!-- Bootstrap JS and dependencies (optional) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
