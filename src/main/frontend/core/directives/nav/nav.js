class NavCtrl {
  constructor() {
  }
}

export default () => {
  return {
    controller: NavCtrl,
    controllerAs: 'nav',
    template: require('./nav.html'),
    link: function ( scope, elem, attrs ) {
      //$(elem).closest("ui-view").addClass("has-nav");
    }
  };
};