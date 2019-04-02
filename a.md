@Api()：作用于类上，表示这个类是swagger的资源。
    tags = ”说明该类的作用“
@ApiOperation()：用在请求的方法上，说明的方法的用户和作用
	value=“说明方法的用途、作用”
    notes="方法的备注说明“
    
@ApiImplicitParams()：用在请求的方法上，表示一组参数说明，可以包含多个@ApiImplicitParam()

	@ApiImplicitParam()：指定一个请求参数的各个方面
	   name：参数名
       value：参数的汉字说明
       required：参数是否必须传
       dataType：参数类型
       defaultValue：参数的默认值
       
@ApiResponses()：用在请求的方法上，表示一组响应。可以包含多个@ApiResponse()

@ApiResponse()：用于表示一个错误的响应信息
	 code：数字
    message：信息
    response：抛出异常的类   
    
@ApiModel()：用在响应类上，表示一个返回响应数据的信息。
@ApiModelProperty()：用在属性上，描述响应类的属性




打包   Run As-->Maven build... 参数：  clean package  -Dmaven.test.skip=true
运行  ：nohup java -jar target/xxx.jar &
