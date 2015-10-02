'use strict';

module.exports = angular.module('app', [
  /* modules */
  require('angular-ui-router'),
  require('./core/controllers/controllers').name,
]);