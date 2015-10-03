class ApiService {
    constructor($http){
        this._$http = $http;
    }

    getItems() {
        return this._$http.get('/item/', {});
    }

    static templateFactory($http){
        return new ApiService($http);
    }
}

ApiService.templateFactory.$inject = ['$http'];

export default ApiService.templateFactory;