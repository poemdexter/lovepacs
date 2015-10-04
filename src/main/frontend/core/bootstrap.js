/*jshint node:true */
/*jshint esnext:true */
'use strict';

global.$ = global.jQuery = require('jquery');
require('angular');
require('angular-ui-bootstrap');
require('bootstrap-sass');
require('ui-router-extras');
require('font-awesome-webpack');
require('angular-fcsa-number');

require('../styles/index.scss');


var appModule = require('../index');

require('./config/config')(appModule);

angular.element(document).ready(() => {
  angular.bootstrap(document, [appModule.name], {
      //strictDi: true
    }
  );
});