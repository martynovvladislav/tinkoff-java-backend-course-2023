package edu.hw6;

import edu.hw6.task1.DiskMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestsForTask1 {
    @Test
    @DisplayName("save and load data test")
    void saveAndLoadDataTest() {
        DiskMap diskMap = new DiskMap();
        diskMap.put("12345", "12345");
        diskMap.put("123456", "abcde");
        diskMap.saveData();
        DiskMap diskMap1 = new DiskMap();
        diskMap1.loadData();
        Assertions.assertEquals(diskMap1.entrySet().toString(), diskMap.entrySet().toString());
    }

    @Test
    @DisplayName("update content test")
    void updateContentTest() {
        DiskMap diskMap = new DiskMap();
        diskMap.put("abc", "abcd");
        diskMap.put("123", "321");
        DiskMap diskMap1 = new DiskMap();
        diskMap1.put("abc", "abcd");
        diskMap1.put("123", "321");
        diskMap1.put("Java", "C++");
        diskMap1.saveData();
        diskMap1.remove("Java");
        diskMap1.saveData();
        diskMap1.clear();
        diskMap1.loadData();
        Assertions.assertEquals(diskMap1.entrySet().toString(), diskMap.entrySet().toString());
    }
}
