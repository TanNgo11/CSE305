<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<select id="reportDropdown" style="width: 20%" class="form-select"
		aria-label="Default select example">

		<option value="1">Report By Week</option>
		<option value="2">Report By Month</option>
		<option value="3">Report By Year</option> 
	</select>


	<div id="chartContainer" style="height: 370px; width: 100%;"></div>
	<script src="https://cdn.canvasjs.com/canvasjs.min.js"></script>
	<script>
   
    $('#reportDropdown').on('change', function() {
     
      var selectedValue = $(this).val();

    
      switch (selectedValue) {
        case '1':
          window.location.href = '/chart/piechart?type=week';
         
          break;
        case '2':
          window.location.href = '/chart/piechart?type=month';
          
          break;
        case '3':
          window.location.href = '/chart/piechart?type=year';
          
          break;
        default:
          
      }
    });
  </script>

	<script type="text/javascript">
	
	
		
		var typeValue = null;
		
		function extractParametersFromURL() {
			const currentURL = window.location.href;

			const questionMarkIndex = currentURL.indexOf('?');

			if (questionMarkIndex !== -1) {
				const queryString = currentURL.slice(questionMarkIndex + 1);
				const queryParams = queryString.split('&');
				const params = {};

				queryParams.forEach(param => {
					const [key, value] = param.split('=');
					params[key] = value;
				});

				typeValue = capitalizeFirstLetter(params['type'] + "ly");
				
			} else {
				console.log('No query parameters found.');
			}
		}
		function capitalizeFirstLetter(string) {
	        return string.charAt(0).toUpperCase() + string.slice(1);
	    }
		
		
		extractParametersFromURL();
		
		window.onload = function() {
			 
			 
			var dps = [[]];
			var chart = new CanvasJS.Chart("chartContainer", {
				theme: "light2", // "light1", "dark1", "dark2"
				exportEnabled: true,
				animationEnabled: true,
				title: {
					text: `\${typeValue} Expenses `
				},
				data: [{
					type: "pie",
					showInLegend: "true",
					legendText: "{label}",
					yValueFormatString: "#,###\"%\"",
					indexLabelFontSize: 16,
					indexLabel: "{label} - {y}",
					dataPoints: dps[0]
				}]
			});

			var yValue;
			var label;

			<c:forEach items="${dataPointsList}" var="dataPoints" varStatus="loop">
				<c:forEach items="${dataPoints}" var="dataPoint">
					yValue = parseFloat("${dataPoint.y}");
					label = "${dataPoint.label}";
					dps[parseInt("${loop.index}")].push({
						label: label,
						y: yValue,
					});
				</c:forEach>
			</c:forEach>

			chart.render();
			
			
			
		}
		
		
	</script>
</body>
</html>
