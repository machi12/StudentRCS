# StudentRCS
###1.1
这是一个学生教师上课签到的app<br/>
该项目分为两个app,分为学生端和教师端<br/>
学生端主要实现了登陆,报道和注销的功能,数据库放在了学生端,报道和注销会修改后台数据库的学生状态信息<br/>
教师端主要实现了登陆和共享学生端的数据库并对报道的学生进行显示和随机点名的功能.<br/>
共享数据库使用的是contentprovidr.<br/>
关于登陆账号的信息通过MyDatabaseHelper类导入<br/>

###1.2
在教师端增加了学生的注册功能,通过contentprovider类的insert方法实现(考虑到安全性方面,学生注册放在了教师端).

