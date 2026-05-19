package com.jmgr.app.writer;



import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class FirstItemWritter implements ItemWriter<String> {

    @Override
    public void write(Chunk<? extends String> chunk) throws Exception {
        System.out.println("Dentro del ItemWriter: " + chunk);
        chunk.forEach(System.out::println);
    }

}
