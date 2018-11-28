<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Account</title>
</head>
<body>
	<div class="container">
		<div class="row main">
			<div class="panel-heading">
				<div class="panel-title text-center">
					<h2 class="title" align="left">Account Opening Form</h2>

				</div>
			</div>
			<div class="main-login main-center">
				<form:form class="form-horizontal" modelAttribute="customer"
					method="post" action="addcustomer">

					<div class="form-group">
						<label for="name" class="cols-sm-2 control-label">Enter
							Your Name: </label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-user fa"
									aria-hidden="true"></i></span>
								<form:input path="name" cssClass="form-control"
									id="name" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="mobileno" class="cols-sm-2 control-label">Enter
							Your Mobile No: </label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-user fa"
									aria-hidden="true"></i></span>
								<form:input path="mobno" cssClass="form-control"
									id="mobno" />
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="address" class="cols-sm-2 control-label">
						Enter Your Address: 
								(Eg:doorno,street/village,city,district,pincode)</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-user fa"
									aria-hidden="true"></i></span>
								<form:input path="address" cssClass="form-control" id="address" />
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="email" class="cols-sm-2 control-label">Enter
							Your EmailId: </label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fa fa-envelope fa" aria-hidden="true"></i></span>
								<form:input path="emailId" cssClass="form-control" id="emailId" />
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label for="balance" class="cols-sm-2 control-label">Enter
							Initial Balance: (Minimum Balance is 500)</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-user fa"
									aria-hidden="true"></i></span>
								<form:input path="balance" cssClass="form-control" id="balance" />
							</div>
						</div>
					</div> 

					<div class="form-group ">
						<input type="submit" class="form-control" value="Submit">
					</div>

				</form:form>
			</div>
		</div>
	</div>

</body>
</html>