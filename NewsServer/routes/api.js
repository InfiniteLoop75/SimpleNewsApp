const express = require('express');
const APIRouter = express.Router();
const {Category} = require('../models/category');
const {News} = require('../models/news');
const {db} = require('../db/dbconfig');
const Op = db.Op;
APIRouter.get('/', (req, res)=>{
    News.findAll(
        {
            where:{
                categoryID:{
                    [Op.ne]:null
                }
            }
        },
        {include:[Category]}
    ).then(found_results=>{
        var obj = {
            author: 'Ibrokhimjon Saydakhmatov',
            status: 200,
            news: found_results,
            adminPanel: 'http://localhost:30000'
        };
        res.status(200).json(obj);
    });
});

APIRouter.get('/categories', (req, res)=>{
    Category.findAll({include: [News]}).then(found_results=>{
        var obj = {
            author: 'Ibrokhimjon Saydakhmatov',
            status: 200,
            categories: found_results,
            adminPanel:'http://localhost:30000'
        }
        res.status(200).send(obj);
    }).catch(err=>{
        console.log(err);
    });
});

module.exports = {
    APIRouter
}