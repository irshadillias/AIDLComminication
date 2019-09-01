// IConvertJson.aidl
package com.irshadillias.singtelservice;
import com.irshadillias.singtelservice.IConvertJsonLister;
// Declare any non-default types here with import statements

interface IConvertJson {
    int addNumbers(int num1, int num2);
    void convertJson(String txt,IConvertJsonLister lister);
}
