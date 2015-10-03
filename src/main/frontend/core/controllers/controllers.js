export default angular.module('app.lovepac', [require('../directives/directives').name, require('../services/services').name])
  .controller('dashboard', require('./dashboard/dashboard'))
  .controller('container', require('./container/container'))
  .controller('items', require('./item/items'))
  .controller('item', require('./item/edit'));