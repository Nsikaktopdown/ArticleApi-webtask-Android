var Express = require('express');
var Webtask = require('webtask-tools');
var bodyParser = require('body-parser');
var app = Express();

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

//database connection/disconnection and routes
app.use(require('./middleware/db').connectDisconnect);
require('./route/articles')(app);

module.exports = Webtask.fromExpress(app);