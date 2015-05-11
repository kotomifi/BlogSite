/**
 * Created by baisu on 15-4-10.
 */

//var tag_path = "rest/tag/tags";
//
//var top_blog_path = "rest/blog/topBlogs";

var app = angular.module('mainApp', ['infinite-scroll']);

app.controller('getBlogsCtrl', ['$scope', '$http', function($scope, $http) {
    $http.get(top_blog_path)
        .success(function(response) {
            $scope.blogs = response;
        });
}]);


app.controller('getTagsCtrl', ['$scope', '$http', function($scope, $http) {
    $http.get(tag_path)
        .success(function(response) {
            $scope.tags = response;
        });
}]);

app.controller('moreBlogCtrl', function($scope, Reddit, $window) {
    $scope.reddit = new Reddit();

    $scope.go = function(blog_id) {
        $window.location.href = find_blog_by_id + blog_id;
    }
});

// Reddit constructor function to encapsulate HTTP and pagination logic
app.factory('Reddit', function($http) {
    var Reddit = function() {
        this.items = [];
        this.busy = false;
        this.after = 4428664638107;
    };

    Reddit.prototype.nextPage = function() {
        if (this.busy) return;
        this.busy = true;

        var url = more_blog_path + "/" + this.after;
        $http.get(url).success(function(data) {
            var items = data;
            for (var i = 0; i < items.length; i++) {
                this.items.push(items[i]);
            }
            this.after = this.items[this.items.length - 1].blog_id;
            this.busy = false;
        }.bind(this));
    };

    return Reddit;
});


