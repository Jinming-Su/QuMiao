# QuMiao
A app for social contact

###pending
---
Next one month, maybe I don't have time to do this project.But I will complete it this year.

###程序名：趣喵
######通过有趣的事情认识他（她）
---
* 注册登录模块
	* 简易教程  
	* register.xml
		* 手机注册，短信验证注册
		* 每天推送短信，在服务器上进行配置
		* Gps地址获取
	* login.xml

* 发帖模块（点赞［认为有趣］，评价），发帖内容包括图片，不超过30s的视频，语音
* 好友模块（如果两个人同时认为对方有趣，自动加为好友，进行聊天）
* 个人信息模块

#####数据库设计
* user  

|name|type|default|  
|----|----|----|  
|uid|int|pk|  
|phone|varchar(20)|not null|  
|password|varchar(20)|not null|  
|nick_name|varchar(40)||  
|age|int||  
|sex|varchar(10)|check option 男女|  
|picture|varchar(256)|｜  
|address|varchar(256)||  

* message  

|name|type|default|  
|----|----|----|  
|mid|int|pk|  
|uid|int|not null|  
|content|varchar(1024)||  
|intrested_num|int|0|  

* interest  

|name|type|default|  
|----|----|----|  
|iid|int|pk|  
|source_uid|int|not null|  
|to_uid|int|not null|  

* friend  

|name|type|default|  
|----|----|----|  
|fid|int|pk|  
|uid1|int|not null|  
|uid2|int|not null|  
|last_chatting_time|timestamp|time_stamp|  
