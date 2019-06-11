# vue-demo

### 配置

config/index.js文件中，proxyTable 用来配置后端请求的接口路径，host 和 port 用来配置Vue项目运行的主机和端口，

```
// Paths
assetsSubDirectory: 'static',
assetsPublicPath: '/',
proxyTable: {
	'/api': {
		target: 'http://localhost:8080',
		pathRewrite: {
			'^/api': '/back'
		}
	}
},

// Various Dev Server settings
host: 'localhost', 
port: 8081,
```

