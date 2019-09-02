'use strict';
module.exports = function(app) {
  var singtellist = require('../controllers/singtelController');

  // todoList Routes
  app.route('/tasks')
    //.get(singtellist.list_all_tasks)
    .post(singtellist.create_a_task);
};