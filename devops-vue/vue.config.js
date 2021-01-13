module.exports = {

	css: {
		loaderOptions: {
			less: {
				lessOptions: {
					modifyVars: {
						'primary-color': '#1da57a',
						'link-color': '#1DA57A',
						'border-radius-base': '2px',
					},
					javascriptEnabled: true,
				},
			},
		},
	},


	devServer: {
		proxy: {
			'/api': {
				target: 'http://localhost:8001/mvc/mvc', // 你要请求的后端接口ip+port
				// target: 'http://172.16.24.4:8001/mvc/mvc', // 你要请求的后端接口ip+port
				changeOrigin: true, // 允许跨域，在本地会创建一个虚拟服务端，然后发送请求的数据，并同时接收请求的数据，这样服务端和服务端进行数据的交互就不会有跨域问题
				ws: true, // 开启webSocket
				pathRewrite: {
					'^/api': '', // 替换成target中的地址
				}
			}
		}
	},
	lintOnSave: false
}
