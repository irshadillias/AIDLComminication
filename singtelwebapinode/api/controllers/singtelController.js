'use strict';


//var mongoose = require('mongoose'),
//Task = mongoose.model('Singteldata');

exports.list_all_tasks = function(req, res) {
    res.send({ message: 'Ad updated.' });
  /*Task.find({}, function(err, task) {
    if (err)
      res.send(err);
    res.json(task);
  });*/
};

exports.create_a_task = function(req, res) {
    res.send({ message: 'Ad updated.' });
};



