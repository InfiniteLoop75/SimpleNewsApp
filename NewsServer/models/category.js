const {db} = require('../db/dbconfig');
const {News} = require('./news');
var Category = db.define('category', {
    ID: {
        autoIncrement: true,
        primaryKey: true,
        type: db.Sequelize.INTEGER,
        allowNull: false
    },
    NAME:{
        allowNull: false,
        type: db.Sequelize.STRING,
        unique: true
    },
    DESCRIPTION:{
        allowNull:false,
        type: db.Sequelize.TEXT
    }
});


module.exports = {
    Category
}