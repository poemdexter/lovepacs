export default angular.module('app.lovepac', [require('../directives/directives').name, require('../services/services').name, 'ui.bootstrap', 'fcsa-number'])
  .controller('dashboard', require('./dashboard/dashboard'))
  .controller('edit', require('./dashboard/edit'))
  .controller('create', require('./dashboard/create'))
  .controller('container', require('./container/container'))
  .controller('items', require('./item/items'))
  .controller('item', require('./item/edit'));