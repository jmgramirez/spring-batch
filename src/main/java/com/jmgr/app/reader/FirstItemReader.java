package com.jmgr.app.reader;

import java.util.List;

import org.springframework.batch.infrastructure.item.ItemReader;
import org.springframework.stereotype.Component;

@Component
public class FirstItemReader implements ItemReader<Integer> {
    List<Integer> items = List.of(1, 2, 3, 4, 5, 6, 7);
    int i = 0;

    @Override
    public Integer read() throws Exception {
        System.out.println("DEntro del ItemReader: " + items);
        Integer item;
        if (i < items.size()) {
            item = items.get(i);
            i++;
            return item;
        }
        return null;
    }

}
