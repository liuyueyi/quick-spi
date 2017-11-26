## SPI框架
> 本项目工程主要是实现了一个spi的使用框架，借此可以方便的利用spi的思想，实现一些业务场景的区分

### 设计思路

下图围绕 `SpiLoader` 为中心，描述了三个主要的流程： 

1. load所有的spi实现
2. 初始化选择器 selector
3. 获取spi实现类 （or一个实现类代理）

![https://static.oschina.net/uploads/img/201705/26185143_ULnL.png](https://static.oschina.net/uploads/img/201705/26185143_ULnL.png)

### 流程说明
> 下面就整个实现的流程顺一遍，主要从使用者的角度出发，当定义了一个SPI接口后，到获取spi实现的过程中，上面的这些步骤是怎样串在一起的

### 流程图

先拿简单的静态获取SPI实现流程说明，先看下这种用法的使用姿势

```java
@Spi
public interface IPrint {
    void print(String str);
}

public class FilePrint implements IPrint {
    @Override
    public void print(String str) {
        System.out.println("file print: " + str);
    }
}

public class ConsolePrint implements IPrint {

    @Override
    public void print(String str) {
        System.out.println("console print: " + str);
    }
}

@Test
public void testPrint() throws NoSpiMatchException {
   SpiLoader<IPrint> spiLoader = SpiLoader.load(IPrint.class);
   IPrint print = spiLoader.getService("ConsolePrint");
   print.print("console---->");
}
```

#### `SpiLoader<IPrint> spiLoader = SpiLoader.load(IPrint.class);` 

这行代码触发的action 主要是初始化所有的选择器, 如下图

- 首先从缓存中查
-  是否已经初始化过了有则直接返回；
- 缓存中没有，则进入new一个新的对象出来
    - 解析类上注解 `@Spi`，初始化 `currentSelector` 
    - 解析所有方法的注解 `@SpiAdaptive` ， 初始化 `currentMethodSelector`
- 塞入缓存，并返回

![https://static.oschina.net/uploads/img/201705/27140821_19ee.png](https://static.oschina.net/uploads/img/201705/27140821_19ee.png)

#### `IPrint print = spiLoader.getService("ConsolePrint");`

根据name获取实现类，具体流程如下

- 判断是否加载过所有实现类 `spiImplClassCacheMap`
- 没有加载，则重新加载所有的实现类
    - 通过jdk的 `ServiceLoader.load()` 方法获取所有的实现类
    - 遍历实现类，根据 `@SpiConf` 注解初始化参数，封装 `SpiImplWrapper `对象
    - 保存封装的 `SpiImplWrapper`对象到缓存
- 执行 `currentSelector.select()` 方法，获取匹配的实现类


![https://static.oschina.net/uploads/img/201705/27150620_EOUL.png](https://static.oschina.net/uploads/img/201705/27150620_EOUL.png)


### 动态编译

项目中实现的动态编译，主要借助的是GroovyEngine来实现，将我们根据SPI接口动态生成的实现代理类当做一个groovy脚本，其核心代码如下

```java
@SuppressWarnings("unchecked")
public static <T> T compile(String code, Class<T> interfaceType, ClassLoader classLoader) throws SpiProxyCompileException {
   GroovyClassLoader loader = new GroovyClassLoader(classLoader);
   Class clz = loader.parseClass(code);

   if (!interfaceType.isAssignableFrom(clz)) {
       throw new IllegalStateException("illegal proxy type!");
   }


   try {
       return (T) clz.newInstance();
   } catch (Exception e) {
       throw new SpiProxyCompileException("init spiProxy error! msg: " + e.getMessage());
   }
}
```

### 相关文档


博客系列链接：

- [SPI框架实现之旅一：背景介绍](https://my.oschina.net/u/566591/blog/911054)
- [SPI框架实现之旅二：整体设计](https://my.oschina.net/u/566591/blog/911055)
- [SPI框架实现之旅三：实现说明](https://my.oschina.net/u/566591/blog/911056)
- [SPI框架实现之旅四：使用测试](https://my.oschina.net/u/566591/blog/911076)

