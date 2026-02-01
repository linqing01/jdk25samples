#include <stdio.h>
#include <stdlib.h>
#include <string.h>
 
// 打印 "Hello, World!"
void hello_world() {
    printf("Hello, World, hahaha!\n");
    return;
}


// 返回动态分配的字符串 “hello ， World！”
char* create_string() {
    char* str = (char *)malloc(15); 
    if (str) {
        strcpy(str, "你好,世界！");
    }
    return str;
}

int add_numbers(int a, int b) {
    return a + b;
}