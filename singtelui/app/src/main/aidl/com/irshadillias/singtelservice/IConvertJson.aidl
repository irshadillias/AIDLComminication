// IConvertJson.aidl
package com.irshadillias.singtelservice;
import com.irshadillias.singtelservice.IConvertJsonLister;
// Declare any non-default types here with import statements

interface IConvertJson {
    int addNumbers(int num1, int num2);
    void convertJson(String txt,IConvertJsonLister lister);
    void addNumber(int num1,int num2,IConvertJsonLister lister);
    void substract(int num1,int num2,IConvertJsonLister lister);
    void multiply(int num1, int num2,IConvertJsonLister lister);
    void pow(int num1, int num2,IConvertJsonLister lister);

}
