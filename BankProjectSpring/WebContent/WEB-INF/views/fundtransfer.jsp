<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Fund Transfer</title>
</head>
<body>
<div class="container">
		<div class="card card-container">
			<p id="profile-name" class="profile-name-card"></p>
			<form class="form-signin" action="fundtransferc" method="get">
				<input type="text" name="recievermobileno" id="recievermobileno"
					class="form-control" placeholder="recievermobileno" required>
				<input type="text" name="sendermobileno" id="sendermobileno"
					class="form-control" placeholder="sendermobileno" required>
				 <input type="text" name="pin" id="pin" class="form-control"
					placeholder="pin" required>
				 <input type="submit" class="btn btn-lg btn-primary btn-block btn-signin" value="Fund Transfer"
					size="100">
			</form>
		</div>
	</div>
</body>
</html>