# ArticleApi-webtask-Android
Article API with [Webtask](https://webtask.io) for Android. Webtasks is a simple, lightweight, and secure way of running isolated backend code that removed or reduces the need for a backend.

![Article Android App](http://res.cloudinary.com/hngfun/image/upload/v1519336613/Screenshot_20180222-101125_ctzbes.png)



### Prerequisites

You need all this get started
 1. Android Studio 3.0+
 2. RxJava
 3. Knowledge of MVVM
 4. Dagger
 5. Retrofit


### Setting up Webtask
![Webtask](https://webtask.io/images/symbol.svg)

Firstly your need to install the command line tool with the following command. This will get all you need set.
```
$ npm i -g wt-cli 
$ wt init
```
To test if it working. Create ```hello.js``` file and add this functions
```
module.exports = function (done) { 
done(null, ‘Hello!’);
 }
 ```
The simply create a function on the webtask server that returns Hello! when the function is triggered. Just run
```
$ wt create hello.js
```
This will return a URL on your terminal . Test the URL with your browser any console of your choice.

![Webtask test](http://res.cloudinary.com/hngfun/image/upload/v1519337316/Screen_Shot_2018-02-22_at_10.39.13_AM_xoaigm.png)



### Testing the API

Your can test this endpoint on Postman or any other console

Add new articles by passed a body with following fields title, content, imageurl, author
```
 POST https://wt-e681794554391ac97c69e1539e0c67df-0.run.webtask.io/article-api/articles
```
Fetch all  articles 
```
GET https://wt-e681794554391ac97c69e1539e0c67df-0.run.webtask.io/article-api/articles
```
Update a article with an {id}
```
PUT https://wt-e681794554391ac97c69e1539e0c67df-0.run.webtask.io/article-api/articles
```
Delete article with an {id}
```
DELETE https://wt-e681794554391ac97c69e1539e0c67df-0.run.webtask.io/article-api/articles
```

### Deploying your APP
We will now deploy our webtask functions. ```MONGOLAB-CONNECTION-URL``` should be replaced with your Mongolab DB URL.

```
wt create index --secret MONGO_URL=<MONGOLAB-CONNECTION-URL> --bundle
```
![Payload for Postman](http://res.cloudinary.com/hngfun/image/upload/v1519337187/Screen_Shot_2018-02-22_at_10.42.53_PM_wghm6g.png)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Codebeast

