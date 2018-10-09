--切换数据库
use chainDev
--创建用户
db.createUser({user: "admin", pwd: "admin", roles: [{ role: "dbOwner", db: "chainDev" }]})