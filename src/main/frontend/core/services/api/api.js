class ApiService {
    constructor($http){
        this._$http = $http;
    }

    getUser() {
        return this._$http.get('/user/info/', {});
    }

    getLocations() {
        return this._$http.get('/location/', {});
    }

    updateInventory(data) {
        return this._$http.put('/location/', data);
    }

    saveInventory(data) {
        return this._$http.post('/location/', data);
    }

    getLocation(id) {
        return this._$http.get('/location/'+id, {});
    }

    updateLocation(data) {
        return this._$http.put('/location/', data);
    }

    createLocation(data) {
        return this._$http.post('/location/', data);
    }

    getItems() {
        return this._$http.get('/item/', {});
    }

    getItem(id) {
        return this._$http.get('/item/'+id, {});
    }

    updateItem(data) {
        return this._$http.put('/item/', data);
    }

    saveItem(data) {
        return this._$http.post('/item/', data);
    }

    getPacks() {
        return this._$http.get('/box/', {});
    }

    getPack(id) {
        return this._$http.get('/box/'+id, {});
    }

    updatePack(data) {
        return this._$http.put('/box/', data);
    }

    static templateFactory($http){
        return new ApiService($http);
    }
}

ApiService.templateFactory.$inject = ['$http'];

export default ApiService.templateFactory;