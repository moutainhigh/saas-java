--切换数据库
use payDev
--创建用户
db.createUser({user: "admin", pwd: "admin", roles: [{ role: "dbOwner", db: "payDev" }]})
--创建test集合
db.test.insert({"employees" : [ 
        {
            "firstName" : "Bill",
            "lastName" : "Gates"
        }, 
        {
            "firstName" : "George",
            "lastName" : "Bush"
        }, 
        {
            "firstName" : "Thomas",
            "lastName" : "Carter"
        }
    ]})
--查询test集合记录数
db.getCollection('test').count()
--移除test集合中address字段
db.test.update({}, {$unset:{'address':''}}, false, true)
--给bossPayInfo集合添加wxpayModel字段
db.bossPayInfo.update({}, {$set:{'wxpayModel':NumberInt(1)}}, {multi: 1})