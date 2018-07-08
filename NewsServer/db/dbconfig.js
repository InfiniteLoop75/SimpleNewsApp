const Sequelize = require('sequelize');
const db = new Sequelize('news_server', 'root', 'android6699', {
    host: 'localhost',
    dialect: 'mysql',
    operatorsAliases: false,
    dialectOptions:{
        multipleStatements: true
    },
    pool:{
        max: 5,
        min: 0,
        acquire: 30000,
        idle: 10000
    }
});

db
    .authenticate()
    .then(()=>{
        console.log('Connection is established. Ready to work')
    })
    .catch(err=>{
        console.log('Unable to connect to db', err);
    });

module.exports = {
    db
}