const express = require('express');
const CategoryRouter = express.Router();
const {Category} = require('../models/category');
const {News} = require('../models/news');

CategoryRouter.get('/', (req, res)=>{
    
    
    Category.findAll({include: [News]}).then(foundData=>{
        res.render('indexCategory', {categories: foundData});
    });
});
CategoryRouter.get('/add', (req, res)=>{
    res.render('form_addCategory');
});

CategoryRouter.post('/', (req, res)=>{
    var body = req.body;
    var name = body.name;
    var description = body.description;
    Category.create(
        {
            NAME: name, 
            DESCRIPTION: description, 
            createdAt: new Date(), 
            updatedAt: new Date()
        }
    ).then(newCat =>{
        console.log(newCat);
        res.redirect('/');
    }).catch(err=>{
        console.log(`Something went wrong while creating category: ${name}. \nSee detailed error here: \n ${err}`);
    });
});

CategoryRouter.get('/:id/edit', (req, res)=>{
    Category.findById(req.params.id)
        .then(foundData=>{
            res.render('form_editCategory', {category: foundData});
        })
        .catch(err=>{
            console.log('Smth went wrong');
        });
});

CategoryRouter.put('/:id', (req, res)=>{
    var body = req.body;
    var NAME = body.name;
    var DESCRIPTION = body.description;
    Category.findById(req.params.id)
        .then(foundCategory=>{
            foundCategory.update(
                {
                    NAME: NAME,
                    DESCRIPTION: DESCRIPTION,
                    updatedAt: new Date()
                }
            ).then(updatedCat =>{
                console.log(updatedCat);
                res.redirect('/');
            })
        })
        .catch();
});
CategoryRouter.delete('/:id', (req, res)=>{
    Category.findById(req.params.id)
        .then(foundCategory=>{
            foundCategory.destroy().then(function(){
                console.log(`${foundCategory.NAME} has been deleted sucessfully`);
                res.redirect('/categories');
            }).catch(err=>{
                console.log('Unable to delete');
               
            })
        })
        .catch(err=>{
            console.log('Can not find category', err)
        });
});
module.exports = {
    CategoryRouter
};
