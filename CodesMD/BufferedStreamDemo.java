import java.io.*;

public class BufferedStreamDemo {
    public static void main(String[] args) throws IOException {
        File srcFile = new File("E:\\workspace\\IdeaProjects\\Study_Java\\testFile\\ok.txt");
        FileInputStream in = new FileInputStream(srcFile);
        BufferedInputStream bin = new BufferedInputStream(in);
        //BufferedInputStream bin=new BufferedInputStream(new FileInputStream("E:\workspace\IdeaProjects\Study_Java\testFile\ok.txt"));
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = in.read(buffer)) != -1) {
            System.out.println(new String(buffer, 0, len));
            //控制台输出结果
        }
        //关闭输入流，只需关闭缓冲输入流即可
        in.close();
        File destFile = new File("E:\\workspace\\IdeaProjects\\Study_Java\\testFile\\test1_copy.txt");
        FileOutputStream out = new FileOutputStream(destFile, true);//可追加
        BufferedOutputStream bos = new BufferedOutputStream(out);
        //BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream("E:\workspace\IdeaProjects\Study_Java\testFile\test1_copy.txt",true))
        bos.write("我试了一下字节缓冲输出流，感觉挺好用的！".getBytes());
        /*本题为字节流，所以牵扯的参数、转换等都为字节级别（byte）：
            String s = "fs123fdsa";//String变量
            byte b[] = s.getBytes();//String转换为byte[]
            String t = new String(b);//bytep[]转换为String
        */
        /*若为字符流，则锁涉及的参数、转换等都为字符级（char)操作
            String s=String.valueof(a);
            char[] b=s.toCharArray();
         */
        //文件中显示上面的内容
        //关闭输出流
        bos.close();
    }
}
