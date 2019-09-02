var express = require('express'),
  app = express(),
  port = process.env.PORT || 3001,
  //mongoose = require('mongoose'),
  //Task = require('./api/models/singtelModel'), //created model loading here
  bodyParser = require('body-parser');
  
// mongoose instance connection url connection
//mongoose.Promise = global.Promise;
//mongoose.connect('mongodb://localhost/Tododb'); 


app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());


var routes = require('./api/routes/singtelRoutes'); //importing route
routes(app); //register the route


app.listen(port);


console.log('todo list RESTful API server started on: ' + port);