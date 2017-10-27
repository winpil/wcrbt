日期控件的使用：
文件目录在/static/My97DatePicker
1.导入WdatePicker.js文件，如<script type="text/javascript" src="WdatePicker.js"></script>
2.在<input 中加入如下代码<input class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false})">即可
WdatePicker.js文件已在style.jsp中导入，使用时<head></head>里面加<%@ include file="/WEB-INF/views/include/style.jsp"%>即可
项目中使用可以复制
<input name="birthday" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
	value="<fmt:formatDate value="${testPerson.birthday}" pattern="yyyy-MM-dd HH:mm:ss"/>"
	onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>


前端js对密码进行sha1加密：
项目管理后台加密使用了sha1加密，登陆页面一级用户管理中新增用户填请求controller时采取了先加密再请求。加密的js文件在/static/sha/下。使用方法：
1.导入sha.js文件
2.执行加密代码如下
var password = "123456";
var shaObj = new jsSHA("SHA-1", "TEXT");
shaObj.update(password);
password = shaObj.getHash("HEX");
jsSHA这个对象根据不用参数可以实例化不同的sha系列加密对象，如sha-2、sha-256等等，具体请浏览 https://github.com/Caligatio/jsSHA
sha.js文件已在style.jsp中导入，使用时<head></head>里面加<%@ include file="/WEB-INF/views/include/style.jsp"%>即可


后台Java的sha1加密：
项目中已导入apache-shiro的jar包，里面就有sha1加密方法
用法：String aa = new Sha1Hash("123").toString();即可，得到的加密结果和上面sha.js加密的一样

下拉列表form:select标签和jquery-select2.js组件：
项目中下拉框已经集成jquery-select2组件，具备检索功能，快速开发请拷贝
<form:select path="属性名" class="input-medium">
	<form:option value="" label=""/>
	<form:options items="${一个Map}"   htmlEscape="false"/>
</form:select>

表单必填项拦截：
项目中集成好了jquery-validation.js控件，在必填的表单控件中的class添加样式required即可，所有class中含required
的表单控件如果为空将不能提交并会显示提示信息

富文本编辑器：
项目富文本集成有ckeditor，具体用法直接看/static/ckeditor下的demo.jsp即可，需要注意的是要把ckfinder.xml<baseURL>xxx</baseURL>中
最前面的一级目录改成自己的项目名，如：我的项目叫aa，那么就改成<baseURL>/aa/xx/</baseURL>，xx随便起自己喜欢的名字即可,注意，记得去spring-mvc.xml
添加xx目录的资源静态化，如：<mvc:resources mapping="/xx/**" location="/xx/"/>。否则spring-mvc会拦截掉

图片选择器
图片类型的表单字段
<sys:img name="表单字段名" value="${xxx }" multiSelect="true"/>
xxx是对应回显的值，multiSelect可否多选，true可以，false不可以

文件选择器
<sys:file name="表单字段名" value="${xxx }"/>
xxx是对应回显的值

sys.js文件的使用：
目录：在/static/sys/js/下

js去掉空格
js的replace方法只替换字符串中的第一个字符，如果abcaa.replace('a','f')会得到fbcaa，显然满足不了替换字符中全部所有a的目的，需要使用正则，在sys.js中
封装好了去掉空格的方法trim(str)，函数返回一个去除所有空格后的字符串

判断字符非空
在sys.js中封装好方法notBlank(str)，如果不是空，返回true，否则返回false
不需要再写if(typeof(str) == 'undefined' || str == null || str == 'null' || str == '')等代码

省市下拉选择框ajax异步请求
并在相应select表单控件中添加onchange="provinceChange()"或onchange="cityChange()"即可




字典表达式：
解析属性：
${fnc:getDictLabel(实体类属性, '字典type' ,'默认值') }
获取字典：
${fnc:getDictList('字典type')}，常与<form:select 一起用，如：
<form:select path="属性名" class="input-medium">
	<form:option value="" label=""/>
	<form:options items="${fnc:getDictList('字典type')}"   htmlEscape="false"/>
</form:select>

获取所有省份，返回Map：
${fnc:getAllProvince()}

<form:select path="属性名" class="input-medium">
	<form:option value="" label=""/>
	<form:options items="${fnc:getAllProvince()}"   htmlEscape="false"/>
</form:select>

获取城市列表，返回Map，用于编辑时回显：
${fnc:getCityMap(cityId)}

<form:select path="cityId" class="input-medium">
	<form:option value="" label=""/>
	<form:options items="${fnc:getCityMap(entity.cityId)}"   htmlEscape="false"/>
</form:select>

获取区列表，返回Map，用于编辑时回显：
${fnc:getDistrictMap(districtId)}

<form:select path="districtId" class="input-medium">
	<form:option value="" label=""/>
	<form:options items="${fnc:getDistrictMap(entity.districtId)}"   htmlEscape="false"/>
</form:select>


根据provinceId、cityId、districtId获取省、市、区的名称：

${fnc:getProvinceName(entity.provinceId)}
${fnc:getCityName(entity.cityId)}
${fnc:getDistrictName(entity.districtId)}




其他：
mybatis模糊查询语法：
字段名 LIKE concat('%',#{参数名},'%')

