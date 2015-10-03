class ApiService {
    constructor($http){
        this._$http = $http;
    }

    getItems() {
        return this._$http.get('/item/', {});
    }

    getItem(id) {
        return this._$http.get('/item/'+id, {});
    }

    getPacks() {
        return this._$http.get('/box/', {});
    }

    getPack(id) {
        return this._$http.get('/box/'+id, {});
    }

    static templateFactory($http){
        return new ApiService($http);
    }
}

ApiService.templateFactory.$inject = ['$http'];

export default ApiService.templateFactory;