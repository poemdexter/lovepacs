'use strict';

module.exports = angular.module('app', [
  /* modules */
  'ct.ui.router.extras',
  require('angular-ui-router'),
  require('./core/controllers/controllers').name,
]);