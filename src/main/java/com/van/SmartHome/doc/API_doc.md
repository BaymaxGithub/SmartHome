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



### 二、终端命令发送

#### 1、终端发送控制指令


* 服务器地址和端口
>   81.69.99.55:8090 
  
* API
>   [POST] api/command
   
* URL示例
>   127.0.0.1:8080/api/command?uid=605322f1f10ad690ab3fec0f&commandContent=开卧室门
   
   
* 请求参数（指的是放在请求路径里面的参数）
   
| 请求参数 | 必选 | 类型及范围 | 说明 |
| :------ | :------ | :------ | :------ |
|  uid | true  |  String | 用户登录后返回数据结构里面的_id，这样才能知道指令是哪个用户发出来的|
|  commandContent | true  |  String | 用户发送的任何指令指令后台都原封不动的保存和转发给设备：比如'开卧室灯'，'开入户门'等| 
    
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
        "uid": "605322f1f10ad690ab3fec0f",
        "commandContent": "开卧室门",
        "createTime": 1616115250,
        "_id": "6053f6325df58bb02df35c01"
    }
}
```
   

  
### 三、图片上传  

#### 1、终端上传图片
    居家安防模式上传抓拍到到陌生人脸
 
  
* 服务器地址和端口
>   81.69.99.55:8090 
  
* API
>   [POST] api/file
   
* URL示例
>   127.0.0.1:8080/api/file?uid=605322f1f10ad690ab3fec0f
   
* 请求参数（指的是放在请求路径里面的参数）
   
| 请求参数 | 必选 | 类型及范围 | 说明 |
| :------ | :------ | :------ | :------ |
|  filename | true  |  String | 文件的名字 |
    
* 请求消息内容
   
```
    "file":图片
```
   
* 返回结果JSON示例
```
//返回的是文件的id，在其他地方需要上传文件的时候，先调用该接口上传文件，获取到这个文件的id，在其他地方使用这个id
{
    "result": {
        "_id": "5cac9bbb7e05d40246bb5eab"
    }
}
 
```  