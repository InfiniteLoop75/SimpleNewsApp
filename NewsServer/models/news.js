const {db} = require('../db/dbconfig');
const {Category} = require("./category");
var News = db.define('news', {
    ID: {
        autoIncrement: true,
        primaryKey: true,
        type: db.Sequelize.INTEGER,
        allowNull: false
    },
    TITLE:{
        allowNull: false,
        type: db.Sequelize.STRING
    },
    PARAGRAPH:{
        allowNull:false,
        type: db.Sequelize.TEXT
    },
    IMGURL:{
        allowNull: false,
        type: db.Sequelize.TEXT,
    },
    DateCreated:{
        allowNull: false,
        type: db.Sequelize.DATE 
    }

});
module.exports = {
    News
}