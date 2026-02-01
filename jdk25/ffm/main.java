
import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

void main(String[] args) {
        try {
                
            // 获取动态链接库的路径
            File file = new File("./hellolib");
            URL url = file.toURL();
            if (!Objects.isNull(url)) {
                Path linkLibraryPath = Paths.get(url.toURI());
 
                // 创建本地库链接器
                Linker linker = Linker.nativeLinker();
 
                // 在全局 Arena（内存区域）内查找符号
                SymbolLookup lookup = SymbolLookup.libraryLookup(linkLibraryPath, Arena.global());
 
                // 1. 调用 hello_world 函数
                MemorySegment helloSegment = lookup.find("hello_world")
                        .orElseThrow(() -> new RuntimeException("函数 hello_world not 未找到"));
                MethodHandle helloHandle = linker.downcallHandle(helloSegment, FunctionDescriptor.ofVoid());
                helloHandle.invoke();
 
                // 2. 调用 create_string 函数，获取字符串
                MemorySegment stringSegment = lookup.find("create_string")
                        .orElseThrow(() -> new RuntimeException("函数 create_string 未找到"));
                MethodHandle stringHandle = linker.downcallHandle(
                        stringSegment,
                        FunctionDescriptor.of(ValueLayout.ADDRESS)
                );
 
                // 调用函数获取返回的指针
                MemorySegment stringPtr = (MemorySegment) stringHandle.invoke();
 
                // 将指针转换为实际的内存段（假设字符串最大长度为256）
                MemorySegment stringMemory = stringPtr.reinterpret(256);
 
                // 读取以null结尾的C字符串
                String resultString = stringMemory.getString(0, StandardCharsets.UTF_8);
                System.out.println("create_string 返回: " + resultString);
 
                // 3. 调用 add_numbers 函数，计算两个整数之和
                MemorySegment addSegment = lookup.find("add_numbers")
                        .orElseThrow(() -> new RuntimeException("函数 add_numbers 未找到"));
                MethodHandle addHandle = linker.downcallHandle(addSegment, FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT));
 
                int num1 = 10;
                int num2 = 20;
                int sum = (int) addHandle.invoke(num1, num2);
                System.out.println("add_numbers(" + num1 + ", " + num2 + ") 返回: " + sum);
            }
            System.out.println("DLL 路径: " + url.toString());
        } catch (Throwable e) {
            throw new RuntimeException("调用 DLL 失败", e);
        }

}