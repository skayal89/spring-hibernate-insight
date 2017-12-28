<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script src="<c:url value="/static/jquery.1.8.2.min.js" />"></script>
<script src="<c:url value="/static/jquery.autocomplete.min.js" />"></script>
<link href="<c:url value="/static/main.css" />" rel="stylesheet">
</head>
<body>
  <h2>Spring MVC + jQuery + Autocomplete example</h2>

  <div>
	<input type="text"  id="w-input-search" value="">
	<span>
	  <button id="button-id" type="button">Search</button>
	</span>
  </div>

  <script>
  $(document).ready(function() {

	$('#w-input-search').autocomplete({
		serviceUrl: '/AutocompleteTest/getEmployees',
		paramName: "employeeName",
		delimiter: ",",
	   transformResult: function(response) {

		return {
		  //must convert json to javascript object before process
		  suggestions: $.map($.parseJSON(response), function(item) {

		      return { value: item, data: item };
		   })

		 };

            }

	 });

  });
  </script>

</body>
</html>