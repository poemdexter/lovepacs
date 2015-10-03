class ApiService {
    constructor($http){
        this._$http = $http;
    }

    getLocations() {
        return this._$http.get('/location/', {});
    }

    getLocation(id) {
        return this._$http.get('/location/'+id, {});
    }

    getItems() {
        return this._$http.get('/item/', {});
    }

    getItem(id) {
        return this._$http.get('/item/'+id, {});
    }

    updateItem(id, data) {
        return this._$http.put('/item/', data);
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