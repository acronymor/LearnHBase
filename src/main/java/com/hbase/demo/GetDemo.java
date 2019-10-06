package com.hbase.demo;

import com.hbase.demo.client.SidxOperation;
import com.hbase.demo.client.SidxOperatorNode;
import com.hbase.demo.client.SidxResult;
import com.hbase.demo.client.SidxTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

/**
 * @author apktool
 * @title: com.hbase.demo.GetDemo
 * @description: TODO
 * @date 2019-10-06 11:47
 */
@Service
public class GetDemo {

    @Autowired
    private SidxOperation sidxOperation;

    public void start(String[] args) {
        SidxTable table = new SidxTable().of("test");
        String value = "a61c5142-b069-4898-a332-7cfb501c91d6";

        SidxOperatorNode node = new SidxOperatorNode(
            SidxOperatorNode.SidxCompareOperatorKind.GREATER_OR_EQUAL,
            Bytes.toBytes("f1"),
            Bytes.toBytes("c1"),
            Bytes.toBytes(value)
        );

        Iterator<SidxResult> iterator = sidxOperation.get(table, node);
        while (iterator.hasNext()) {
            Result result = iterator.next().getResult();
            byte[] data = result.getValue(Bytes.toBytes("f2"), Bytes.toBytes("c1"));
            System.out.println(Bytes.toString(data));
        }
    }
}