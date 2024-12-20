<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>

<html>

<head>

<meta charset="ISO-8859-1">

<script>

function calc() {
	
	var n1 = parseInt(document.getElementById('n1').value);
	
	var n2 = parseInt(document.getElementById('n2').value);
	
	var oper = document.getElementById('operatore').value;
	
	if (oper == '+'){
	   document.getElementById('result').value = n1 + n2;
	}
	
	if (oper == '-'){
		   document.getElementById('result').value = n1 - n2;
		}
	
	if (oper == '*'){
		   document.getElementById('result').value = n1 * n2;
		}
	
	if (oper == '/'){
		   document.getElementById('result').value = n1 / n2;
		}
	
}

</script>

<title>Insert title here</title>

</head>

<body>

<input type="text" id="n1"><br><br>

<input type="text" id="n2"><br><br>

<select id="operatore">

<option value="+">+</option>

<option value="-">-</option>

<option value="*">*</option>

<option value="/">/</option>

</select>



<button onclick="calc();">=</button>

<input type="text" id="result">

</body>

</html>