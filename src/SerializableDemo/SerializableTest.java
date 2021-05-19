package SerializableDemo;

import java.io.*;

public class SerializableTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //  如果将序列化对象改成父类，则会抛出异常，没有标记为Serializable接口
//        Father father = new Father();
        Father father = new Son();
        father.f = 5;

        //  序列化，写入文件
        FileOutputStream fileOutputStream  = new FileOutputStream("temp.o");
        ObjectOutput objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(father);

        //  反序列化，取出
        FileInputStream fileInputStream = new FileInputStream("temp.o");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Object object = objectInputStream.readObject();
        Father f = (Father) object;
        //  由于子类没有f这个变量，是调用的父类的f变量
        System.out.println(f.f);

        //输出结果， 当父类没有实现Serializable时：f = 0
        // 当父类实现Serializable接口时，f = 5；
        // 因此，在实体bean中都应该显示地实现序列化接口。
    }
}
