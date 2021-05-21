// 1.将可能抛出FileNotFoundException 文件不存在异常的代码放在try里
// 2.如果文件存在，就会顺序往下执行，并且不执行catch块中的代码
// 3. 如果文件不存在，try 里的代码会立即终止，程序流程会运行到对应的catch块中
// 4. e.printStackTrace(); 会打印出方法的调用痕迹，如此例，
// 会打印出异常开始于TestException的第16行，这样就便于定位和分析到底哪里出了异常

import java.io.File;
import java.io.FileInputStream;
import java.io.FilterInputStream;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import sun.misc.PostVMInitHook;
import sun.net.www.content.text.plain;

import java.io.FileNotFoundException;

public class TestException {
    public static void main(String[] args) {
        File f = new File("d:/LOL.exe");\

        try {
            System.out.print("尝试打开");
            new FileInputStream(f);
            System.out.print("成功打开");
        } catch (FileNotFoundException e) {
            //TODO: handle exception
            System.out.print("打不开");
            e.printStackTrace();
        }
    }
}