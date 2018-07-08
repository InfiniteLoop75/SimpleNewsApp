const express = require('express');
const bodyParser = require('body-parser');
const methOverride = require('method-override');
const app = express();
const {db} = require('./db/dbconfig');
const {News} = require('./models/news');
const {Category} = require('./models/category');
const {CategoryRouter} = require('./routes/category');
const {NewsRouter} = require('./routes/news');
const {APIRouter} = require('./routes/api');
app.use(express.static("public"));
app.use(bodyParser.urlencoded({extended: true}));
app.use(methOverride('_method'));
app.set('view engine', 'ejs');
News.belongsTo(Category);
Category.hasMany(News);
Category.sync();
News.sync();
app.use('/categories', CategoryRouter)
app.use('/news', NewsRouter);
app.use('/api', APIRouter);

app.get('/', (req, res)=>{
    res.redirect('/news');
});
app.listen(3000, ()=>{
    console.log("SERVER IS UP AND RUNNING IN PORT 30000");
})