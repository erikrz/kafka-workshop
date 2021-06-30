package com.demo;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.demo.invoice.InvoiceApplication;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = InvoiceApplication.class)
public class SpringBootWithKafkaApplicationTests {

    @Test
    public void contextLoads() {
    }

}
