#关于spring.Validation　通用数据校验工具的使用说明

1.如何使用
       1.再需要参数校验的工程引入classpath:validation/applicationContext-validation.xml
       2.在需要校验的类和接口上加入@Validated

１．默认有三种校验器（都是可继承的）

     ＠NotEmpty 为空判断
     ＠Length  长度校验
     @Pattern  正则校验

     注解公共属性:messageCode,message,groups
   
    1. messageCode:消息码（当messageCode有值的情况下，会读取      classpath:validation/validation.properties文件，否则读取message）
    2. message:消息
    3.groups:校验分组


2.关于扩展自定义校验规则
   1.新建一个校验规则注解
   2.继承AbstractValidationHandler或实现ValidationHandler类实现校验规则，使用    ValidationHandlerExpand类来扩展新的校验规则（建议继承AbstractValidationHandler）

Demo：
<bean class="ValidationHandlerExpand">
    <property name="validationHandlers">
        <list>
            <bean class="NotNullHandler"></bean>
        </list>
    </property>
</bean>

3.建议
当系统某一校验规则多次出现的情况下建议扩展校验器。