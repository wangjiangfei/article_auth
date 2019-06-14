### 1、 登录

**简要描述：**

- 用户登录

**请求URL：**

- http://localhost:8080/login/auth

**请求方式：**

- POST

**请求示例：**

```
{
	"userName":"admin",
	"password":"123456"
}
```

**返回示例：**

```json
{
    "code": 300,
    "msg": "用户名或密码错误",
    "info": null
}
```

```json
{
    "code": 100,
    "msg": "请求成功",
    "info": {
        "tokenName": "b59086f2-37d7-47f4-81f8-d40779190203",
        "createTime": 1559140364006,
        "effectiveMinutes": 60,
        "userId": 10003
    }
}
```

**参数说明：**

| 字段名 | 变量名   | 必填 | 类型   | 描述 |
| ------ | -------- | ---- | ------ | ---- |
| 用户名 | userName | 是   | String |      |

**备注：**



### 2、 获取用户信息

**简要描述：**

- 获取用户信息

**请求URL：**

- http://localhost:8080/login/getInfo

**请求方式：**

- POST     头部需要携带Token

**请求示例：**

```
token 1c9a7e74-a5dd-4ae6-8b90-cf2cf60292a0
```

**返回示例：**

```json
{
    "code": 20010,
    "msg": "无token，请重新登录",
    "info": null
}
```

```json
{
    "code": 20011,
    "msg": "登陆已过期,请重新登陆",
    "info": null
}
```

```json
{
    "code": 100,
    "msg": "请求成功",
    "info": {
        "userId": 10003,
        "userName": "admin",
        "nickname": "超级用户23",
        "roleId": 1,
        "roleName": "管理员",
        "menuList": [],
        "permissionList": []
    }
}
```

```json
{
    "code": 100,
    "msg": "请求成功",
    "info": {
        "userId": 10004,
        "userName": "user",
        "nickname": "莎士比亚",
        "roleId": 2,
        "roleName": "作家",
        "menuList": [
            "role",
            "user",
            "article"
        ],
        "permissionList": [
            "article:list",
            "user:list",
            "user:add",
            "role:update",
            "article:add",
            "role:list",
            "article:update",
            "user:update",
            "role:delete",
            "role:add"
        ]
    }
}
```

**参数说明：**

| 字段名 | 变量名 | 必填 | 类型 | 描述 |
| ------ | ------ | ---- | ---- | ---- |
|        |        |      |      |      |

**备注：**



### 3、 获取文章列表

**简要描述：**

- 获取文章列表

**请求URL：**

- http://localhost:8080/article/listArticle?pageNum=1&pageRow=10

**请求方式：**

- GET     头部需要携带Token

**请求示例：**

```
token 1c9a7e74-a5dd-4ae6-8b90-cf2cf60292a0
http://localhost:8080/article/listArticle?pageNum=1&pageRow=10
```

**返回示例：**

```json
{
    "code": 100,
    "msg": "请求成功",
    "info": {
        "articleInfoList": [
            {
                "articleId": 5,
                "content": "莎士比亚",
                "createTime": 1508893725000,
                "updateTime": 1559203700000,
                "deleteStatus": "1",
                "createUserId": 10003,
                "updateUserId": 10003,
                "createUserName": "admin",
                "updateUserName": "admin"
            },
            {
                "articleId": 6,
                "content": "亚里士多德",
                "createTime": 1508986168000,
                "updateTime": 1559265434000,
                "deleteStatus": "1",
                "createUserId": 10007,
                "updateUserId": 10009,
                "createUserName": "test",
                "updateUserName": "bbb"
            },
            {
                "articleId": 10,
                "content": "亚历山大",
                "createTime": 1509001065000,
                "updateTime": 1559203732000,
                "deleteStatus": "1",
                "createUserId": 10009,
                "updateUserId": 10009,
                "createUserName": "bbb",
                "updateUserName": "bbb"
            },
            {
                "articleId": 11,
                "content": "李白",
                "createTime": 1509002622000,
                "updateTime": 1559203739000,
                "deleteStatus": "1",
                "createUserId": 10009,
                "updateUserId": 10009,
                "createUserName": "bbb",
                "updateUserName": "bbb"
            },
            {
                "articleId": 19,
                "content": "文章test2",
                "createTime": 1510983427000,
                "updateTime": 1559203745000,
                "deleteStatus": "1",
                "createUserId": 10009,
                "updateUserId": 10009,
                "createUserName": "bbb",
                "updateUserName": "bbb"
            }
        ],
        "pageNum": 1,
        "pageRow": 10,
        "count": 5
    }
}
```

```json
{
    "code": 250,
    "msg": "无权限访问, 权限不足",
    "info": null
}
```

**参数说明：**

| 字段名 | 变量名 | 必填 | 类型 | 描述 |
| ------ | ------ | ---- | ---- | ---- |
|        |        |      |      |      |

**备注：**



### 4、 新增文章

**简要描述：**

- 新增文章

**请求URL：**

- http://localhost:8080/article/addArticle

**请求方式：**

- POST     头部需要携带Token

**请求示例：**

```
token 1c9a7e74-a5dd-4ae6-8b90-cf2cf60292a0
```

```json
{
	"content":"新增文章"
}
```

**返回示例：**

```json
{
    "code": 250,
    "msg": "无权限访问, 权限不足",
    "info": null
}
```

```json
{
    "code": 100,
    "msg": "请求成功",
    "info": null
}
```

**参数说明：**

| 字段名 | 变量名 | 必填 | 类型 | 描述 |
| ------ | ------ | ---- | ---- | ---- |
|        |        |      |      |      |

**备注：**



### 5、 更新文章

**简要描述：**

- 更新文章

**请求URL：**

- http://localhost:8080/article/updateArticle

**请求方式：**

- PUT     头部需要携带Token

**请求示例：**

```
token 1c9a7e74-a5dd-4ae6-8b90-cf2cf60292a0
```

```json
{
	"articleId":20,
	"content":"修改后的内容"
}
```

**返回示例：**

```json
{
    "code": 250,
    "msg": "无权限访问, 权限不足",
    "info": null
}
```

```json
{
    "code": 100,
    "msg": "请求成功",
    "info": null
}
```

**参数说明：**

| 字段名 | 变量名 | 必填 | 类型 | 描述 |
| ------ | ------ | ---- | ---- | ---- |
|        |        |      |      |      |

**备注：**



### 6、 删除文章

**简要描述：**

- 删除文章

**请求URL：**

- http://localhost:8080/back/article/deleteArticle/20

**请求方式：**

- DELETE     头部需要携带Token

**请求示例：**

```
token 1c9a7e74-a5dd-4ae6-8b90-cf2cf60292a0
http://localhost:8080/article/deleteArticle/20
```

**返回示例：**

```json
{
    "code": 100,
    "msg": "请求成功",
    "info": null
}
```

**参数说明：**

| 字段名 | 变量名 | 必填 | 类型 | 描述 |
| ------ | ------ | ---- | ---- | ---- |
|        |        |      |      |      |

**备注：**



### 7、 查询文章详情

**简要描述：**

- 查询文章详情

**请求URL：**

- http://localhost:8080/article/detail/19

**请求方式：**

- GET

**请求示例：**

```
token 1c9a7e74-a5dd-4ae6-8b90-cf2cf60292a0
```

```
http://localhost:8080/article/detail/19
```

**返回示例：**

```json
{
    "code": 100,
    "msg": "请求成功",
    "info": {
        "articleId": 19,
        "content": "文章test2",
        "createTime": 1510983427000,
        "updateTime": 1559203745000,
        "deleteStatus": "1",
        "createUserId": 10009,
        "updateUserId": 10009,
        "createUserName": "bbb",
        "updateUserName": "bbb"
    }
}
```

**参数说明：**

| 字段名 | 变量名 | 必填 | 类型 | 描述 |
| ------ | ------ | ---- | ---- | ---- |
|        |        |      |      |      |

**备注：**



### 8、 查询用户列表

**简要描述：**

- 查询用户列表

**请求URL：**

- http://localhost:8080/user/list?pageNum=1&pageRow=10

**请求方式：**

- GET

**请求示例：**

```
token 1c9a7e74-a5dd-4ae6-8b90-cf2cf60292a0
```

```
http://localhost:8080/user/list?pageNum=1&pageRow=10
```

**返回示例：**

```json
{
    "code": 100,
    "msg": "请求成功",
    "info": {
        "userInfoList": [
            {
                "userId": 10003,
                "userName": "admin",
                "nickname": "超级用户23",
                "roleId": 1,
                "roleName": "管理员",
                "userCreateTime": 1509335558000,
                "userUpdateTime": 1559138071000,
                "permissionNameList": [
                    "文章管理列表",
                    "角色权限列表",
                    "用户新增",
                    "角色权限修改",
                    "文章管理修改",
                    "角色权限新增",
                    "用户修改",
                    "文章管理新增",
                    "用户列表",
                    "角色权限删除"
                ]
            },
            {
                "userId": 10004,
                "userName": "user",
                "nickname": "莎士比亚",
                "roleId": 2,
                "roleName": "作家",
                "userCreateTime": 1509351182000,
                "userUpdateTime": 1559138075000,
                "permissionNameList": [
                    "文章管理列表",
                    "角色权限列表",
                    "用户新增",
                    "角色权限修改",
                    "文章管理修改",
                    "角色权限新增",
                    "用户修改",
                    "文章管理新增",
                    "用户列表",
                    "角色权限删除"
                ]
            },
            {
                "userId": 10005,
                "userName": "aaa",
                "nickname": "abba",
                "roleId": 1,
                "roleName": "管理员",
                "userCreateTime": 1510725776000,
                "userUpdateTime": 1559138079000,
                "permissionNameList": [
                    "文章管理列表",
                    "角色权限列表",
                    "用户新增",
                    "角色权限修改",
                    "文章管理修改",
                    "角色权限新增",
                    "用户修改",
                    "文章管理新增",
                    "用户列表",
                    "角色权限删除"
                ]
            },
            {
                "userId": 10007,
                "userName": "test",
                "nickname": "就看看列表",
                "roleId": 3,
                "roleName": "程序员",
                "userCreateTime": 1511339381000,
                "userUpdateTime": 1511339381000,
                "permissionNameList": [
                    "角色权限列表",
                    "文章管理列表",
                    "角色权限新增",
                    "用户修改",
                    "用户列表",
                    "文章管理新增",
                    "角色权限删除"
                ]
            },
            {
                "userId": 10009,
                "userName": "bbb",
                "nickname": "测试昵称",
                "roleId": 4,
                "roleName": "只看文章",
                "userCreateTime": 1559096615000,
                "userUpdateTime": 1559138082000,
                "permissionNameList": [
                    "文章管理列表"
                ]
            }
        ],
        "pageNum": 1,
        "pageRow": 10,
        "count": 5
    }
}
```

**参数说明：**

| 字段名 | 变量名 | 必填 | 类型 | 描述 |
| ------ | ------ | ---- | ---- | ---- |
|        |        |      |      |      |

**备注：**



### 9、 添加用户

**简要描述：**

- 添加用户

**请求URL：**

- http://localhost:8080/back/user/addUser

**请求方式：**

- POST

**请求示例：**

```
token 1c9a7e74-a5dd-4ae6-8b90-cf2cf60292a0
```

```json
{
	"userName":"user3",
	"password":"123456",
	"nickname":"nicheng",
	"roleId":2
}
```

**返回示例：**

```json
{
    "code": 10009,
    "msg": "账户已存在",
    "info": null
}
```

```json
{
    "code": 100,
    "msg": "请求成功",
    "info": null
}
```

**参数说明：**

| 字段名 | 变量名 | 必填 | 类型 | 描述 |
| ------ | ------ | ---- | ---- | ---- |
|        |        |      |      |      |

**备注：**



### 10、 查询某用户的详细信息

**简要描述：**

- 查询某用户的详细信息

**请求URL：**

- http://localhost:8080/user/detail/10003

**请求方式：**

- GET

**请求示例：**

```
token 1c9a7e74-a5dd-4ae6-8b90-cf2cf60292a0
```

```json
http://localhost:8080/back/user/detail/10003
```

**返回示例：**

```json
{
    "code": 100,
    "msg": "请求成功",
    "info": {
        "userId": 10003,
        "userName": "admin",
        "password": null,
        "nickname": "超级用户23",
        "roleId": "1",
        "createTime": 1509335558000,
        "updateTime": 1559138071000,
        "deleteStatus": "1"
    }
}
```

**参数说明：**

| 字段名 | 变量名 | 必填 | 类型 | 描述 |
| ------ | ------ | ---- | ---- | ---- |
|        |        |      |      |      |

**备注：**

### 11、更新用户

**简要描述：**

- 更新用户，密码 password 可传可不传

**请求URL：**

- http://localhost:8080/user/updateUser

**请求方式：**

- PUT

**请求示例：**

```
token 1c9a7e74-a5dd-4ae6-8b90-cf2cf60292a0
```

```json
{
	"userId": 10011,
	"password":"123456789",
	"nickname":"nic",
	"roleId": 3
}
```

**返回示例：**

```json
{
    "code": 100,
    "msg": "请求成功",
    "info": null
}
```

**参数说明：**

| 字段名 | 变量名 | 必填 | 类型 | 描述 |
| ------ | ------ | ---- | ---- | ---- |
|        |        |      |      |      |

**备注：**



### 12、查询所有的角色

**简要描述：**

- 查询所有的角色，在添加/修改用户的时候要使用接口

**请求URL：**

- http://localhost:8080/user/getAllRoles

**请求方式：**

- GET

**请求示例：**

```
token 1c9a7e74-a5dd-4ae6-8b90-cf2cf60292a0
```

**返回示例：**

```json
{
    "code": 100,
    "msg": "请求成功",
    "info": [
        {
            "roleId": 1,
            "roleName": "管理员",
            "createTime": 1511339074000,
            "updateTime": 1511339092000,
            "deleteStatus": "1"
        },
        {
            "roleId": 2,
            "roleName": "作家",
            "createTime": 1511339074000,
            "updateTime": 1511339092000,
            "deleteStatus": "1"
        },
        {
            "roleId": 3,
            "roleName": "程序员",
            "createTime": 1511339327000,
            "updateTime": 1511339327000,
            "deleteStatus": "1"
        },
        {
            "roleId": 4,
            "roleName": "只看文章",
            "createTime": 1559096533000,
            "updateTime": 1559096533000,
            "deleteStatus": "1"
        }
    ]
}
```

**参数说明：**

| 字段名 | 变量名 | 必填 | 类型 | 描述 |
| ------ | ------ | ---- | ---- | ---- |
|        |        |      |      |      |

**备注：**



### 13、角色列表

**简要描述：**

- 角色列表

**请求URL：**

- http://localhost:8080/role/listRole

**请求方式：**

- GET

**请求示例：**

```
token 1c9a7e74-a5dd-4ae6-8b90-cf2cf60292a0
```

**返回示例：**

```json
{
    "code": 100,
    "msg": "请求成功",
    "info": [
        {
            "roleId": 1,
            "roleName": "管理员",
            "users": [
                {
                    "userId": 10003,
                    "nickname": "超级用户23"
                },
                {
                    "userId": 10005,
                    "nickname": "abba"
                }
            ],
            "menus": [
                {
                    "menuCode": "article",
                    "menuName": "文章管理",
                    "permissions": [
                        {
                            "permissionId": 101,
                            "permissionName": "列表"
                        },
                        {
                            "permissionId": 102,
                            "permissionName": "新增"
                        },
                        {
                            "permissionId": 103,
                            "permissionName": "修改"
                        }
                    ]
                },
                {
                    "menuCode": "user",
                    "menuName": "用户",
                    "permissions": [
                        {
                            "permissionId": 601,
                            "permissionName": "列表"
                        },
                        {
                            "permissionId": 602,
                            "permissionName": "新增"
                        },
                        {
                            "permissionId": 603,
                            "permissionName": "修改"
                        }
                    ]
                },
                {
                    "menuCode": "role",
                    "menuName": "角色权限",
                    "permissions": [
                        {
                            "permissionId": 701,
                            "permissionName": "列表"
                        },
                        {
                            "permissionId": 702,
                            "permissionName": "新增"
                        },
                        {
                            "permissionId": 703,
                            "permissionName": "修改"
                        },
                        {
                            "permissionId": 704,
                            "permissionName": "删除"
                        }
                    ]
                }
            ]
        },
        {
            "roleId": 2,
            "roleName": "作家",
            "users": [
                {
                    "userId": 10010,
                    "nickname": ""
                },
                {
                    "userId": 10004,
                    "nickname": "莎士比亚"
                }
            ],
            "menus": [
                {
                    "menuCode": "article",
                    "menuName": "文章管理",
                    "permissions": [
                        {
                            "permissionId": 101,
                            "permissionName": "列表"
                        },
                        {
                            "permissionId": 102,
                            "permissionName": "新增"
                        },
                        {
                            "permissionId": 103,
                            "permissionName": "修改"
                        }
                    ]
                },
                {
                    "menuCode": "user",
                    "menuName": "用户",
                    "permissions": [
                        {
                            "permissionId": 601,
                            "permissionName": "列表"
                        },
                        {
                            "permissionId": 602,
                            "permissionName": "新增"
                        },
                        {
                            "permissionId": 603,
                            "permissionName": "修改"
                        }
                    ]
                },
                {
                    "menuCode": "role",
                    "menuName": "角色权限",
                    "permissions": [
                        {
                            "permissionId": 701,
                            "permissionName": "列表"
                        },
                        {
                            "permissionId": 702,
                            "permissionName": "新增"
                        },
                        {
                            "permissionId": 703,
                            "permissionName": "修改"
                        },
                        {
                            "permissionId": 704,
                            "permissionName": "删除"
                        }
                    ]
                }
            ]
        },
        {
            "roleId": 3,
            "roleName": "程序员",
            "users": [
                {
                    "userId": 10007,
                    "nickname": "就看看列表"
                },
                {
                    "userId": 10011,
                    "nickname": "nic"
                }
            ],
            "menus": [
                {
                    "menuCode": "article",
                    "menuName": "文章管理",
                    "permissions": [
                        {
                            "permissionId": 101,
                            "permissionName": "列表"
                        },
                        {
                            "permissionId": 102,
                            "permissionName": "新增"
                        }
                    ]
                },
                {
                    "menuCode": "user",
                    "menuName": "用户",
                    "permissions": [
                        {
                            "permissionId": 601,
                            "permissionName": "列表"
                        },
                        {
                            "permissionId": 603,
                            "permissionName": "修改",
                            "requiredPermission": null
                        }
                    ]
                },
                {
                    "menuCode": "role",
                    "menuName": "角色权限",
                    "permissions": [
                        {
                            "permissionId": 701,
                            "menuCode": null,
                            "menuName": null,
                            "permissionCode": null,
                            "permissionName": "列表",
                            "requiredPermission": null
                        },
                        {
                            "permissionId": 702,
                            "menuCode": null,
                            "menuName": null,
                            "permissionCode": null,
                            "permissionName": "新增",
                            "requiredPermission": null
                        },
                        {
                            "permissionId": 704,
                            "menuCode": null,
                            "menuName": null,
                            "permissionCode": null,
                            "permissionName": "删除",
                            "requiredPermission": null
                        }
                    ]
                }
            ]
        },
        {
            "roleId": 4,
            "roleName": "只看文章",
            "users": [
                {
                    "userId": 10009,
                    "userName": null,
                    "password": null,
                    "nickname": "测试昵称",
                    "roleId": null,
                    "createTime": null,
                    "updateTime": null,
                    "deleteStatus": null
                }
            ],
            "menus": [
                {
                    "menuCode": "article",
                    "menuName": "文章管理",
                    "permissions": [
                        {
                            "permissionId": 101,
                            "menuCode": null,
                            "menuName": null,
                            "permissionCode": null,
                            "permissionName": "列表",
                            "requiredPermission": null
                        }
                    ]
                }
            ]
        }
    ]
}
```

**参数说明：**

| 字段名 | 变量名 | 必填 | 类型 | 描述 |
| ------ | ------ | ---- | ---- | ---- |
|        |        |      |      |      |

**备注：**



### 14、查询所有权限

**简要描述：**

- 查询所有权限, 给角色分配权限时调用

**请求URL：**

- http://localhost:8080/role/listAllPermission

**请求方式：**

- GET

**请求示例：**

```
token 1c9a7e74-a5dd-4ae6-8b90-cf2cf60292a0
```

**返回示例：**

```json
{
    "code": 100,
    "msg": "请求成功",
    "info": [
        {
            "menuCode": "article",
            "menuName": "文章管理",
            "permissions": [
                {
                    "permissionId": 101,
                    "permissionName": "列表",
                    "requiredPermission": "1"
                },
                {
                    "permissionId": 102,
                    "permissionName": "新增",
                    "requiredPermission": "2"
                },
                {
                    "permissionId": 103,
                    "permissionName": "修改",
                    "requiredPermission": "2"
                }
            ]
        },
        {
            "menuCode": "user",
            "menuName": "用户",
            "permissions": [
                {
                    "permissionId": 601,
                    "permissionName": "列表",
                    "requiredPermission": "1"
                },
                {
                    "permissionId": 602,
                    "permissionName": "新增",
                    "requiredPermission": "2"
                },
                {
                    "permissionId": 603,
                    "permissionName": "修改",
                    "requiredPermission": "2"
                }
            ]
        },
        {
            "menuCode": "role",
            "menuName": "角色权限",
            "permissions": [
                {
                    "permissionId": 701,
                    "permissionName": "列表",
                    "requiredPermission": "1"
                },
                {
                    "permissionId": 702,
                    "permissionName": "新增",
                    "requiredPermission": "2"
                },
                {
                    "permissionId": 703,
                    "permissionName": "修改",
                    "requiredPermission": "2"
                },
                {
                    "permissionId": 704,
                    "permissionName": "删除",
                    "requiredPermission": "2"
                }
            ]
        }
    ]
}
```

**参数说明：**

| 字段名 | 变量名 | 必填 | 类型 | 描述 |
| ------ | ------ | ---- | ---- | ---- |
|        |        |      |      |      |

**备注：**



### 15、新增角色

**简要描述：**

- 新增角色

**请求URL：**

- http://localhost:8080/role/addRole

**请求方式：**

- POST

**请求示例：**

```
token 1c9a7e74-a5dd-4ae6-8b90-cf2cf60292a0
```

```json
{
	"roleName":"新角色",
	"permissions":[101, 102]
}
```

**返回示例：**

```json
{
    "code": 100,
    "msg": "请求成功",
    "info": null
}
```

```json
{
    "code": 10010,
    "msg": "角色已存在",
    "info": null
}
```

**参数说明：**

| 字段名 | 变量名 | 必填 | 类型 | 描述 |
| ------ | ------ | ---- | ---- | ---- |
|        |        |      |      |      |

**备注：**



### 16、修改角色

**简要描述：**

- 修改角色

**请求URL：**

- http://localhost:8080/role/updateRole

**请求方式：**

- PUT

**请求示例：**

```
token 1c9a7e74-a5dd-4ae6-8b90-cf2cf60292a0
```

```json
{
	"roleId": 7,
	"roleName":"新角色aaa",
	"permissions":[101, 103]
}
```

**返回示例：**

```json
{
    "code": 100,
    "msg": "请求成功",
    "info": null
}
```

**参数说明：**

| 字段名 | 变量名 | 必填 | 类型 | 描述 |
| ------ | ------ | ---- | ---- | ---- |
|        |        |      |      |      |

**备注：**



### 17、删除角色

**简要描述：**

- 删除角色

**请求URL：**

- http://localhost:8080/role/deleteRole/4

**请求方式：**

- DELETE

**请求示例：**

```
token 1c9a7e74-a5dd-4ae6-8b90-cf2cf60292a0
```

```
http://localhost:8080/role/deleteRole/4
```

**返回示例：**

```json
{
    "code": 10008,
    "msg": "角色删除失败,尚有用户属于此角色",
    "info": null
}
```

```json
{
    "code": 100,
    "msg": "请求成功",
    "info": null
}
```

**参数说明：**

| 字段名 | 变量名 | 必填 | 类型 | 描述 |
| ------ | ------ | ---- | ---- | ---- |
|        |        |      |      |      |

**备注：**


