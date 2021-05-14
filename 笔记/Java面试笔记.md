## 笔记

**1. Java语言特点：**

1.简单易学；

2.面向对象；

3.平台无关性；

4.可靠性；

5.安全性；

6.多线程编程；

7.编译与解释并行；

**2. 字符型常量和字符串常量的区别**

1.形式上不同：字符常量单引号，字符串常量双引号；

2.含义上不同：字符常量相当于一个整型值，可以参加表达式运算；字符串常量代表一个地址值；

3.占内存大小不同：字符常量只占 2 个字节; 字符串常量占若⼲个字节；

**3. 构造器Constructor是否可被override**

 不能，但可以被overload（重载）。

**4. 重载和重写的区别**

> 重载是同样的一个方法能够根据输入的数据不同，做出不同的处理
>
> 重写是当子类继承自父类的相同方法，输入数据一样，但又做出有别于父类的响应时，你就要写覆盖父类方法。

1. 重载：

发生在同一个类中，方法名必须相同，参数类型不同、个数不同、顺序不同，方法返回值和访问修饰符可以不同。

2. 重写

重写发生在运行期，是子类对父类的允许访问的方法的实现过程进行重写编写

```java
public class Hero {
 public String name() {
 return "超级英雄";
 }
}
public class SuperMan extends Hero{
 @Override
 public String name() {
 return "超⼈";
 }
 public Hero hero() {
 return new Hero();
 }
}
public class SuperSuperMan extends SuperMan {
    public String name() {
 return "超级超级英雄";
 }
 @Override
 public SuperMan hero() {
 return new SuperMan();
 }
}

```

**5. 异常**

```java
pakeage exception;

import java.io.File;
import java.io.FileInputStream;

public class TestException{
    public static void main(String[] args){
        File f = new File("d:/LOL.exe");
        new FileInputStream(f);
    }
}
```

**6. 异常处理**

```java
/*
1.将可能抛出FileNotFoundException 文件不存在异常的代码放在try里
2.如果文件存在，就会顺序往下执行，并且不执行catch块中的代码
3. 如果文件不存在，try 里的代码会立即终止，程序流程会运行到对应的catch块中
4. e.printStackTrace(); 会打印出方法的调用痕迹，如此例，会打印出异常开始于TestException的第16行，这样就便于定位和分析到底哪里出了异常
*/

pakeage exception;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TestException{
    public static void main(String[] args){
        File f = new File("d:/LOLO.exe");
        
        try{
            System.out.println("试图打开 d:/LOL.exe");
            new FileInputStream(f);
            System.out.print("成功打开");
        }
        catch(FileNotFoundException e){
            System.out.println("d:/LOL.exe");
            e.printStackTrace();
        }
    }
}
```

