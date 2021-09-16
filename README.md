# 说明
试验性质的运行了一下，修改了部分内容。不确定是否可以抢到

**代码fork于 [https://github.com/rejigtian/jd_seckill](https://github.com/rejigtian/jd_seckill)**

主要修改内容有

- 增加配置文件，把pid和数量放到里面
- 修改pom文件，可以打成可运行的jar包
- 可在linux上运行，不过需要自行将图片目录设置成可访问地址

> 在linux运行时，yml文件里的qrcode不能为空。如果不是linux，qrcode配置项需要关闭



## 环境

- Windows 10 or MAC
- Oracle JDK 8

## 使用

- 编辑Start.java
- 运行Class Start
- 输入商品ID
- 输入商品数量
- 扫码登录

