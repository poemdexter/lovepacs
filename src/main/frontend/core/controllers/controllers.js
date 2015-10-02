export default angular.module('app.lovepac', [require('../directives/directives').name, require('../services/services').name])
  .controller('dashboard', require('./dashboard/dashboard'));