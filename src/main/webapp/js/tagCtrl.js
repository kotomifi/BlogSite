/**
 * Created by baisu on 15-4-10.
 */

var app = angular.module('mainApp', []);
app.controller('getTagsCtrl', ['$scope', '$http', '$window', function($scope, $http, $window) {
    $scope.f_tag = true;
    $scope.f_blog = true;

    $http.get(tag_path)
        .success(function(response) {
            $scope.tags = response;
        });

    $scope.goTag = function(tag_name) {
        $scope.f_tag = false;
        $scope.f_blog = true;

        $http.get(get_blog_by_tag + tag_name)
            .success(function(response) {
                $scope.blogs = response;
            });

    }

    $scope.goBlog = function(blog_id) {
        $window.location.href = find_blog_by_id + blog_id;
    }
}]);
