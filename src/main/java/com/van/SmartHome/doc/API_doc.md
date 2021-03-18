## API文档

    
> 用户数据结构

    private ObjectId id;      //用户唯一标识
    private String userName;   //用户名
    private String email;    //邮箱
    private String phone;   //电话
    private String password;   //密码 md5加密
    private Long createTime;   //用户创建时间
    private Long updateTime;   //用户名数据更新时间
       
 



### 一、用户管理 

#### 1、用户注册


* 服务器地址和端口
>   81.69.99.55:8090 
  
* API
>   [POST] api/user
   
* URL示例
>   http://81.69.99.55:8090/api/user?password=11&email=11&phone=11&userName=11
   
   
* 请求参数（指的是放在请求路径里面的参数）
   
| 请求参数 | 必选 | 类型及范围 | 说明 |
| :------ | :------ | :------ | :------ |
|  userName | true  |  String | 用户的姓名|
|  email | true  |  String | 用户的邮箱|
|  phone | true  |  String | 用户的电话|
|  password | true  |  String | 用户设置的密码| 
    
* 请求消息内容（指的是放在请求体里面的参数）JSON格式
   
```
无
```
   
* 返回结果处理逻辑
```
    所有正常的返回结果都是JSON格式的字符串，并且都包含在以result为键的json值中。
    首先要判断的是result里面有没有包含error字段，如果有就代表操作有误，这时候需要将error对应的值直接
    弹出对话框显示即可。
    如果不包含error就表示返回结果是预期的数据，按照需要进行字段读取。   
```

* 返回结果JSON示例
```

正确结果：
{
    "result": {
        "userName": "11",
        "email": "11",
        "phone": "11",
        "password": "11",
        "createTime": 1616061169,
        "updateTime": 1616061169,
        "_id": "605322f1f10ad690ab3fec0f"
    }
} 
```
   

 
 
#### 2、用户登录
  

* 服务器地址和端口
>   81.69.99.55:8090   
  
* API
>   [GET] api/user/login

* URL示例
>  http://81.69.99.55:8090/api/user/login?email=11&password=111

  
* 请求参数（指的是放在请求路径里面的参数）
   
| 请求参数 | 必选 | 类型及范围 | 说明 |
| :------ | :------ | :------ | :------ |
|  email | true  |  String | 用户的邮箱|
|  password | true  |  String | 用户设置的密码| 


 
* 返回结果处理逻辑
```
    所有正常的返回结果都是JSON格式的字符串，并且都包含在以result为键的json值中。
    首先要判断的是result里面有没有包含error字段，如果有就代表操作有误，这时候需要将error对应的值直接
    弹出对话框显示即可。
    如果不包含error就表示返回结果是预期的数据，按照需要进行字段读取。   
```

* 返回结果JSON示例
```

正确结果：
{
    "result": {
        "userName": "11",
        "email": "11",
        "phone": "11",
        "password": "11",
        "createTime": 1616061169,
        "updateTime": 1616061169,
        "_id": "605322f1f10ad690ab3fec0f"
    }
}


可能的错误结果1
{
    "result": {
        "error": "用户不存在"
    }
}
```



  