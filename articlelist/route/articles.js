var mongoose = require('mongoose');

const Article = require('../models/Article');


//Webtask funtions
module.exports = (app) => {

    //fetch all the articles from the db
  app.get('/articles', (req, res) => {
      req.articleModel.find({}).sort({'created_at': -1}).exec((err, articles) => res.json(articles))
  });

  //Create new Article db
  app.post('/articles', (req, res) => {
      const newArticle = new req.articleModel(Object.assign({}, req.body, {created_at: Date.now()}));
      newArticle.save((err, savedArticle) => {
          res.json(savedArticle)
          res.json(req.body)
      })
  })

  //Update articles from the db with {id}
  app.put('/articles', (req, res) => {
    const idParam = req.webtaskContext.query.id;
    req.articleModel.findOne({_id: idParam}, (err, storyToUpdate) => {
        const updatedArticle = Object.assign(articleToUpdate, req.body);
        updatedArticle.save((err, article) => res.json(article))
    })
  })

  //Delete an articles from the db with {id}
  app.delete('/articles', (req, res) => {
    const idParam = req.webtaskContext.query.id;
    req.articleModel.remove({_id: idParam}, (err, removedArticle) => res.json(removedArticle));
  })
}