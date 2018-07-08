
const express = require('express');
const NewsRouter = express.Router();
const {News} = require('../models/news');
const {Category} = require('../models/category');



NewsRouter.get('/', (req, res)=>{
   Category.findAll({
       
       include: [News],
       //order:[News, 'createdAt', 'DESC']
    }
    ).then(foundData=>{
       
         res.render('index', {
            categories: foundData 
         });
   }).catch(err=>{
       console.error('Error in retreiving news from db', err);
   });
});
// CREATE
NewsRouter.get('/add', (req, res)=>{
   
    Category.findAll({}).then((data)=>{
        res.render('form_addNews', {categories:data})
        
    }).catch((err)=>{
        console.log(err)
    });
});

NewsRouter.post('/', (req, res)=>{
    var body = req.body;
    console.log(body.category, typeof body.category);
    var news = {
        TITLE: body.title,
        PARAGRAPH: body.text,
        IMGURL: body.banner,
        categoryID: parseInt(body.category),
        DateCreated: new Date(),
        createdAt: new Date(),
        updatedAt: new Date(),
        
    };   
    News.create(news).then(newNews=>{
        res.redirect('/');
    });
});


NewsRouter.get('/:id/edit', (req, res)=>{
    var catArray = [];   
    Category.findAll({}).then(categoryData=>{
        catArray=categoryData
    }).then(function(){
        News.findById(req.params.id, {include: [Category]}).then(data=>{
            res.render('form_editNews', 
                {
                    news: data,
                    categories: catArray
                }
            );
        });
        
    });  
});


NewsRouter.put('/:id', (req, res)=>{
    var TITLE = req.body.title;
    var PARAGRAPH = req.body.text;
    var categoryID = parseInt(req.body.category);
    var IMGURL = req.body.banner;
    var updatedAt = new Date();

    News.findById(req.params.id).then(foundNews=>{
        foundNews.update({
            TITLE: TITLE,
            PARAGRAPH: PARAGRAPH,
            IMGURL: IMGURL,
            updatedAt: updatedAt,
            categoryID: categoryID
        }).then(updatedNews=>{
            console.log(updatedNews);
            res.redirect('/');
        });
    });
});

NewsRouter.delete('/:id', (req, res)=>{
    News.findById(req.params.id).then(foundNews=>{
        foundNews.destroy().then(function(){
            console.log('successfully deleted');
            res.redirect('/');
        });
    }).catch(err=>{
        console.log('Something went wrong', err);
    });
});
module.exports = {
    NewsRouter
};
