<!DOCTYPE html>
<html lang="en">
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<body>

	<div ng-app="myApp" ng-controller="formCtrl">
		<form name="myForm" >
			Search Key<br> <input type="text" ng-model="user.postOffice" required><br>
			<span ng-show="user.postOffice.$touched && user.postOffice.$invalid">The Serach key is required.
			</span>
			<br>
			<button ng-click="reset()">RESET</button>
			<button ng-click="submit()">Search</button>
		</form>
		

		<table>
		<th> POST Office </th>
		<th> Pincode </th>
			<tr ng-repeat="post in posts">
				<td>{{ post.officename }}</td>
				<td>{{ post.pincode }}</td>
			</tr>
		</table>

	</div>

	<script>
		var app = angular.module('myApp', []);
		app
				.controller(
						'formCtrl',
						function($scope, $http) {

							$scope.reset = function() {
								$scope.user = angular.copy($scope.master);
							};

							$scope.submit = function() {
								//  $scope.master = angular.copy($scope.user);
								//  $http.get('http://localhost:8080/matrimony/services/system/v1/address?postOffice=KUM', config).then(successCallback, errorCallback);

								console.log($scope.user);
								$http(
										{
											method : 'GET',
											url : 'http://localhost:8080/matrimony/services/system/v1/address?postOffice='
													+ $scope.user.postOffice,
											headers : {
												'Accept' : 'application/json'
											}
										})
										.success(
												function(data, status, headers,
														config) {
													$scope.posts = data;
												}).error(
												function(data, status, headers,
														config) {
												});
								//      var responsePromise = $http.get("http://localhost:8080/matrimony/services/system/v1/address?postOffice=KUM",{});

							};

						});
	</script>

</body>
</html>
